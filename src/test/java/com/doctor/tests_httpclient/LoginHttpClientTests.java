package com.doctor.tests_httpclient;

import com.doctor.core.TestBaseAPI;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginHttpClientTests extends TestBaseAPI {

    @Test
    public void loginSuccessfulTest() {
        // Данные для успешного логина
        String email = "alice.smith@t.test";
        String password = "SecurePass1";

        // Отправка запроса на логин
        cookies = given()
                .contentType("application/x-www-form-urlencoded;charset=UTF-8")
                .formParam("Email", email)
                .formParam("Password", password)
                .when()
                .post("/api/auth/login") // POST-запрос на логин
                .then()
                .assertThat()
                .statusCode(200) // Проверка успешного логина
                .log().all() // Логирование ответа
                .extract()
                .header("Set-Cookie"); // Извлечение куков

        // Проверяем, что куки были получены
        assert cookies != null && !cookies.isEmpty() : "Куки не были получены после логина";
        System.out.println("Полученные куки: " + cookies);
    }

    @Test
    public void loginFailureInvalidPasswordTest() {
        // Данные для неудачного логина
        String email = "alice.smith@t.test";
        String invalidPassword = "WrongPassword";

        // Отправка запроса на логин
        given()
                .contentType("application/x-www-form-urlencoded;charset=UTF-8")
                .formParam("Email", email)
                .formParam("Password", invalidPassword)
                .when()
                .post("/api/auth/login") // POST-запрос на логин
                .then()
                .assertThat()
                .statusCode(403) // Проверка ошибки авторизации
                .log().all(); // Логирование ответа
    }
}
