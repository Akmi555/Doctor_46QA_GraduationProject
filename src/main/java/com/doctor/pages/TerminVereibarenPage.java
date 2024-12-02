package com.doctor.pages;

import com.doctor.core.BasePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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

    @FindBy(xpath = "//option[contains(text(),''Schröpftherapie')]")
    WebElement schröpftherapieOption;

    @FindBy(xpath = "//button[@data-bs-target='#aTimeslot2']")//
    WebElement selectDayNegative;

    @FindBy(xpath = "//button[@data-bs-target='#aTimeslot3']")
    WebElement selectDayPositive;


    @FindBy(xpath = "(//input[@class='form-check-input'])[2]")
    WebElement firstAvailableTimeslotNegative;
    @FindBy(xpath = "(//input[@class='form-check-input'])[2]")
    WebElement firstAvailableTimeslotPositive;


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
    public TerminVereibarenPage clickServiceMenuSelector(String service) {
        Select select = new Select(serviceDropdown);
        select.selectByVisibleText(service);
        return this;
    }
public TerminVereibarenPage selectTherapie(String therapie) {
        if (therapie.equals("Infusionstherapie")) {
            click(infusionstherapieOption);
        } else if (therapie.equals("Schröpftherapie")) {
            click(schröpftherapieOption);
        }
        return this;
}
    @FindBy(xpath = "//button[contains(@data-bs-target, '#aTimeslot')]")
    public List<WebElement> allDates; // Все доступные даты

    @FindBy(xpath = "//input[starts-with(@id, '5at')]")
    public List<WebElement> allTimeslots;


    /**
     * Выбрать дату и таймслот по индексу.
     * Метод сначала выбирает одну из последних двух дат, затем таймслот.
     */
    public TerminVereibarenPage selectDateAndTimeslot(int dateIndex, int timeslotIndex) {
        // Собираем все доступные даты
        List<WebElement> dates = allDates;
        if (dateIndex >= 0 && dateIndex < dates.size()) {
            // Выбираем дату по индексу
            click(dates.get(dateIndex));
            System.out.println("Выбрана дата с индексом: " + dateIndex);

            // После выбора даты собираем таймслоты
            List<WebElement> timeslots = allTimeslots;
            if (timeslotIndex >= 0 && timeslotIndex < timeslots.size()) {
                // Выбираем таймслот по индексу
                click(timeslots.get(timeslotIndex));
                System.out.println("Выбран таймслот с индексом: " + timeslotIndex);
            } else {
                System.err.println("Индекс таймслота вне диапазона: " + timeslotIndex);
            }
        } else {
            System.err.println("Индекс даты вне диапазона: " + dateIndex);
        }
        return this;
    }
    // Open timeslot menu
    public TerminVereibarenPage openDaysSlotMenuNegative() {
        click(selectDayNegative);
        return this;
    }


    // Select the first available timeslot
    public TerminVereibarenPage selectFirstAvailableTimeslotNegative() {
        click(firstAvailableTimeslotNegative);
        return this;
    }
    public TerminVereibarenPage selectFirstAvailableTimeslotPositive() {
        click(firstAvailableTimeslotPositive);
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
        try {
            wait.until(ExpectedConditions.visibilityOf(successNotification));
            return true;
        } catch (TimeoutException e) {
            System.err.println("Сообщение об успешной регистрации не отображается: " + e.getMessage());
            return false;
        }
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


