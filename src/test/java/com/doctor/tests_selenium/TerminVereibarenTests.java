package com.doctor.tests_selenium;
import com.doctor.core.TestBaseUI;
import com.doctor.pages.HomePage;
import com.doctor.pages.LoginPage;
import com.doctor.pages.TerminVereibarenPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;




public class TerminVereibarenTests extends TestBaseUI {
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
//               .enterPersonalData("smith@1t.test", "Qwery123")
               .enterPersonalData("alice.smith@t.test", "SecurePass1")
                .clickOnAnmeldenLink();
        Assert.assertTrue(homePage.isAccountButtonPresent(), "Пользователь не залогинен!");
    }

    @Test
    public void bookAndCancelTerminTest() {
        // Шаг 1: Нажать на кнопку Termin vereinbaren
        terminVereibarenPage.clickTerminVereibarenButton()
                // Шаг 2: Нажать на меню терапий
                .selectTherapyMenu()
                // Шаг 3: Выбрать Infusionstherapie
                .selectTherapyById("1") // ID для Infusionstherapie
                // Шаг 4: Нажать на дату 20.12.2024
                .selectDateByTarget("#aTimeslot6")
                // Шаг 5: Выбрать таймслот 16:30
                .selectTimeslotById("6at4")
                // Шаг 6: Прокрутить вниз и нажать Termin vereinbaren
                .scrollToBottomAndConfirmTermin();
        // Шаг 7: Проверить успешность бронирования
        Assert.assertTrue(terminVereibarenPage.isSuccessNotificationDisplayed(),
                "Уведомление об успешной регистрации не отображается!");
    }

    @AfterMethod
    public void  postCondition() {
        // Шаг 1: Закрыть всплывающее сообщение об успешной регистрации
        terminVereibarenPage
                .closeSuccessNotification();
//         Шаг 2: Нажать на кнопку аккаунта
        HomePage
                .clickAccountButton();
        // Шаг 3: Нажать на термин в меню аккаунта
        terminVereibarenPage
                .selectTerminFromAccountMenu()
                // Шаг 4: Удалить термин
                .deleteFirstTermin()
                .clickConfirmDeleteButton();
        // Шаг 5: Проверить успешное удаление
        Assert.assertTrue(terminVereibarenPage.isDeleteSuccessNotificationDisplayed(),
                "Уведомление об успешном удалении не отображается!");
    }
}




