package com.doctor.core;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;

public class TestBaseAPI {

    protected String cookies; // Поле для хранения куков

    @BeforeMethod
    public void init() {
        RestAssured.baseURI = "https://gesundheitspraxis-wertvoll.de"; // Устанавливаем базовый URL
        loginAndRetrieveCookie(); // Выполняем авторизацию и получаем куки
    }

    private void loginAndRetrieveCookie() {
        // Данные для авторизации
        String email = "alice.smith@t.test";
        String password = "SecurePass1";

        // Отправка POST-запроса для авторизации
        cookies = given()
                .contentType("application/x-www-form-urlencoded;charset=UTF-8")
                .formParam("Email", email)
                .formParam("Password", password)
                .when()
                .post("/api/auth/login")
                .then()
                .assertThat()
                .statusCode(200) // Проверка успешного логина
                .log().all() // Логирование ответа
                .extract()
                .header("Set-Cookie"); // Извлечение куков

        if (cookies == null || cookies.isEmpty()) {
            throw new RuntimeException("Куки не были получены");
        }

        System.out.println("Полученные куки: " + cookies);
    }
}
