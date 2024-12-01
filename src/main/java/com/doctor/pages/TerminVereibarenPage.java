package com.doctor.pages;

import com.doctor.core.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TerminVereibarenPage extends BasePage {

    public TerminVereibarenPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "//input[@id='isNew']")
    WebElement newPatientCheckbox;

    @FindBy(xpath = "//select[@class='form-select mb-3']")
    WebElement serviceDropdown;

    @FindBy(xpath = "//option[contains(text(),'Infusionstherapie')]")
    WebElement infusionstherapieOption;

    @FindBy(xpath = "//button[@data-bs-target='#aTimeslot2']")
    WebElement timeslotMenuButton;

    @FindBy(xpath = "(//input[@class='form-check-input'])[2]")
    WebElement firstAvailableTimeslot;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    WebElement confirmTerminVereibarenButton;

    @FindBy(xpath = "//div[contains(text(),'Ihr Termin wurde erfolgreich abgeschlossen. Sie er')]")
    WebElement successNotification;

    @FindBy(xpath = "//div[contains(text(),'Diese Zeit ist bereits vergeben')]")
    WebElement errorNotification;

    @FindBy(xpath = "//button[contains(text(),'Termin vereinbaren')]")
    WebElement terminVereibarenButton;

    // Click Termin vereibaren button
    public TerminVereibarenPage clickTerminVereibarenButton() {
        clickElementWithJs(terminVereibarenButton);
        return this;

    }

    // Select "Ich bin ein neuer Patient" checkbox
    public TerminVereibarenPage selectNewPatientCheckbox() {
        click(newPatientCheckbox);
        return this;
    }

    // Выбрать вид услуги
    public TerminVereibarenPage selectService(String service) {
        Select select = new Select(serviceDropdown);
        select.selectByVisibleText(service);
        return this;
    }

    // Open timeslot menu
    public TerminVereibarenPage openTimeslotMenu() {
        click(timeslotMenuButton);
        return this;
    }

    // Select the first available timeslot
    public TerminVereibarenPage selectFirstAvailableTimeslot() {
        click(firstAvailableTimeslot);
        return this;
    }

    // Scroll to bottom of the page and confirm Termin vereibaren
    public TerminVereibarenPage scrollToBottomAndConfirmTermin() {
//        scrollToElement(confirmTerminVereibarenButton);
        clickElementWithJs(confirmTerminVereibarenButton);
        return this;
    }

    // Проверить, отображается ли сообщение об успешной записи
    public boolean isSuccessNotificationDisplayed() {
        return isElementPresent(successNotification);
    }

    // Check if error notification is displayed
    public boolean isErrorNotificationDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorNotification));
            return true;
        } catch (TimeoutException e) {
            System.err.println("Сообщение об ошибке не отображается: " + e.getMessage());
            return false;
        }

    }


}


