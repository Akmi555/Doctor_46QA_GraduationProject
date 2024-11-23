package com.doctor.pages;


import com.doctor.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
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
    WebElement password;

    public RegistrationPage enterPatienDetails(String vorName, String nachName, String Email, String telefonNumber, String passWord) {
        type(vorname,vorName);
        type(nachname,nachName);
        type(email,Email);
        type(telefonnummer, telefonNumber);
        type(password,passWord);
        return this;
    }
    @FindBy(xpath = "//button[contains(text(),'Weiter')]")
    WebElement weiterButton;

    public RegistrationPage clickWeiterLink() {
        click(weiterButton);
        return new RegistrationPage(driver,wait);
    }
    @FindBy(xpath = "//button[contains(text(),'Account')]")
    WebElement accountButton;

    /**
     * Проверяет успешное перенаправление на HomePage
     * @return true, если кнопка "Account" отображается
     */
    public boolean redirectOnHomePage() {
        return isElementPresent(accountButton);
    }
    @FindBy(xpath = "//footer[@class='footer py-4']/following-sibling::div[1]")
    public WebElement toastifyMessage;
    public WebElement getToastieMessage() {
        return toastifyMessage;
    }
    // Проверка наличия элемента Toastify
    public boolean isToastifyMessageDisplayed() {
        return isElementPresent(toastifyMessage);
    }

    public boolean isRegistrationSuccessful() {
        // Проверяем, что сообщение Toastify отсутствует
        if (isElementPresent(toastifyMessage)) {
            return false;
        }

        // Проверяем, что пользователь находится на HomePage
        return redirectOnHomePage();
    }

    /**
     * Проверяет успешность регистрации
     * @return true, если регистрация прошла успешно; false, если возникла ошибка
     */

}
