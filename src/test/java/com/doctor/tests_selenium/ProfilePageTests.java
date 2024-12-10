package com.doctor.tests_selenium;
import com.doctor.model.User;
import com.doctor.core.TestBase;
import com.doctor.pages.HomePage;
import com.doctor.pages.LoginPage;
import com.doctor.pages.ProfilePage;
import com.doctor.utils.DataProviders;
import org.testng.Assert;

import org.testng.annotations.*;

public class ProfilePageTests extends TestBase {
    @BeforeMethod
    public void preCondition() {
        new HomePage(app.driver, app.wait).getLoginPage();
        LoginPage loginPage = new LoginPage(app.driver, app.wait);
        loginPage

                .enterPersonalData("alice.smith@t.test", "SecurePass1")
                .clickOnAnmeldenLink();
        HomePage homePage = new HomePage(app.driver, app.wait);
        homePage
                .clickProfileLink();

    }

    @Test(dataProvider = "userUpdateData", dataProviderClass = DataProviders.class)
    public void updateUserTest(String vorName, String nachName, String telefonnummer) {
        String updateVorName = vorName + System.currentTimeMillis();
        ProfilePage profilePage = new ProfilePage(app.getDriver(), app.wait);
        profilePage
                .updateUser(updateVorName, nachName, telefonnummer)
                .verifyUpdateResult();

        // Verify the update result
        Assert.assertTrue(profilePage.verifyUpdateResult(), "User update should be successful with valid data");
    }

    @Test(dataProvider = "userUpdateFromCSV", dataProviderClass = DataProviders.class)
    public void updateUserFromCSVTest(User user) {
        String updateName = user.getName() + System.currentTimeMillis();
        System.out.println(updateName);
        ProfilePage profilePage = new ProfilePage(app.getDriver(), app.wait);
        profilePage
                .updateUser(updateName, user.getLastName(), user.getPhone())
                .verifyUpdateResult();
        Assert.assertTrue(profilePage.verifyUpdateResult(), "User update should be successful for valid data from CSV");

    }

    @Test
    public void updateNewRandomUser() {
        String newRandomName = "Tom" + System.currentTimeMillis();
        System.out.println(newRandomName);
        ProfilePage profilePage = new ProfilePage(app.getDriver(), app.wait);
        User user = new User().setName(newRandomName).setPhone("1234567890");
        profilePage
                .updateUser(user.getName(), user.getLastName(),user.getPhone())
                .verifyUpdateResult();

        Assert.assertTrue(profilePage.verifyUpdateResult(), "User update should be successful for a randomly generated user");
    }
    @Test
    public void updateUserWithEmptyVornameTest() {
        ProfilePage profilePage = new ProfilePage(app.getDriver(), app.wait);

        // Оставляем поле Vorname пустым
        profilePage
                .updateUser("", "Smith", "1234567890");

        // Проверяем сообщение о валидации
        String validationMessage = profilePage.getValidationMessage(profilePage.vorname);
        Assert.assertTrue(
                validationMessage.matches(".*Заполните это поле.*|.*Please fill out this field.*"),
                "Сообщение валидации для Vorname не соответствует ожидаемому!"
        );
        System.out.println("Сообщение валидации для Vorname: " + validationMessage);
    }

    @Test
    public void updateUserWithEmptyNachnameTest() {
        ProfilePage profilePage = new ProfilePage(app.getDriver(), app.wait);

        // Оставляем поле Nachname пустым
        profilePage
                .updateUser("Alice", "", "1234567890");

        // Проверяем сообщение о валидации
        String validationMessage = profilePage.getValidationMessage(profilePage.nachname);
        Assert.assertTrue(
                validationMessage.matches(".*Заполните это поле.*|.*Please fill out this field.*"),
                "Сообщение валидации для Vorname не соответствует ожидаемому!"
        );
        System.out.println("Сообщение валидации для Nachname: " + validationMessage);
    }


    @AfterMethod(enabled = false)
    public void postCondition() {
        try {
            // Проверяем, отображается ли сообщение об успешном обновлении
            ProfilePage profilePage = new ProfilePage(app.driver, app.wait);
            if (profilePage.verifyUpdateResult()) {
                // Закрываем всплывающее окно, если оно есть
                profilePage.clickCloseupdateSuccessMessageButton();
                System.out.println("Всплывающее окно успешно закрыто.");
            } else {
                System.out.println("Сообщение об успешном обновлении отсутствует, пропускаем закрытие всплывающего окна.");
            }
        } catch (Exception e) {
            System.err.println("Ошибка при проверке или закрытии всплывающего окна: " + e.getMessage());
        }

        try {
            // Проверяем, отображается ли кнопка аккаунта (признак авторизации)
            HomePage homePage = new HomePage(app.driver, app.wait);
            if (homePage.isAccountButtonPresent()) {
                HomePage.clickAccountButton();
                homePage.clickLogoutButton();
                System.out.println("Пользователь разлогинился.");
            } else {
                System.out.println("Пользователь не залогинен, разлогинивание не требуется.");
            }
        } catch (Exception e) {
            System.err.println("Ошибка при разлогинивании: " + e.getMessage());
        }

    }

}

