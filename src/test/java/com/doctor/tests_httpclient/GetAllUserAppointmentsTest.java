package com.doctor.tests_httpclient;

import com.doctor.core.TestBaseAPI;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class GetAllUserAppointmentsTest extends TestBaseAPI {

    @Test
    public void getAllUserAppointmentsTest() {
        // Указываем номер страницы для тестирования
        int pageNumber = 1;

        // Отправка GET-запроса
        Response response = given()
                .header("Cookie", cookies) // Добавляем куки для авторизации
                .when()
                .get("/api/appointments?page=" + pageNumber) // Указываем номер страницы
                .then()
                .assertThat()
                .statusCode(200) // Проверяем успешный ответ
                .log().all() // Логируем запрос и ответ
                .extract()
                .response(); // Извлекаем полный ответ

        // Извлечение данных из ответа
        boolean hasNext = response.jsonPath().getBoolean("hasNext");
        assertNotNull(hasNext, "Поле 'hasNext' не должно быть null");

        // Проверяем, что данные возвращены
        var appointments = response.jsonPath().getList("data");
        assertNotNull(appointments, "Данные о терминах не должны быть null");
        assertTrue(appointments.size() > 0, "Список терминов должен содержать хотя бы один элемент");

        // Выводим данные для отладки
        System.out.println("Has Next: " + hasNext);
        System.out.println("Appointments: " + appointments);
    }

    @Test
    public void getEmptyUserAppointmentsTest() {
        // Указываем номер страницы, которая, скорее всего, будет пустой
        int emptyPageNumber = 999;

        // Отправка GET-запроса
        Response response = given()
                .header("Cookie", cookies) // Добавляем куки для авторизации
                .when()
                .get("/api/appointments?page=" + emptyPageNumber) // Указываем пустую страницу
                .then()
                .assertThat()
                .statusCode(200) // Проверяем успешный ответ
                .log().all() // Логируем запрос и ответ
                .extract()
                .response(); // Извлекаем полный ответ

        // Проверка, что данные отсутствуют
        boolean hasNext = response.jsonPath().getBoolean("hasNext");
        var appointments = response.jsonPath().getList("data");

        assertFalse(hasNext, "Поле 'hasNext' должно быть false для пустой страницы");
        assertNotNull(appointments, "Данные о терминах не должны быть null");
        assertTrue(appointments.isEmpty(), "Список терминов должен быть пустым");
    }
}
