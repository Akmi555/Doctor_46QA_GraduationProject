package com.doctor.tests_httpclient;

import com.doctor.core.TestBaseAPI;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class GetAllExistedServicesTest extends TestBaseAPI {

    @Test
    public void getAllServicesTest() {
        // Отправка GET-запроса
        Response response = given()
                .header("Cookie", cookies) // Добавляем куки для авторизации
                .when()
                .get("/api/services") // Конечная точка для получения списка услуг
                .then()
                .assertThat()
                .statusCode(200) // Проверяем успешный ответ
                .log().all() // Логируем запрос и ответ
                .extract()
                .response(); // Извлекаем полный ответ

        // Проверяем, что данные возвращены
        var services = response.jsonPath().getList("$");
        assertNotNull(services, "Данные об услугах не должны быть null");
        assertTrue(services.size() > 0, "Список услуг должен содержать хотя бы одну услугу");

        // Проверяем структуру первой услуги
        var firstService = response.jsonPath().getMap("[0]");
        assertNotNull(firstService.get("id"), "Поле 'id' не должно быть null");
        assertNotNull(firstService.get("name"), "Поле 'name' не должно быть null");
        assertTrue(firstService.get("image").toString().contains("/api/file"), "Поле 'image' должно содержать ссылку на файл");

        // Выводим данные для отладки
        System.out.println("Services: " + services);
    }

}