package com.doctor;

import com.doctor.core.TestBase;
import com.doctor.pages.HomePage;
import com.doctor.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @BeforeMethod
    public void preCondition() {
        new HomePage(driver, wait).getLoginPage();
        new LoginPage(driver, wait).isAnmeldenButtonIsPresent();

    }

    @Test
    public void loginPositiveTest() {
        new LoginPage(driver, wait)
                .enterPersonalData("exampleEmail@gmail.com", "Qwery123456")
                .clickLoginButton();
//                .verifyUserIsLoggedIn(new com.doctor.pages.UserData()
//                        .setEmail("Y0vMv@example.com")
//                        .setPassword("123456"));

    }

}
