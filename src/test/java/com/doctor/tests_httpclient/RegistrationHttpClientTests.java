package com.doctor.tests_httpclient;

import com.doctor.core.TestBaseAPI;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RegistrationHttpClientTests extends TestBaseAPI {

    @Test
    public void registerSuccessfulTest() {
        // Уникальный email для регистрации
        String uniqueEmail = "test" + System.currentTimeMillis() + "@t.test";
        String firstName = "Tom";
        String lastName = "Doe";
        String phoneNumber = "1234567890";
        String password = "Qwery12345";

        // Отправка запроса на регистрацию
        given()
                .contentType("application/x-www-form-urlencoded;charset=UTF-8")
                .formParam("FirstName", firstName)
                .formParam("LastName", lastName)
                .formParam("Email", uniqueEmail)
                .formParam("PhoneNumber", phoneNumber)
                .formParam("Password", password)
                .when()
                .post("/api/auth/register")
                .then()
                .assertThat()
                .statusCode(200) // Проверка успешной регистрации
                .log().all(); // Логирование ответа
    }

    @Test
    public void registerFailureDuplicateEmailTest() {
        // Используем уже существующий email
        String duplicateEmail = "test@example.com";
        String firstName = "Tom";
        String lastName = "Doe";
        String phoneNumber = "1234567890";
        String password = "Qwery12345";

        // Отправка запроса на регистрацию с дублирующимся email
        given()
                .contentType("application/x-www-form-urlencoded;charset=UTF-8")
                .formParam("FirstName", firstName)
                .formParam("LastName", lastName)
                .formParam("Email", duplicateEmail)
                .formParam("PhoneNumber", phoneNumber)
                .formParam("Password", password)
                .when()
                .post("/api/auth/register")
                .then()
                .assertThat()
                .statusCode(400) // Проверка ошибки
                .log().all(); // Логирование ответа
    }
}
