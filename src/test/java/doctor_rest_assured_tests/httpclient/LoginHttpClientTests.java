package doctor_rest_assured_tests.httpclient;

import org.apache.http.Header;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Form;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

public class LoginHttpClientTests {

    private static final String LOGIN_URL = "https://gesundheitspraxis-wertvoll.de/api/auth/login";

    @Test
    public void loginSuccessfulTest() throws IOException {
        // Данные для успешного логина
        String email = "alice.smith@t.test";
        String password = "SecurePass1";

        try {
            // Отправка POST-запроса с form-data и заголовком Content-Type
            HttpResponse response = Request.Post(LOGIN_URL)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                    .bodyForm(
                            Form.form()
                                    .add("Email", email)
                                    .add("Password", password)
                                    .build()
                    )
                    .execute()
                    .returnResponse();

            // Получение тела ответа как строки
            String responseBody = EntityUtils.toString(response.getEntity());

            // Печать тела ответа для отладки
            System.out.println("Response: " + responseBody);

            // Проверка успешного логина по статусу и содержимому ответа
            assertEquals(response.getStatusLine().getStatusCode(), 200, "Ожидался статус 200");
            // Проверка статус-кода
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("HTTP Status: " + statusCode);
            assertTrue(statusCode == 200, "Ожидался статус 200 для успешного логина");

            // Извлечение куков из заголовков ответа
            Header[] cookies = response.getHeaders("Set-Cookie");
            assertNotNull(cookies, "Куки должны присутствовать в ответе");
            assertTrue(cookies.length > 0, "Куки не найдены в заголовках ответа");

            // Печать куков для отладки
            for (Header cookie : cookies) {
                System.out.println("Cookie: " + cookie.getValue());
                assertTrue(cookie.getValue().contains(".AspNetCore.Identity.Application"),
                        "Куки '.AspNetCore.Identity.Application' не найдены");
            }
        } finally {

        }
    }

        @Test
        public void loginFailureTest () throws IOException {
            // Данные для неудачного логина
            String email = "alice.smith@t.test";
            String wrongPassword = "123";

            // Отправка POST-запроса с неверными данными
            HttpResponse response = Request.Post(LOGIN_URL)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                    .bodyForm(
                            Form.form()
                                    .add("Email", email)
                                    .add("Password", wrongPassword)
                                    .build()
                    )
                    .execute()
                    .returnResponse();

            // Проверка статус-кода для неудачного логина
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("HTTP Status: " + statusCode);
            assertTrue(statusCode == 403, "Ожидался статус 403 для неверных данных");

            // Проверяем отсутствие куков
            Header[] cookies = response.getHeaders("Set-Cookie");
            assertTrue(cookies == null || cookies.length == 0, "Куки не должны присутствовать в ответе для неудачного логина");
        }
    }
