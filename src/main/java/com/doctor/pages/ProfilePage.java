package com.doctor.pages;

import com.doctor.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasePage {


    public ProfilePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
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

    @FindBy(xpath = "//button[contains(text(),'Speichern')]")
    WebElement speichernButton;

    @FindBy(xpath = "//div[contains(text(),'Vorgang erfolgreich abgeschlossen')]")
    private WebElement updateSuccessMessage;

    // Method to update user profile information
    public ProfilePage updateUser(String vorName, String nachName, String telefonNumber, String passWord) {
        type(vorname, vorName);
        type(nachname, nachName);
        type(telefonnummer, telefonNumber);
        type(password, passWord);
        click(speichernButton);
        return this;
    }

        // Method to verify if the update was successful
        public boolean verifyUpdateResult () {
            try {
                wait.until(ExpectedConditions.visibilityOf(updateSuccessMessage));
                boolean result = isElementPresent(updateSuccessMessage);
            } catch (Exception e) {
                logger.error("Error while verifying update result: " + e.getMessage());

            }
            return false;
        }
    }
