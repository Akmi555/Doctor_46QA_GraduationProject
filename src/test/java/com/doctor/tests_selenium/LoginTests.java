package com.doctor.tests_selenium;


import com.doctor.core.TestBase;
import com.doctor.pages.HomePage;
import com.doctor.pages.LoginPage;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTests extends TestBase {
    String newRandomEmail = System.currentTimeMillis() + "test@t.test";

    @BeforeMethod
    public void preCondition() {
        new HomePage(app.driver, app.wait).getLoginPage();
        new LoginPage(app.driver, app.wait).isAnmeldenButtonIsPresent();

    }

    @Test
    public void loginPositiveTest() {
        new LoginPage(app.driver, app.wait)
                .enterPersonalData("alice.smith@t.test", "SecurePass1")
                .isAnmeldenButtonIsPresent()
                .clickOnAnmeldenLink();
        Assert.assertTrue(new HomePage(app.driver, app.wait).isAccountButtonPresent(), "User is not logged in"); //реализовать на BasePage
    }

    @Test
    public void loginInvalidEmailTest() {
        new LoginPage(app.driver, app.wait)
                .enterPersonalData("test6@t", "123") // Невалидный email
                .clickOnAnmeldenLink();
        Assert.assertTrue(new LoginPage(app.driver, app.wait).isFalscheDatenNotificationPresent(), "User is logged in");


    }

    @Test
    public void loginInvalidPasswordTest() {
        new LoginPage(app.driver, app.wait)
                .enterPersonalData(newRandomEmail, "12345") // Невалидный email
                .clickOnAnmeldenLink();

        // Проверяем, что пользователь НЕ был перенаправлен на домашнюю страницу
        Assert.assertTrue(new LoginPage(app.driver, app.wait).isFalscheDatenNotificationPresent(), "User is logged in");
    }

}