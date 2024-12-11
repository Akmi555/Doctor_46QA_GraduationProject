package doctor_rest_assured_tests.httpclient;

import org.apache.http.Header;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

public class UserProfileUpdateHttpClientTests {

    private static final String LOGIN_URL = "https://gesundheitspraxis-wertvoll.de/api/auth/login";
    private static final String PROFILE_UPDATE_URL = "https://gesundheitspraxis-wertvoll.de/api/auth/me";

    private String cookieHeader; // Поле для хранения куки

    @BeforeClass
    public void loginAndRetrieveCookie() throws IOException {
        // Данные для успешного логина
        String email = "alice.smith@t.test";
        String password = "SecurePass1";

        // Отправка POST-запроса на логин
        HttpResponse response = Request.Post(LOGIN_URL)
                .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                .bodyForm(
                        Form.form()
                                .add("Email", email)
                                .add("Password", password)//из постмана

                                .build()
                )
                .execute()
                .returnResponse();

        // Проверка успешного логина
        int statusCode = response.getStatusLine().getStatusCode();
        assertEquals(statusCode, 200, "Ожидался статус 200 для успешного логина");

        // Извлечение куки из заголовков ответа
        Header[] cookies = response.getHeaders("Set-Cookie");
        assertNotNull(cookies, "Куки должны присутствовать в ответе");
        assertTrue(cookies.length > 0, "Куки не найдены в заголовках ответа");

        // Сохранение куки в поле cookieHeader
        StringBuilder cookieBuilder = new StringBuilder();
        for (Header cookie : cookies) {
            cookieBuilder.append(cookie.getValue().split(";")[0]).append("; ");
        }
        cookieHeader = cookieBuilder.toString().trim();
        System.out.println("Retrieved Cookie Header: " + cookieHeader);
    }

    @Test
    public void profileUpdateSuccessfulTest() throws IOException {
        // Данные для обновления профиля
        String firstName = "UpdatedFirstName";
        String lastName = "UpdatedLastName";
        String email = "alice.smith@t.test";
        // Отправка PUT-запроса
        HttpResponse response = Request.Put(PROFILE_UPDATE_URL)
                .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                .addHeader("Cookie", cookieHeader) // Куки из логина
                .bodyForm(
                        Form.form()
                                .add("FirstName", firstName) // Параметр из Postman
                                .add("LastName", lastName)   // Параметр из Postman
                                .add("Email", email)    // Поле Email (обязательно)
                                .build()
                )
                .execute()
                .returnResponse();

        // Проверка статус-кода
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("HTTP Status: " + statusCode);
        assertEquals(statusCode, 200, "Ожидался статус 200 для успешного обновления профиля");

        // Логирование тела ответа
        String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println("Response Body: " + responseBody);
    }

    @Test
    public void profileUpdateWithoutLastNameTest() throws IOException {
        // Данные для неудачного обновления профиля
        String firstName = "UpdatedFirstName";

        // Отправка PUT-запроса с отсутствующим LastName
        HttpResponse response = Request.Put(PROFILE_UPDATE_URL)
                .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                .addHeader("Cookie", cookieHeader) // Куки из логина
                .bodyForm(
                        Form.form()
                                .add("FirstName", firstName) // LastName отсутствует
                                .build()
                )
                .execute()
                .returnResponse();

        // Проверка статус-кода
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("HTTP Status: " + statusCode);
        assertEquals(statusCode, 400, "Ожидался статус 400 для неудачного обновления профиля");

        // Проверка тела ответа
        String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println("Response Body: " + responseBody);

        // Убедитесь, что сообщение об ошибке корректное
        assertTrue(responseBody.contains("\"LastName\""), "Ожидалась ошибка для поля LastName");
        assertTrue(responseBody.contains("The LastName field is required."),
                "Ожидалось сообщение 'The LastName field is required.'");
    }
}
