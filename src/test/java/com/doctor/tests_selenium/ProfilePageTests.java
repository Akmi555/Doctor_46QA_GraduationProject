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
        new LoginPage(app.driver, app.wait).isAnmeldenButtonIsPresent();
    }

    @Test(dataProvider = "userUpdateData", dataProviderClass = DataProviders.class)
    public void updateUserTest(String name, String lastName , String phone, String passwort) {
        ProfilePage profilePage = new ProfilePage(app.getDriver(), app.wait);
        profilePage
                .updateUser(name, lastName, phone, passwort )
                .verifyUpdateResult();

        // Verify the update result
        Assert.assertTrue(profilePage.verifyUpdateResult(), "User update should be successful with valid data");
    }

    @Test(dataProvider = "userUpdateFromCSV", dataProviderClass = DataProviders.class)
    public void updateUserFromCSVTest(User user) {
        ProfilePage profilePage = new ProfilePage(app.getDriver(), app.wait);
        profilePage
                .updateUser(user.getName(), user.getLastName(), user.getPhone(), user.getPassword())
                .verifyUpdateResult();

        if (user.getEmail().contains(".test")) {
            Assert.assertTrue(profilePage.verifyUpdateResult(), "User update should be successful for valid data from CSV");
        } else {
            Assert.assertFalse(profilePage.verifyUpdateResult(), "User update should fail for invalid data from CSV");
        }
    }

    @Test
    public void updateNewRandomUser() {
        String newRandomEmail = System.currentTimeMillis() + "_updated@t.test";
        ProfilePage profilePage = new ProfilePage(app.getDriver(), app.wait);
        User user = new User().setName("newRandomUser").setEmail(newRandomEmail);
        profilePage
                .updateUser(user.getName(), user.getEmail(), user.getPhone(), user.getPassword())
                .verifyUpdateResult();

        Assert.assertTrue(profilePage.verifyUpdateResult(), "User update should be successful for a randomly generated user");
    }
}
