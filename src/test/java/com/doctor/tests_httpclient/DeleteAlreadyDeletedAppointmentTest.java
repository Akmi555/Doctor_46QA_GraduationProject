package com.doctor.tests_httpclient;

import com.doctor.core.TestBaseAPI;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class DeleteAlreadyDeletedAppointmentTest extends TestBaseAPI {

    @Test
    public void deleteAlreadyDeletedAppointmentWithStatus3Test() {
        // Получение списка терминов пользователя
        Response getResponse = given()
                .header("Cookie", cookies) // Добавляем куки для авторизации
                .when()
                .get("/api/appointments") // Запрос на получение всех терминов
                .then()
                .log().all() // Логируем ответ
                .assertThat()
                .statusCode(200) // Убедимся, что список терминов успешно получен
                .extract()
                .response();

        // Фильтрация терминов со статусом 3
        List<Map<String, Object>> appointments = getResponse.jsonPath().getList("data");
        assertNotNull(appointments, "Список терминов не должен быть null");
        assertFalse(appointments.isEmpty(), "Список терминов должен содержать хотя бы один элемент");

        Map<String, Object> deletedAppointment = appointments.stream()
                .filter(appt -> appt.get("status").equals(3)) // Фильтруем по статусу 3
                .findFirst()
                .orElseThrow(() -> new AssertionError("Не найдено терминов со статусом 3"));

        Integer appointmentId = (Integer) deletedAppointment.get("id");
        assertNotNull(appointmentId, "ID термина со статусом 3 не должен быть null");
        System.out.println("Attempting to delete already deleted appointment with ID: " + appointmentId);

        // Попытка удалить термин
        Response deleteResponse = given()
                .header("Cookie", cookies) // Добавляем куки для авторизации
                .when()
                .delete("/api/appointments/" + appointmentId) // Указываем ID термина
                .then()
                .log().all() // Логируем запрос и ответ
                .assertThat()
                .statusCode(400) // Ожидаем ошибку 400
                .extract()
                .response();

        // Проверяем сообщение об ошибке
        String responseBody = deleteResponse.getBody().asString();
        assertTrue(responseBody.contains("Falsche Daten"), "Ожидалось сообщение 'Falsche Daten'");
        System.out.println("Test passed. Server returned expected error for already deleted appointment.");
    }
}
