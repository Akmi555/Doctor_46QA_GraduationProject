package com.doctor.tests_selenium;
import com.doctor.core.TestBaseUI;
import com.doctor.model.User;
import com.doctor.pages.HomePage;
import com.doctor.pages.LoginPage;
import com.doctor.pages.RegistrationPage;
import com.doctor.utils.DataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBaseUI {
    @BeforeMethod
    public void preCondition() {
        new HomePage(app.driver, app.wait).getLoginPage().isAnmeldenButtonIsPresent();
        new LoginPage(app.driver, app.wait).isAnmeldenButtonIsPresent();

    }

    @Test(dataProvider = "userRegistrationData", dataProviderClass = DataProviders.class)
    public void registrationTest(String vorName, String nachName, String email, String telefonnummer, String passwort) {
        String newRandomEmail = System.currentTimeMillis() + email;
        System.out.println(newRandomEmail);
        RegistrationPage registrationPage = new RegistrationPage(app.getDriver(), app.wait);
        registrationPage
                .clickKontoErstellenButton()
                .enterPatienDetails(vorName, nachName, email, telefonnummer, passwort)
                .clickWeiterLink();

        if (email.endsWith(".test") && passwort.length() >= 8) {
            // Проверка успешной регистрации
            Assert.assertTrue(new HomePage(app.driver, app.wait).isAccountButtonPresent());

        }
    }

    @Test(dataProvider = "userRegistrationFromCSV", dataProviderClass = DataProviders.class)
    public void registrationFromCSVTest(User user) {
        String newRandomEmail = System.currentTimeMillis() + user.getEmail();
        System.out.println(newRandomEmail);

        RegistrationPage registrationPage = new RegistrationPage(app.getDriver(), app.wait);
        registrationPage
                .clickKontoErstellenButton()
                .enterPatienDetails(user.getName(), user.getLastName(), newRandomEmail, user.getPhone(), user.getPassword())
                .clickWeiterLink();

        if (newRandomEmail.endsWith(".test") && user.getPassword().length() >= 8) {
        }
    }

    @Test
    public void registrationWithExistingEmailTest() {
        String existingEmail = "alice.smith@t.test";
        RegistrationPage registrationPage = new RegistrationPage(app.getDriver(), app.wait);

        registrationPage
                .clickKontoErstellenButton()
                .enterPatienDetails("Alice", "Smith", existingEmail, "1234567890", "SecurePass1")
                .clickWeiterLink();
        Assert.assertTrue(new RegistrationPage(app.driver, app.wait).isUserNameAlreadyTakenNotificationPresent(), "User is logged in");
        // Проверяем наличие уведомления о занятом email

    }

    @Test
    public void registrationWithInvalidEmailTest() {
        String invalidEmail = "alice.smith@";
        RegistrationPage registrationPage = new RegistrationPage(app.getDriver(), app.wait);

        registrationPage
                .clickKontoErstellenButton()
                .enterPatienDetails("Alice", "Smith", invalidEmail, "1234567890", "SecurePass1");

        // Используем JavaScript Executor для получения системного сообщения об ошибке
        WebElement emailInputField = app.getDriver().findElement(By.xpath("//input[@type='email']"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) app.getDriver();
        String validationMessage = (String) jsExecutor.executeScript(
                "return arguments[0].validationMessage;", emailInputField
        );

        Assert.assertEquals(validationMessage, "Введите часть адреса после символа \"@\". Адрес \"alice.smith@\" неполный.", "Сообщение валидации не соответствует ожидаемому!");
        System.out.println("Сообщение валидации: " + validationMessage);
    }

    @AfterMethod(enabled = false)
    public void postCondition() {
        try {
            HomePage homePage = new HomePage(app.driver, app.wait);
            RegistrationPage registrationPage = new RegistrationPage(app.driver, app.wait);

            // Проверяем, успешна ли регистрация (по наличию кнопки аккаунта)
            if (homePage.isAccountButtonPresent()) {
                // Пользователь авторизован, разлогиниваемся
                homePage.clickAccountButton();
                homePage.clickLogoutButton();
                System.out.println("Пользователь успешно разлогинился после успешной регистрации.");
            } else {
                // Проверяем наличие сообщения об ошибке
                if (registrationPage.isRegistrationErrorMessagePresent()) {
                    registrationPage.closeRegistrationErrorMessage(); // Закрываем сообщение об ошибке
                    System.out.println("Сообщение об ошибке закрыто.");
                } else {
                    System.out.println("Сообщения об ошибке нет, дополнительных действий не требуется.");
                }
                // Перенаправляем на домашнюю страницу, если требуется
                homePage.getHomePage();
                System.out.println("Пользователь возвращен на домашнюю страницу.");
            }
        } catch (Exception e) {
            System.err.println("Ошибка в @AfterMethod: " + e.getMessage());

        }
        HomePage.clickAccountButton();
    }
}

