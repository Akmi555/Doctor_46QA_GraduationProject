package com.doctor.tests_httpclient;

import com.doctor.core.TestBaseAPI;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserProfileUpdateHttpClientTests extends TestBaseAPI {

    @Test
    public void profileUpdateSuccessfulTest() {
        // Данные для успешного обновления профиля
        String firstName = "UpdatedFirstName";
        String lastName = "UpdatedLastName";
        String email = "alice.smith@t.test";

        // Отправка запроса на обновление профиля
        given()
                .contentType("application/x-www-form-urlencoded;charset=UTF-8")
                .header("Cookie", cookies) // Добавляем куки для авторизации
                .formParam("FirstName", firstName)
                .formParam("LastName", lastName)
                .formParam("Email", email)
                .when()
                .put("/api/auth/me") // Используем PUT-запрос
                .then()
                .assertThat()
                .statusCode(200) // Проверка успешного обновления
                .log().all(); // Логирование ответа
    }

    @Test
    public void profileUpdateWithoutLastNameTest() {
        // Данные для обновления профиля без LastName
        String firstName = "UpdatedFirstName";

        // Отправка запроса на обновление профиля
        given()
                .contentType("application/x-www-form-urlencoded;charset=UTF-8")
                .header("Cookie", cookies) // Добавляем куки для авторизации
                .formParam("FirstName", firstName)
                .when()
                .put("/api/auth/me") // Используем PUT-запрос
                .then()
                .assertThat()
                .statusCode(400) // Проверка ошибки из-за отсутствующего LastName
                .log().all(); // Логирование ответа
    }
}
