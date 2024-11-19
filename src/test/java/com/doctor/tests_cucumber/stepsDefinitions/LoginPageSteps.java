package com.doctor.tests_cucumber.stepsDefinitions;


import com.doctor.core.TestBase;
import com.doctor.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;


public class LoginPageSteps extends TestBase {
    @Then("Check that the Login page title is displayed")
    public void checkLoginPageTitleDisplayed() {
        Assert.assertTrue(new LoginPage(app.driver, app.wait).isLoginPageTitlePresent());
    }

    @And("The user enters valid data")
    public void userEntersValidData() {
        new LoginPage(app.driver, app.wait).enterPazientData("test_qa@gmail.com", "Password@1");
    }

    @And("The user clicks on the Anmelden button")
    public void userClicksAnmeldenButton() {
        new LoginPage(app.driver, app.wait).clickOnAnmeldenLink();
    }

    @Then("The user clicks on the Kontoerstellen button")
    public void userClickKontoErstellen() {
        new LoginPage(app.driver, app.wait).clickKontoErstellen();
    }

    @And("The user enters Angemeldet bleiben")
    public void userEntersAngemeldetBleiben() {
        new LoginPage(app.driver, app.wait).clickAngemeldet();
    }
}
