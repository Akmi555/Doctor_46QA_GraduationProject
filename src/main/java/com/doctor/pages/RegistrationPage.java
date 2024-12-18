package com.doctor.pages;


import com.doctor.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @BeforeMethod
    public static RegistrationPage navigateToRegistrationPage(WebDriver driver, WebDriverWait wait) {
        // Инициализируем HomePage
        HomePage homePage = new HomePage(driver, wait);

        // Получаем LoginPage через HomePage
        LoginPage loginPage = homePage.getLoginPage();

        // Переход на RegistrationPage через LoginPage
        return loginPage.navigateToRegistrationPage();
    }

    @FindBy(xpath = "//a[contains(text(),'Konto erstellen')]")
    WebElement kontoErstellen;

    public RegistrationPage clickKontoErstellenButton() {
        click(kontoErstellen);
        return new RegistrationPage(driver, wait);
    }

    @FindBy(xpath = "//h1[contains(text(),'Konto erstellen')]")
    WebElement kontoErstellenPageTitle;

    public boolean isKontoErstellenPageTitlePresent() {
        return isElementPresent(kontoErstellenPageTitle);
    }

    @FindBy(xpath = "(//input[@placeholder='Vorname'])")
    WebElement vorname;

    @FindBy(xpath = "(//input[@placeholder='Nachname'])")
    WebElement nachname;

    @FindBy(xpath = "(//input[@placeholder='Email'])")
    WebElement email;

    @FindBy(xpath = "(//input[@placeholder='Telefonnummer (optional)'])")
    WebElement telefonnummer;

    @FindBy(xpath = "//input[@placeholder='Passwort']")
    WebElement password;

    public RegistrationPage enterPatienDetails(String vorName, String nachName, String Email, String telefonNumber, String passWord) {
        type(vorname, vorName);
        type(nachname, nachName);
        type(email, Email);
        type(telefonnummer, telefonNumber);
        type(password, passWord);
        return this;
    }

    @FindBy(xpath = "//button[contains(text(),'Weiter')]")
    WebElement weiterButton;

    public RegistrationPage clickWeiterLink() {
        click(weiterButton);
        return new RegistrationPage(driver, wait);
    }

    @FindBy(xpath = "//div[contains(text(),\"Username 'alice.smith@t.test' is already taken\")]")
    WebElement userNameAlreadyTakenNotification;

    public boolean isUserNameAlreadyTakenNotificationPresent() {
        try {
            // Ожидание появления элемента
            wait.until(ExpectedConditions.visibilityOf(userNameAlreadyTakenNotification));

            // Проверка текста элемента
            String actualText = userNameAlreadyTakenNotification.getText();
            Assert.assertTrue(actualText.contains("Username 'alice.smith@t.test' is already taken."),
                    "Текст уведомления не совпадает! Ожидается: 'Username 'alice.smith@t.test' is already taken.'");

            // Уведомление найдено
            return true;
        } catch (TimeoutException e) {
            System.err.println("Ошибка: уведомление 'Username 'alice.smith@t.test' is already taken.' не появилось вовремя.");
            return false;
        } catch (Exception e) {
            System.err.println("Ошибка: уведомление 'Username 'alice.smith@t.test' is уже не обработано. Причина: " + e.getMessage());
            return false;
        }
    }

    @FindBy(xpath = "//div[contains(@class,'Toastify__toast-icon Toastify--animate-icon')]/following-sibling::div[1]")
    WebElement errorRegistrationMessage;

    @FindBy(xpath = "//button[@class='Toastify__close-button Toastify__close-button--light']")
    WebElement closeRegistrationMessageButton;

    public boolean isRegistrationErrorMessagePresent() {
return isElementPresent(errorRegistrationMessage);
    }

    public void closeRegistrationErrorMessage() {
        click(closeRegistrationMessageButton);
    }
}


    /**
     * Проверяет успешное перенаправление на HomePage
     * @return true, если кнопка "Account" отображается
     */


    // Проверка наличия элемента Toastify

//    public HomePage redirectOnHomePage() {
//        // Проверяем, что пользователь находится на HomePage
//        return new HomePage(driver, wait);
//    }

    /**
     * Проверяет успешность регистрации
     * @return true, если регистрация прошла успешно; false, если возникла ошибка
     */


