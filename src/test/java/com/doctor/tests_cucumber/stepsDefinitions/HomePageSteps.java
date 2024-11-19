package com.doctor.tests_cucumber.stepsDefinitions;


import com.doctor.core.TestBase;
import com.doctor.pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class HomePageSteps extends TestBase {

    @Given("The user launches the browser")
    public void userLaunchBrowser() {
        setUp();
    }

    @When("The user opens the gesundheitspraxis home page")
    public void userOpensGesundheitspraxisHomePage() {
        new HomePage(app.driver, app.wait).openHomePage();
    }

    @Then("Check that the home page title is displayed")
    public void checkHomePageTitleDisplayed() {
        Assert.assertTrue(new HomePage(app.driver, app.wait).isHomePageTitlePresent());
    }

    @After
    @And("The user closes the browser")
    public void userClosesTheBrowser() {
        app.driver.quit();
    }

    @Then("The user clicks on the Login button")
    public void clickLoginButtonHomePage() {
        new HomePage(app.driver, app.wait).clickOnLoginLink();
    }


    @Then("The user clicks on the Infusionstherapie")
    public void clicksInfusionstherapie() {
        new HomePage(app.driver, app.wait).clickInfusionstherapieLink();
    }

    @Then("Check that the title Infusionstherapie is displayed")
    public void checkTitleInfusionstherapieIsDisplayed() {
        Assert.assertTrue(new HomePage(app.driver, app.wait).isInfusionstherapieTitlePresent());
    }

    @Then("The user clicks on the Portfolio")
    public void userClicksOnPortfolio() {
        new HomePage(app.driver, app.wait).clikPortfolio();
    }

    @Then("Check that the title Meine Leistungen is displayed")
    public void checkTitleMeineLeistungenIsDisplayed() {
        Assert.assertTrue(new HomePage(app.driver, app.wait).isMeineLeistungenTitle());
    }


    @Then("The user clicks on the Neuraltherapie")
    public void userClicksOnNeuraltherapie() {
        new HomePage(app.driver, app.wait).clickNeuraltherapie();
    }

    @Then("Check that the title Neuraltherapie is displayed")
    public void checkTitleNeuraltherapieIsDisplayed() {
        Assert.assertTrue(new HomePage(app.driver, app.wait).isNeuraltherapieTitlePresent());
    }

    @Then("The user click on the Schröpftherapie")
    public void userClickOnSchropftherapie() {
        new HomePage(app.driver, app.wait).clickSchropftherapie();
    }
}
