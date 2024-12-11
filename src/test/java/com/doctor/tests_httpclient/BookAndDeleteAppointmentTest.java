package com.doctor.tests_httpclient;
import com.doctor.core.TestBaseAPI;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class BookAndDeleteAppointmentTest extends TestBaseAPI {

    private String appointmentId; // ID созданного термина

    @BeforeMethod
    public void createAppointment() {
        // Отправка POST-запроса для создания термина
        Response response = given()
                .header("Cookie", cookies) // Куки для авторизации
                .contentType("application/json") // Указываем тип содержимого
                .body("{\"isNew\": false, \"date\": 7, \"time\": \"14:00\", \"serviceId\": 3}")// Тело запроса Schröpftherapie 13.12.24 14-00
                .when()
                .post("/api/appointments")
                .then()
                .assertThat()
                .statusCode(200) // Проверяем, что термин успешно создан
                .log().all() // Логируем ответ для отладки
                .extract()
                .response();

        // Извлечение ID созданного термина
        appointmentId = response.jsonPath().getString("id");
        assertNotNull(appointmentId, "ID термина не должен быть null");

        // Лог для отладки
        System.out.println("Appointment created with ID: " + appointmentId);
    }

    @Test
    public void validateAppointmentCreation() {
        // Проверка, что ID термина был успешно получен
        assertNotNull(appointmentId, "Тест создания термина: ID термина должен быть не null");
    }

    @AfterMethod
    public void deleteAppointment() {
        if (appointmentId != null) {
            // Отправка DELETE-запроса для удаления термина
            Response response = given()
                    .header("Cookie", cookies) // Куки для авторизации
                    .when()
                    .delete("/api/appointments/" + appointmentId)
                    .then()
                    .assertThat()
                    .statusCode(204) // Проверяем, что удаление прошло успешно
                    .log().all() // Логируем ответ для отладки
                    .extract()
                    .response();

            // Лог для отладки
            System.out.println("Appointment with ID " + appointmentId + " deleted successfully");
        } else {
            System.err.println("Appointment ID is null. No deletion performed.");
        }
    }
}
