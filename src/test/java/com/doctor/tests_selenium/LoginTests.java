package com.doctor.tests_selenium;

import com.doctor.core.BasePage;
import com.doctor.core.TestBase;
import com.doctor.pages.HomePage;
import com.doctor.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @BeforeMethod
    public void preCondition() {
        new HomePage(app.driver, app.wait).getLoginPage();
        new LoginPage(app.driver, app. wait).isAnmeldenButtonIsPresent();

    }

    @Test
    public void loginPositiveTest() {
        new LoginPage(app.driver, app.wait)
                .enterPersonalData("exampleEmail@gmail.com", "Qwery123456")
                .isAnmeldenButtonIsPresent()
                .clickAnmeldenButton();
       Assert.assertTrue(new BasePage(app.driver, app.wait).isUserLoggedIn(), "User is not logged in"); //реализовать на BasePage
    }



}
