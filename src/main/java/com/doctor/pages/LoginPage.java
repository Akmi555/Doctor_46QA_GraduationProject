package com.doctor.pages;

import com.doctor.core.BasePage;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;



public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
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

    @FindBy(xpath = "//div[contains(text(), 'Falsche Daten')]")
    WebElement falscheDatenNotification;

    public boolean isFalscheDatenNotificationPresent() {
        try {
            // Ожидание появления элемента
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Falsche Daten')]")));

            // Проверка текста элемента
            String actualText = falscheDatenNotification.getText();
            Assert.assertTrue(actualText.contains("Falsche Daten"), "Текст уведомления не совпадает!");

            // Ожидание исчезновения элемента
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(), 'Falsche Daten')]")));

            return true;
        } catch (Exception e) {
            System.err.println("Ошибка: уведомление 'Falsche Daten' не обработано. Причина: " + e.getMessage());
            return false;
        }
//
    }


    @FindBy(xpath = "//button[contains(text(),'Anmelden')]")
    WebElement anmeldenLink;

    public LoginPage isAnmeldenButtonIsPresent() {
        isElementPresent(anmeldenLink);
        return this;
    }

    public LoginPage clickOnAnmeldenLink() {
        click(anmeldenLink);
        return this;
    }


}


//WebElement inputField = driver.findElement(By.cssSelector("селектор_поля"));
//String validationMessage = (String) jsExecutor.executeScript(
//    "return arguments[0].validationMessage;", inputField
//);
//System.out.println("Validation message: " + validationMessage);
