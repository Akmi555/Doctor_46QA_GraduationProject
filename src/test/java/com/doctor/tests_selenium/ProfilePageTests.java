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
                .updateUser(user.getName(), user.getLastName(), user.getPhone())
                .verifyUpdateResult();

        Assert.assertTrue(profilePage.verifyUpdateResult(), "User update should be successful for a randomly generated user");
    }

    @AfterMethod
    public void postCondition() {
        new HomePage(app.driver, app.wait).clickAccountButton();
        new HomePage(app.driver, app.wait).clickLogoutButton();


    }
}
