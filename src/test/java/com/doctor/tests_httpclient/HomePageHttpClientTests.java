package com.doctor.tests_httpclient;

import com.doctor.core.TestBaseAPI;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class HomePageHttpClientTests extends TestBaseAPI {

    @Test
    public void homePageResponseTimeTest() {
        // Проверка времени отклика и статус-кода
        long startTime = System.currentTimeMillis();
        given()
                .when()
                .get(baseURI)
                .then()
                .assertThat()
                .statusCode(200) // Проверка статус-кода
                .log().all(); // Логирование ответа
        long endTime = System.currentTimeMillis();

        // Подсчёт времени отклика
        long responseTime = endTime - startTime;
        System.out.println("Response time: " + responseTime + "ms");
        assert responseTime < 1000 : "Время отклика должно быть меньше 700 мс";
    }

    @Test
    public void homePageHtmlContentTest() {
        // Проверка содержимого HTML
        String responseBody = given()
                .when()
                .get(baseURI)
                .then()
                .assertThat()
                .statusCode(200) // Проверка статус-кода
                .extract()
                .body()
                .asString(); // Извлечение тела ответа

        System.out.println("Response Body: " + responseBody);

        // Проверка содержимого
        assert responseBody.contains("<!doctype html>") : "Ответ должен содержать DOCTYPE";
        assert responseBody.contains("<title>Gesundheitspraxis wertvoll</title>") :
                "HTML должен содержать <title> с названием 'Gesundheitspraxis wertvoll'";
        assert responseBody.contains("<div id=\"root\"></div>") :
                "HTML должен содержать элемент <div id=\"root\">";
    }
}
