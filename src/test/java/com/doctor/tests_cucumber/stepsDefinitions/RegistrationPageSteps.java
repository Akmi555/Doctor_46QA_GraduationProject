package com.doctor.tests_cucumber.stepsDefinitions;


import com.doctor.core.TestBase;

import com.doctor.pages.RegistrationPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
;

public class RegistrationPageSteps extends TestBase {

    @Then("Check that the Konto erstellen page title is displayed")
    public void checkKontoErstellenPageTitleDisplayed() {
        Assert.assertTrue(new RegistrationPage(app.driver, app.wait).isKontoErstellenPageTitlePresent());
    }

    @And("The user enters valid details")
    public void userEntersValidDetails() {
        new RegistrationPage(app.driver, app.wait).enterPatienDetails("Milka","Katze","max.mustermann@test.de","0123456789","Password!1");
    }


    @And("The user clicks the Weiter button")
    public void userClickWeiter() {
        //кликаем на кнопку
        new RegistrationPage(app.driver, app.wait).clickWeiterLink();
    }


}
