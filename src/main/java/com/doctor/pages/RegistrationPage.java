package com.doctor.pages;


import com.doctor.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(),'Konto erstellen')]")
    WebElement kontoErstellen;

    public RegistrationPage clickKontoErstellenButton() {
        click(kontoErstellen);
        return new RegistrationPage(driver,wait);
    }

    @FindBy(xpath = "//h1[contains(text(),'Konto erstellen')]")
    WebElement kontoErstellenPageTitle;
    public boolean isKontoErstellenPageTitlePresent() {
        return isElementPresent(kontoErstellenPageTitle);
    }

    @FindBy(xpath = "(//div[contains(@class,'col-sm-6 mb-3')]//input)[1]")
    WebElement vorname;

    @FindBy(xpath = "(//div[@class='col-sm-6']//input)[1]")
    WebElement nachname;

    @FindBy(xpath = "(//div[contains(@class,'col-sm-6 mb-3')]//input)[2]")
    WebElement email;

    @FindBy(xpath = "(//div[@class='col-sm-6']//input)[2]")
    WebElement telefonnummer;

    @FindBy(xpath = "//div[@class='form-group']//input[1]")
    WebElement passwort;

    public RegistrationPage enterPatienDetails(String vorName, String nachName, String Email, String telefonNumber, String password) {
        type(vorname,vorName);
        type(nachname,nachName);
        type(email,Email);
        type(telefonnummer, telefonNumber);
        type(passwort,password);
        return this;
    }
    @FindBy(xpath = "//button[contains(text(),'Weiter')]")
    WebElement weiterButton;

    public RegistrationPage clickWeiterLink() {
        click(weiterButton);
        return new RegistrationPage(driver,wait);
    }
}
