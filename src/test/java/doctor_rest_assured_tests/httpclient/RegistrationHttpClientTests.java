package doctor_rest_assured_tests.httpclient;
import org.apache.http.Header;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

public class RegistrationHttpClientTests {

    private static final String REGISTER_URL = "https://gesundheitspraxis-wertvoll.de/api/auth/register";

    @Test
    public void registerSuccessfulTest() throws IOException {
        // Уникальный email для теста
        String uniqueEmail = "test" + System.currentTimeMillis() + "@t.test";
        String firstName = "Tom";
        String lastName = "Doe";
        String phoneNumber = "1234567890";
        String password = "Qwery12345";

        // Отправка POST-запроса с форм-данными
        HttpResponse response = Request.Post(REGISTER_URL)
                .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                .bodyForm(
                        Form.form()
                                .add("FirstName", firstName)
                                .add("LastName", lastName)
                                .add("Email", uniqueEmail)
                                .add("PhoneNumber", phoneNumber)
                                .add("Password", password)
                                .build()
                )
                .execute()
                .returnResponse();

        // Проверка статус-кода
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("HTTP Status: " + statusCode);
        assertEquals(statusCode, 200, "Ожидался статус 200");

        // Проверка наличия куков в заголовке ответа
        Header[] cookies = response.getHeaders("Set-Cookie");
        assertNotNull(cookies, "Куки должны присутствовать в ответе");
        assertTrue(cookies.length > 0, "Куки не найдены в заголовках ответа");

        // Печать куков для отладки
        for (Header cookie : cookies) {
            System.out.println("Cookie: " + cookie.getValue());
            assertTrue(cookie.getValue().contains(".AspNetCore.Identity.Application"),
                    "Куки '.AspNetCore.Identity.Application' не найдены");
        }
    }

    @Test
    public void registerFailureDuplicateEmailTest() throws IOException {
        // Используем email, который уже зарегистрирован
        String duplicateEmail = "test@example.com";
        String firstName = "Tom";
        String lastName = "Doe";
        String phoneNumber = "1234567890";
        String password = "Qwery12345";

        // Отправка POST-запроса с форм-данными
        HttpResponse response = Request.Post(REGISTER_URL)
                .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                .bodyForm(
                        Form.form()
                                .add("FirstName", firstName)
                                .add("LastName", lastName)
                                .add("Email", duplicateEmail)
                                .add("PhoneNumber", phoneNumber)
                                .add("Password", password)
                                .build()
                )
                .execute()
                .returnResponse();

        // Проверка статус-кода для ошибки
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("HTTP Status: " + statusCode);
        assertEquals(statusCode, 400, "Ожидался статус 400 для дублирующегося email");

        // Проверяем отсутствие куков
        Header[] cookies = response.getHeaders("Set-Cookie");
        assertTrue(cookies == null || cookies.length == 0, "Куки не должны присутствовать в ответе для неудачной регистрации");

        // Проверка тела ответа на наличие ошибки
        String responseBody = EntityUtils.toString(response.getEntity());
        System.out.println("Response Body: " + responseBody);
        assertTrue(responseBody.contains("Diese Funktion ist derzeit nicht verfügbar"));
    }
}
