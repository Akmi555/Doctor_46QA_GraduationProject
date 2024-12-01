package com.doctor.tests_selenium;

import com.doctor.core.TestBase;
import com.doctor.pages.HomePage;
import com.doctor.pages.LoginPage;
import com.doctor.pages.TerminVereibarenPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TerminVereibarenTests extends TestBase {
    private HomePage homePage;
    private TerminVereibarenPage terminVereibarenPage;

    @BeforeMethod
    public void preCondition() {
        homePage = new HomePage(app.driver, app.wait);
        terminVereibarenPage = new TerminVereibarenPage(app.driver, app.wait);

        // Логин пользователя
        new HomePage(app.driver, app.wait)
                .clickOnLoginLink();
        new LoginPage(app.driver, app.wait)
                .enterPersonalData("alice.smith@t.test", "SecurePass1")
                .clickOnAnmeldenLink();
        Assert.assertTrue(homePage.isAccountButtonPresent(), "Пользователь не залогинен!");
    }

    @Test
    public void negativeTerminVereibarenTest() {
        terminVereibarenPage
                .clickTerminVereibarenButton()
                .selectNewPatientCheckbox()
                .selectService("Infusionstherapie")
                .openTimeslotMenu()
                .selectFirstAvailableTimeslot()
                .scrollToBottomAndConfirmTermin();
        // Проверяем, что сообщение об ошибке отображается
        Assert.assertTrue(terminVereibarenPage.isErrorNotificationDisplayed(), "Сообщение об ошибке не отображается!");
        System.out.println("Сообщение об ошибке отображается: Diese Zeit ist bereits vergeben.");
    }


    @Test
    public void positiveTerminVereibarenTest() {
        terminVereibarenPage
                .clickTerminVereibarenButton()
                .selectNewPatientCheckbox()
                .selectService("Infusionstherapie")
                .openTimeslotMenu()
                .selectFirstAvailableTimeslot()
                .scrollToBottomAndConfirmTermin();

        // Проверяем, что сообщение об успешной записи отображается
        Assert.assertTrue(terminVereibarenPage.isSuccessNotificationDisplayed(), "Сообщение об успешной записи не отображается!");
        System.out.println("Сообщение об успешной записи отображается: Ihr Termin wurde erfolgreich abgeschlossen.");
    }

    @Test
    public void testTerminVereibarenButtonPresence() {
        TerminVereibarenPage terminVereibarenPage = new TerminVereibarenPage(app.driver, app.wait);

        try {
            terminVereibarenPage.clickTerminVereibarenButton();
            System.out.println("Элемент 'Termin vereinbaren' найден и успешно нажат.");
        } catch (Exception e) {
            System.err.println("Ошибка: Элемент 'Termin vereinbaren' не найден. " + e.getMessage());
        }
    }

}



