package com.doctor.pages;
import com.doctor.core.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
public class ProfilePage extends BasePage {


    public ProfilePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "//input[@name='FirstName']")
    public WebElement vorname;

    @FindBy(xpath = "(//div[@class='col-sm-6']//input)[2]")
    public WebElement nachname;


    @FindBy(xpath = "(//div[@class='col-sm-6']//input)[1]")
    WebElement telefonnummer;


    @FindBy(xpath = "//button[contains(text(),'Speichern')]")
    WebElement speichernButton;

    @FindBy(xpath = "//div[contains(text(),'Vorgang erfolgreich abgeschlossen')]")
    private WebElement updateSuccessMessage;

    // Method to update user profile information
    public ProfilePage updateUser(String vorName, String nachName, String telefonNumber) {
        vorname.clear();
        type(vorname, vorName);
        nachname.clear();
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

    @FindBy(xpath = "//button[@class='Toastify__close-button Toastify__close-button--light']")
    WebElement closeUpdateSuccessMessageButton;

    public void clickCloseupdateSuccessMessageButton() {
        click(closeUpdateSuccessMessageButton);
    }
    // Method to get validation message for a field using JavaScriptExecutor
    public String getValidationMessage(WebElement field) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", field);
    }
}