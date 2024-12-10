package com.doctor.pages;

import com.doctor.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(),'Team')]")
    WebElement teamButton;


    public boolean isHomeComponentPresent() {
        System.out.println("Look for 'HomeComponent' on the home page");
        return teamButton.isDisplayed();
    }

    @FindBy(xpath = "//a[contains(text(),'Login')]")
    WebElement loginLink;

    public LoginPage getLoginPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        click(loginLink);
        return new LoginPage(driver, wait);
    }

    //    public boolean isTeamButtonPresent() {
//        return teamButton.isDisplayed();
//    }
    public void openHomePage() {
        driver.get("https://gesundheitspraxis-wertvoll.de");
    }

    @FindBy(xpath = " //h1[contains(text(),'Gesundheitspraxis Wertvoll')]")
    WebElement homePageTitle;

    public boolean isHomePageTitlePresent() {
        return isElementPresent(homePageTitle);
    }

    public LoginPage clickOnLoginLink() {
        click(loginLink);
        return new LoginPage(driver, wait);
    }

    // @FindBy(xpath = "(//a[@class='portfolio-link']//div)[1]")

    @FindBy(xpath = "//div[text()='Infusionstherapie']/preceding::a[1]")
    WebElement infusionstherapieLink;

    public HomePage clickInfusionstherapieLink() {
        click(infusionstherapieLink);
        return this;
    }

    @FindBy(xpath = "//h2[contains(text(),'Infusionstherapie')]")
    WebElement infusionstherapieTitle;

    public boolean isInfusionstherapieTitlePresent() {
        wait.until(ExpectedConditions.visibilityOf(infusionstherapieTitle));
        return infusionstherapieTitle.isDisplayed();
        // return isElementPresent(infusionstherapieTitle);
    }

    @FindBy(xpath = "//a[contains(text(),'Portfolio')]")
    WebElement portfolioLink;

    public HomePage clikPortfolio() {
        click(portfolioLink);
        return this;
    }

    @FindBy(xpath = "//h2[contains(text(),'Meine Leistungen')]")
    WebElement meineLeistungenTitle;

    public boolean isMeineLeistungenTitle() {
        return isElementPresent(meineLeistungenTitle);
    }

    @FindBy(xpath = "//div[text()='Neuraltherapie']/preceding::a[1]")
    WebElement neuraltherapieLink;

    public void clickNeuraltherapie() {
        click(neuraltherapieLink);
    }

    @FindBy(xpath = "//h2[contains(text(),'Neuraltherapie')]")
    WebElement neuraltherapieTitle;

    public boolean isNeuraltherapieTitlePresent() {
        wait.until(ExpectedConditions.visibilityOf(neuraltherapieTitle));
        return neuraltherapieTitle.isDisplayed();
    }

    @FindBy(xpath = "(//div[@class='portfolio-hover'])[3]")
    WebElement schropftherapieLink;

    public HomePage clickSchropftherapie() {
        click(schropftherapieLink);
        return new HomePage(driver, wait);
    }

    @FindBy(xpath = "//a[contains(text(),'Profile')]")
    WebElement profileLink;

  public ProfilePage clickProfileLink() {
      click(accountButton);
      click(profileLink);
      return new ProfilePage(driver, wait);

  }

    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    WebElement logoutButton;

    public HomePage clickLogoutButton() {
        click(logoutButton);
        return new HomePage(driver, wait);
    }
    @FindBy(xpath = "//a[contains(text(),'Account')]")
    static WebElement accountButton;

    public static LoginPage clickAccountButton() {
        click(accountButton);
        return new LoginPage(driver, wait);
    }
    public boolean isAccountButtonPresent() {
        return isElementPresent(accountButton);
    }

    public void getHomePage() {
        driver.get("https://gesundheitspraxis-wertvoll.de");
    }
}











