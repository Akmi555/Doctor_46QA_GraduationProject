package com.doctor.pages;

import com.doctor.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(text(),'Anmelden')]")
    WebElement anmeldenButton;

    public LoginPage isAnmeldenButtonIsPresent() {
        isElementPresent(anmeldenButton);
        return this;
    }

    public HomePage clickAnmeldenButton() {
        click(anmeldenButton);
        return new HomePage(driver, wait);
    }

    @FindBy(xpath = "//input[@type='email']")
    WebElement emailInput;

    @FindBy(xpath = "//input[@type='password']")
    WebElement passwordInput;

    public LoginPage enterPersonalData(String email, String password) {
        type(emailInput, email);
        type(passwordInput, password);
        return this;
    }

    @FindBy(xpath = "//h1[contains(text(),'Willkommen zurück')]")
    WebElement loginPageTitle;

    public boolean isLoginPageTitlePresent() {
        return isElementPresent(loginPageTitle);
    }


    public LoginPage enterPazientData(String email, String password) {
        type(emailInput, email);
        type(passwordInput, password);
        return this;
    }


    @FindBy(xpath = "//button[contains(@class,'btn btn-primary')]")
    WebElement anmeldenLink;

    public LoginPage clickOnAnmeldenLink() {
        click(anmeldenLink);
        return new LoginPage(driver, wait);
    }

    @FindBy(xpath = "//a[contains(text(),'Konto erstellen')]")
    WebElement kontoErstellen;

    public LoginPage clickKontoErstellen() {
        click(kontoErstellen);
        return new LoginPage(driver, wait);
    }
    public RegistrationPage navigateToRegistrationPage() {
        click(kontoErstellen);
        return new RegistrationPage(driver, wait);
    }

    @FindBy(xpath = "//input[@id='isPersistent']")
    WebElement angemeldet;

    public LoginPage clickAngemeldet() {
        click(angemeldet);
        return this;
    }

}
