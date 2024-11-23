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
        public boolean verifyUpdateResult () {
            if (isElementPresent(updateSuccessMessage)) {
          return false;
            }
            return  true;
        }
    @FindBy(xpath = "//button[contains(text(),'Account')]")
    WebElement accountButton;
    public ProfilePage clickAccountButton() {
        click(accountButton);
        return this;
    }
    @FindBy(xpath = "//a[contains(text(),'Profile')]")
    WebElement profileLink;
    public ProfilePage clickProfileLink() {
        click(profileLink);
        return this;
    }
}
