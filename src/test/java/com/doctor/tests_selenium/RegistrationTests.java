package com.doctor.tests_selenium;

import com.doctor.core.TestBase;
import com.doctor.model.User;
import com.doctor.pages.HomePage;
import com.doctor.pages.LoginPage;
import com.doctor.pages.RegistrationPage;
import com.doctor.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {
    @BeforeMethod
    public void preCondition() {
        new HomePage(app.driver, app.wait).getLoginPage();
        new LoginPage(app.driver, app.wait).isAnmeldenButtonIsPresent();

    }

    @Test(dataProvider = "userRegistrationData", dataProviderClass = DataProviders.class)
    public void registrationTest(String vorName, String nachName, String email, String telefonnummer, String passwort) {
        RegistrationPage registrationPage = new RegistrationPage(app.getDriver(), app.wait);
        registrationPage
                .clickKontoErstellenButton()
                .enterPatienDetails(vorName, nachName, email, telefonnummer, passwort)
                .clickWeiterLink();

        if (email.endsWith(".test") && passwort.length() >= 8) {
            // Проверка успешной регистрации
            Assert.assertTrue(registrationPage.isRegistrationSuccessful(),
                    "Registration should be successful for valid data");
            Assert.assertTrue(registrationPage.redirectOnHomePage(),
                    "User should be redirected to HomePage with 'Account' button visible");
        } else {
            // Проверка неуспешной регистрации
            Assert.assertFalse(registrationPage.isRegistrationSuccessful(),
                    "Registration should fail for invalid data");

            // Проверяем, что Toastify сообщение отображается
            Assert.assertTrue(registrationPage.isElementPresent(registrationPage.toastifyMessage),
                    "Toastify message should appear for invalid registration");
            System.out.println("Toastify Message: " + registrationPage.getToastieMessage());
        }
    }

    //    @Test(dataProvider = "userRegistrationFromCSV", dataProviderClass = DataProviders.class)
//    public void registrationFromCSVTest(String vorName, String nachName, String email, String telefonnummer, String passwort) {
//        String newRandomEmail= System.currentTimeMillis()+email;
//        System.out.println(newRandomEmail);
//        RegistrationPage registrationPage = new RegistrationPage(app.getDriver(), app.wait);
//        registrationPage
//                .clickKontoErstellenButton()
//                .enterPatienDetails(vorName, nachName, newRandomEmail, telefonnummer, passwort)
//                .clickWeiterLink();
//
//        if (newRandomEmail.endsWith(".test") && passwort.length() >= 8) {
//            // Проверка успешной регистрации
//            Assert.assertTrue(registrationPage.isRegistrationSuccessful(),
//                    "Registration should be successful for valid data from CSV");
//            Assert.assertTrue(registrationPage.redirectOnHomePage(),
//                    "User should be redirected to HomePage with 'Account' button visible");
//        } else {
//            // Проверка неуспешной регистрации
//            Assert.assertFalse(registrationPage.isRegistrationSuccessful(),
//                    "Registration should fail for invalid data from CSV");
//
//            // Проверяем, что Toastify сообщение отображается
//            Assert.assertTrue(registrationPage.isElementPresent(registrationPage.toastifyMessage),
//                    "Toastify message should appear for invalid registration");
//            System.out.println("Toastify Message: " + registrationPage.getToastieMessage());
//        }
//    }
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
            Assert.assertTrue(registrationPage.isRegistrationSuccessful(),
                    "Registration should be successful for valid data from CSV");
            Assert.assertTrue(registrationPage.redirectOnHomePage(),
                    "User should be redirected to HomePage with 'Account' button visible");
        } else {
            Assert.assertFalse(registrationPage.isRegistrationSuccessful(),
                    "Registration should fail for invalid data from CSV");
            Assert.assertTrue(registrationPage.isElementPresent(registrationPage.toastifyMessage),
                    "Toastify message should appear for invalid registration");
            System.out.println("Toastify Message: " + registrationPage.getToastieMessage());
        }
    }
//    @Test
//    public void registerNewRandomUser() {
//        String newRandomEmail = System.currentTimeMillis() + "_johndoe@t.test";
//        new RegistrationPage(app.driver, app.wait).newRandomUser(newRandomEmail);
//        new ProfilePage(app.driver, app.wait).updateUser("name","voreName","Telephone");
//        new ProfilePage(app.driver, app.wait).verifyUpdateResult("name","voreName","Telephone");



}
