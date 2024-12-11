package doctor_rest_assured_tests.httpclient;

import org.apache.http.client.fluent.Request;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;
public class HomePageHttpClientTests {
    private static final String HOME_PAGE_URL = "https://gesundheitspraxis-wertvoll.de";

    @Test
    public void homePageResponseTimeTest() throws IOException {
        // Отправка GET-запроса
        long startTime = System.currentTimeMillis();
        HttpResponse response = Request.Get(HOME_PAGE_URL)
                .execute()
                .returnResponse();
        long endTime = System.currentTimeMillis();

        // Подсчёт времени отклика
        long responseTime = endTime - startTime;
        System.out.println("Response time: " + responseTime + "ms");

        // Проверка времени отклика
        assertTrue(responseTime < 700, "Время отклика должно быть меньше 700 мс");
    }

    @Test
    public void homePageStatusCodeTest() throws IOException {
        // Отправка GET-запроса
        HttpResponse response = Request.Get(HOME_PAGE_URL)
                .execute()
                .returnResponse();

        // Проверка статус-кода
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("HTTP Status: " + statusCode);
        assertEquals(statusCode, 200, "Ожидался статус 200 для главной страницы");
    }

    @Test
    public void homePageHtmlContentTest() throws IOException {
        // Отправка GET-запроса
        HttpResponse response = Request.Get(HOME_PAGE_URL)
                .execute()
                .returnResponse();

        // Получение тела ответа как строки
        String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println("Response Body: " + responseBody);

        // Проверка содержимого HTML
        assertTrue(responseBody.contains("<!doctype html>"), "Ответ должен содержать DOCTYPE");
        assertTrue(responseBody.contains("<title>Gesundheitspraxis wertvoll</title>"),
                "HTML должен содержать <title> с названием 'Gesundheitspraxis wertvoll'");
        assertTrue(responseBody.contains("<div id=\"root\"></div>"),
                "HTML должен содержать элемент <div id=\"root\">");
    }
}

