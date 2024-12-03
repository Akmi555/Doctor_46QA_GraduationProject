package com.doctor.tests_selenium;

import com.doctor.core.TestBase;
import com.doctor.pages.HomePage;
import com.doctor.pages.LoginPage;
import com.doctor.pages.TerminVereibarenPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


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
    public void bookAndCancelTerminTest() {
        // Шаг 1: Нажать на кнопку Termin vereinbaren
        terminVereibarenPage.clickTerminVereibarenButton()
                // Шаг 2: Нажать на меню терапий
                .selectTherapyMenu()
                // Шаг 3: Выбрать Infusionstherapie
                .selectTherapyById("1") // ID для Infusionstherapie
                // Шаг 4: Нажать на дату 11.12.2024
                .selectDateByTarget("#aTimeslot3")
                // Шаг 5: Выбрать таймслот 11:00
                .selectTimeslotById("3at0")
                // Шаг 6: Прокрутить вниз и нажать Termin vereinbaren
                .scrollToBottomAndConfirmTermin();
        // Шаг 7: Проверить успешность бронирования
        Assert.assertTrue(terminVereibarenPage.isSuccessNotificationDisplayed(),
                "Уведомление об успешной регистрации не отображается!");
    }

    @AfterMethod
    public void postCondition() {
        // Шаг 1: Закрыть всплывающее сообщение об успешной регистрации
        terminVereibarenPage
                .closeSuccessNotification();
        // Шаг 2: Нажать на кнопку аккаунта
        homePage
                .clickAccountButton();
        // Шаг 3: Нажать на термин в меню аккаунта
        terminVereibarenPage
                .selectTerminFromAccountMenu()
                // Шаг 4: Удалить термин
                .deleteFirstTermin();
        // Шаг 5: Проверить успешное удаление
        Assert.assertTrue(terminVereibarenPage.isDeleteSuccessNotificationDisplayed(),
                "Уведомление об успешном удалении не отображается!");
    }
}
//@Test
//public void iterativeTherapyAndTimeslotBookingTest() {
//    // Переход на страницу записи и выбор нового пациента
//    terminVereibarenPage
//            .clickTerminVereibarenButton()
//            .selectNewPatientCheckbox()
//
//            // Запуск процесса перебора терапий, дат и таймслотов
//            .selectDateAndTimeslotForAllTherapies();
//
//    // Проверка, что запись успешно завершена
//    Assert.assertTrue(terminVereibarenPage.isSuccessNotificationDisplayed(),
//            "Не удалось зарегистрироваться на сеанс. Все таймслоты для всех терапий заняты.");
//}
//    @Test
//    public void iterativeDateAndTimeslotBookingTest() {
//        terminVereibarenPage
//                .clickTerminVereibarenButton()
//                .selectNewPatientCheckbox()
//                .clickServiceMenuSelector("Infusionstherapie");
//
//        List<WebElement> dates = terminVereibarenPage.allDates;
//
//        // Проверяем последние две даты
//        for (int dateIndex = dates.size() - 2; dateIndex < dates.size(); dateIndex++) {
//            terminVereibarenPage.selectDateAndTimeslot(dateIndex, 0); // Начинаем с первого таймслота
//
//            // Собираем массив таймслотов для текущей даты
//            List<WebElement> timeslots = terminVereibarenPage.allTimeslots;
//            for (int timeslotIndex = 0; timeslotIndex < timeslots.size(); timeslotIndex++) {
//                terminVereibarenPage.selectDateAndTimeslot(dateIndex, timeslotIndex)
//                        .scrollToBottomAndConfirmTermin();
//
//                // Проверяем успешность или занятость
//                if (terminVereibarenPage.isSuccessNotificationDisplayed()) {
//                    System.out.println("Таймслот с индексом " + timeslotIndex + " на дату " + dateIndex + " занят.");
//                } else if (terminVereibarenPage.isSuccessNotificationDisplayed()) {
//                    System.out.println("Таймслот с индексом " + timeslotIndex + " на дату " + dateIndex + " успешно забронирован.");
//                    return; // Завершаем тест при успешной записи
//                }
//            }
//        }
//    }





//    public void itera() {
//        terminVereibarenPage
//                .clickTerminVereibarenButton()
//                .selectNewPatientCheckbox()
//                .clickServiceMenuSelector("Infusionstherapie")
//                .selectTherapie("Infusionstherapie")
//                .openDaysSlotMenuNegative()
//                .selectFirstAvailableTimeslotNegative()
//                .scrollToBottomAndConfirmTermin();
//        // Проверяем, что сообщение об ошибке отображается
//        Assert.assertTrue(terminVereibarenPage.isErrorNotificationDisplayed(), "Сообщение об ошибке не отображается!");
//        System.out.println("Сообщение об ошибке отображается: Diese Zeit ist bereits vergeben.");
//    }

//
//    @Test
//    public void positiveTerminVereibarenTest() {
//        terminVereibarenPage
//                .clickTerminVereibarenButton()
//                .selectNewPatientCheckbox()
//                .clickServiceMenuSelector("Schröpftherapie")
//                .openDaysSlotMenuPositive()
//                .selectFirstAvailableTimeslotNegative()
//                .scrollToBottomAndConfirmTermin();
//
//        // Проверяем, что сообщение об успешной записи отображается
//        Assert.assertTrue(terminVereibarenPage.isSuccessNotificationDisplayed(), "Сообщение об успешной записи не отображается!");
//        System.out.println("Сообщение об успешной записи отображается: Ihr Termin wurde erfolgreich abgeschlossen.");
//    }
//
//    @Test
//    public void testTerminVereibarenButtonPresence() {
//        TerminVereibarenPage terminVereibarenPage = new TerminVereibarenPage(app.driver, app.wait);
//
//        try {
//            terminVereibarenPage.clickTerminVereibarenButton();
//            System.out.println("Элемент 'Termin vereinbaren' найден и успешно нажат.");
//        } catch (Exception e) {
//            System.err.println("Ошибка: Элемент 'Termin vereinbaren' не найден. " + e.getMessage());
//        }
//    }
//
//}



