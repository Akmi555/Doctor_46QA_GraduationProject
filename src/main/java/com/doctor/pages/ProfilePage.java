package com.doctor.pages;

import com.doctor.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

import java.time.Duration;

public class ProfilePage extends BasePage {


    public ProfilePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "//input[@name='FirstName']")
    WebElement vorname;

    @FindBy(xpath = "(//div[@class='col-sm-6']//input)[1]")
    WebElement nachname;

    @FindBy(xpath = "(//div[contains(@class,'col-sm-6 mb-3')]//input)[2]")
    WebElement email;

    @FindBy(xpath = "(//div[@class='col-sm-6']//input)[2]")
    WebElement telefonnummer;


    @FindBy(xpath = "//button[contains(text(),'Speichern')]")
    WebElement speichernButton;

    @FindBy(xpath = "//div[contains(text(),'Vorgang erfolgreich abgeschlossen')]")
    private WebElement updateSuccessMessage;

    // Method to update user profile information
    public ProfilePage updateUser(String vorName, String nachName, String telefonNumber) {
        type(vorname, vorName);
        type(nachname, nachName);
        type(telefonnummer, telefonNumber);
        click(speichernButton);
        return this;
    }

    // Method to verify if the update was successful
    public boolean verifyUpdateResult() {
        if (isElementPresent(updateSuccessMessage)) {
            return false;
        }
        return true;
    }
    @FindBy(xpath = "//button[contains(@class, 'close-popup')]") // Укажите точный локатор
    WebElement popupCloseButton;
    @FindBy(xpath = "//button[contains(text(),'Account')]")
    WebElement accountButton;

    public ProfilePage clickAccountButton() {
        // Создаем экземпляр WebDriverWait для явного ожидания
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            // Ожидаем появления попапа, если он есть
            WebElement popupMessage = wait.until(ExpectedConditions.visibilityOf(popupCloseButton));

            // Проверяем текст внутри попапа
            if (popupMessage.isDisplayed() && popupMessage.getText().equals("Vorgang erfolgreich abgeschlossen")) {
                System.out.println("Popup confirmation detected: " + popupMessage.getText());

                // Закрываем попап
                popupCloseButton.click();
            }
        } catch (Exception e) {
            // Если попап не появился, продолжаем выполнение
            System.out.println("Popup did not appear, proceeding to click Account.");
        }

        // Кликаем на кнопку "Account"
        click(accountButton);

        return this; // Возвращаем текущий экземпляр страницы для цепочки вызовов

    }

    @FindBy(xpath = "//a[contains(text(),'Profile')]")
    WebElement profileLink;

    public ProfilePage clickProfileLink() {
        click(profileLink);
        return this;
    }
    // Локатор для крестика поп-ап сообщения


    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    WebElement logoutButton;

    public void clickLogoutButton() {
        logoutButton.click();
    }
}

