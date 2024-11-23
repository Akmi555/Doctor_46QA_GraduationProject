package com.doctor.tests_selenium;
import com.doctor.model.User;
import com.doctor.core.TestBase;
import com.doctor.pages.HomePage;
import com.doctor.pages.LoginPage;
import com.doctor.pages.ProfilePage;
import com.doctor.utils.DataProviders;
import org.testng.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProfilePageTests extends TestBase {
    @BeforeMethod
    public void preCondition() {
        new HomePage(app.driver, app.wait).getLoginPage();
        LoginPage loginPage = new LoginPage(app.driver, app.wait);
        new LoginPage(app.driver, app.wait).isAnmeldenButtonIsPresent();
        loginPage
                .enterPersonalData("alice.smith@t.test", "SecurePass1")
                .clickAnmeldenButton();
        ProfilePage profilePage = new ProfilePage(app.driver, app.wait);
        profilePage.clickAccountButton().clickProfileLink();
    }

    @Test(dataProvider = "userUpdateData", dataProviderClass = DataProviders.class)
    public void updateUserTest(String vorName, String nachName,  String telefonnummer) {
        ProfilePage profilePage = new ProfilePage(app.getDriver(), app.wait);
        profilePage
                .updateUser(vorName, nachName, telefonnummer)
                .verifyUpdateResult();

        // Verify the update result
        Assert.assertTrue(profilePage.verifyUpdateResult(), "User update should be successful with valid data");
    }

    @Test(dataProvider = "userUpdateFromCSV", dataProviderClass = DataProviders.class)
    public void updateUserFromCSVTest(User user) {
        ProfilePage profilePage = new ProfilePage(app.getDriver(), app.wait);
        profilePage
                .updateUser(user.getName(), user.getLastName(), user.getPhone())
                .verifyUpdateResult();
            Assert.assertTrue(profilePage.verifyUpdateResult(), "User update should be successful for valid data from CSV");

    }

    @Test
    public void updateNewRandomUser() {
        String newRandomName = "user"+System.currentTimeMillis();
        ProfilePage profilePage = new ProfilePage(app.getDriver(), app.wait);
        User user = new User().setName("newRandomUser").setPhone("1234567890");
        profilePage
                .updateUser(user.getName(), user.getLastName(), user.getPhone())
                .verifyUpdateResult();

        Assert.assertTrue(profilePage.verifyUpdateResult(), "User update should be successful for a randomly generated user");
    }
}
