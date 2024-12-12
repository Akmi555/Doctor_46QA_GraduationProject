package com.doctor.pages;

import com.doctor.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class TerminVereibarenPage extends BasePage {

    public TerminVereibarenPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    // Локаторы
    @FindBy(xpath = "//select[@class='form-select mb-3']")
    WebElement therapyMenu;

    @FindBy(xpath = "//button[@aria-label='close']")
    WebElement closeSuccessNotificationButton;

    @FindBy(xpath = "//button[contains(text(),'Termin vereinbaren')]")
    WebElement terminVereibarenButton;

    @FindBy(xpath = "//button[@data-bs-target='#aTimeslot3']")
    WebElement dateButton;

    @FindBy(xpath = "//input[@id='3at0']")
    WebElement timeslotButton;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    WebElement confirmTerminVereibarenButton;

    @FindBy(xpath = "//div[contains(text(),'Ihr Termin wurde erfolgreich abgeschlossen. Sie er')]")
    WebElement successNotification;

    @FindBy(xpath = "//button[@aria-label='close']")
    WebElement successNotificationCloseButton;

    @FindBy(xpath = "//a[contains(text(),'Termine')]")
    WebElement accountTerminMenu;

    @FindBy(xpath = "(//div[@class='col-sm-1']//button)[1]")
    public WebElement deleteTerminButton;

    @FindBy(xpath = "//div[contains(text(),'Ihr Termin wurde erfolgreich abgesagt. Sie erhalte')]")
    WebElement deleteSuccessNotification;

    @FindBy(xpath = "//button[contains(text(),'Ja, löschen!')]")
    WebElement confirmDeleteButton;

    // Методы
    public TerminVereibarenPage clickTerminVereibarenButton() {
        clickElementWithJs(terminVereibarenButton);
        return this;
    }

    public TerminVereibarenPage selectTherapyMenu() {
        click(therapyMenu);
        return this;
    }

    public TerminVereibarenPage selectTherapyById(String therapyId) {
        WebElement therapyOption = driver.findElement(By.xpath("//option[@value='" + therapyId + "']"));
        click(therapyOption);
        return this;
    }

    public TerminVereibarenPage selectDateByTarget(String target) {
        WebElement date = driver.findElement(By.xpath("//button[contains(@data-bs-target, '" + target + "')]"));
        click(date);
        return this;
    }

    public TerminVereibarenPage selectTimeslotById(String timeslotId) {
        WebElement timeslot = driver.findElement(By.xpath("//input[@id='" + timeslotId + "']"));
        click(timeslot);
        return this;
    }

    public TerminVereibarenPage scrollToBottomAndConfirmTermin() {
        clickElementWithJs(confirmTerminVereibarenButton);
        return this;
    }

    public boolean isSuccessNotificationDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(successNotification)).isDisplayed();
    }

    public TerminVereibarenPage closeSuccessNotification() {
        if (successNotificationCloseButton.isDisplayed()) {
            click(successNotificationCloseButton);
        }
        return this;
    }



    public TerminVereibarenPage selectTerminFromAccountMenu() {
        click(accountTerminMenu);
        return this;
    }

    public TerminVereibarenPage deleteFirstTermin() {
        try {
            // Ожидаем появления кнопки удаления
            WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='col-sm-1']//button)[1]")));

            // Если кнопка удаления уже видна, кликаем по ней
            clickElementWithJs(deleteButton);
        } catch (Exception e) {
            // Если кнопка удаления не была найдена, пытаемся нажать на Mehr до тех пор, пока кнопка удаления не станет видимой
            boolean mehrButtonVisible = true;

            while (mehrButtonVisible) {
                try {
                    // Ищем кнопку Mehr
                    WebElement mehrButton = driver.findElement(By.xpath("//button[contains(text(),'Mehr')]"));

                    // Если кнопка Mehr доступна, кликаем по ней
                    if (mehrButton.isDisplayed()) {
                        clickElementWithJs(mehrButton);
                        System.out.println("Mehr button pressed");
                    }

                    // Ожидаем появления кнопки удаления
                    deleteTerminButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='col-sm-1']//button)[1]")));

                    // Если кнопка удаления появилась, кликаем по ней
                    clickElementWithJs(deleteTerminButton);
                    mehrButtonVisible = false; // Прерываем цикл, так как кнопка удаления найдена
                } catch (Exception ex) {
                    // Если кнопка Mehr не найдена или кнопка удаления не появилась, пробуем снова
                    System.out.println("Кнопка Mehr не найдена или кнопка удаления не видна. Попробуем снова.");
                    // Добавляем небольшую задержку перед следующей попыткой, чтобы не перегружать сервер
                    try {
                        Thread.sleep(500); // Пауза в 500 мс
                    } catch (InterruptedException interruptedException) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        return this;
    }


    public boolean isDeleteSuccessNotificationDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(deleteSuccessNotification)).isDisplayed();
    }

    public void clickConfirmDeleteButton() {
        click(confirmDeleteButton);
    }
}
//    @FindBy(xpath = "//input[@id='isNew']")
//    WebElement newPatientCheckbox;
//
//    @FindBy(xpath = "//select[@class='form-select mb-3']")
//    WebElement serviceDropdownButton;
//
//    @FindBy(xpath = "//select[@class='form-select mb-3']/option")
//    public List<WebElement> therapiesOptions; // Все виды терапий
//
//    @FindBy(xpath = "//option[contains(text(),'Infusionstherapie')]")
//    WebElement infusionstherapieOption;
//
//    @FindBy(xpath = "//option[contains(text(),''Schröpftherapie')]")
//    WebElement schröpftherapieOption;
//
//    @FindBy(xpath = "//button[@data-bs-target='#aTimeslot2']")//
//    WebElement selectDayNegative;
//
//    @FindBy(xpath = "//button[@data-bs-target='#aTimeslot3']")
//    WebElement selectDayPositive;
//
//
//    @FindBy(xpath = "(//input[@class='form-check-input'])[2]")
//    WebElement firstAvailableTimeslotNegative;
//    @FindBy(xpath = "(//input[@class='form-check-input'])[2]")
//    WebElement firstAvailableTimeslotPositive;
//
//
//    @FindBy(xpath = "//button[@class='btn btn-primary']")
//    WebElement confirmTerminVereibarenButton;
//
//    @FindBy(xpath = "//div[contains(text(),'Ihr Termin wurde erfolgreich abgeschlossen. Sie er')]")
//    WebElement successNotification;
//
//    @FindBy(xpath = "//div[contains(text(),'Diese Zeit ist bereits vergeben')]")
//    WebElement errorNotification;
//
//    @FindBy(xpath = "//button[contains(text(),'Termin vereinbaren')]")
//    WebElement terminVereibarenButton;
//
//    // Click Termin vereibaren button
//    public TerminVereibarenPage clickTerminVereibarenButton() {
//        clickElementWithJs(terminVereibarenButton);
//        return this;
//
//    }
//
//    // Select "Ich bin ein neuer Patient" checkbox
//    public TerminVereibarenPage selectNewPatientCheckbox() {
//        click(newPatientCheckbox);
//        return this;
//    }
//
//    public TerminVereibarenPage clickServiceMenuSelector() {
//        click(serviceDropdownButton);
//        return this;
//    }
////    // Выбрать вид услуги
////    public TerminVereibarenPage clickServiceMenuSelector(String service) {
////        Select select = new Select(serviceDropdown);
////        select.selectByVisibleText(service);
////        return this;
//
//    public TerminVereibarenPage selectTherapie(String therapie) {
//        if (therapie.equals("Infusionstherapie")) {
//            click(infusionstherapieOption);
//        } else if (therapie.equals("Schröpftherapie")) {
//            click(schröpftherapieOption);
//        }
//        return this;
//    }
//
//    @FindBy(xpath = "//button[contains(@data-bs-target, '#aTimeslot')]")
//    public List<WebElement> allDates; // Все доступные даты
//
//    @FindBy(xpath = "//input[starts-with(@id, '5at')]")
//    public List<WebElement> allTimeslots;
//
//
//    // Scroll to bottom of the page and confirm Termin vereinbaren
//    public TerminVereibarenPage scrollToBottomAndConfirmTermin() {
//        clickElementWithJs(confirmTerminVereibarenButton);
//        return this;
//    }
//
//    // Проверить, отображается ли сообщение об успешной записи
//    public boolean isSuccessNotificationDisplayed() {
//        try {
//            wait.until(ExpectedConditions.visibilityOf(successNotification));
//            return true;
//        } catch (TimeoutException e) {
//            System.err.println("Сообщение об успешной регистрации не отображается: " + e.getMessage());
//            return false;
//        }
//    }
//
//    // Check if error notification is displayed
//    public boolean isErrorNotificationDisplayed() {
//        try {
//            wait.until(ExpectedConditions.visibilityOf(errorNotification));
//            return true;
//        } catch (TimeoutException e) {
//            System.err.println("Сообщение об ошибке не отображается: " + e.getMessage());
//            return false;
//        }
//    }
//
//    // Метод для выбора конкретной терапии по имени
//    public TerminVereibarenPage selectTherapyByName(String therapyName) {
//        for (WebElement therapy : therapiesOptions) {
//            if (therapy.getText().equalsIgnoreCase(therapyName)) {
//                System.out.println("Выбрана терапия: " + therapyName);
//                therapy.click();
//                return this;
//            }
//        }
//        throw new IllegalArgumentException("Терапия с именем '" + therapyName + "' не найдена.");
//    }
//
//    // Основной метод: перебор терапий, дат и таймслотов
//    public TerminVereibarenPage selectDateAndTimeslotForAllTherapies() {
//        // Нажимаем на выпадающее меню
//        click(serviceDropdownButton);
//
//        // Перебираем все терапии
//        for (WebElement therapy : therapiesOptions) {
//            String therapyName = therapy.getText();
//
//            // Пропускаем заглушку
//            if ("Wählen Sie eine Dienstleistung aus".equals(therapyName)) {
//                continue;
//            }
//
//            System.out.println("Текущая терапия: " + therapyName);
//            selectTherapyByName(therapyName); // Выбираем терапию
//
//            // Обновляем список дат
//            if (allDates.size() <= 1) {
//                System.out.println("Нет доступных дат для терапии: " + therapyName);
//                continue;
//            }
//            List<WebElement> dates = allDates.subList(1, allDates.size()); // Исключаем первую дату
//
//            boolean slotsAvailableForTherapy = false;
//
//            // Перебираем даты
//            for (int dateIndex = 0; dateIndex < dates.size(); dateIndex++) {
//                click(dates.get(dateIndex)); // Выбираем дату
//                System.out.println("Выбрана дата с индексом: " + (dateIndex + 1));
//
//                // Получаем список таймслотов
//                List<WebElement> timeslots = allTimeslots;
//
//                if (timeslots.isEmpty()) {
//                    System.out.println("Нет доступных таймслотов для даты с индексом: " + (dateIndex + 1));
//                    continue;
//                }
//
//                // Перебираем таймслоты
//                for (int timeslotIndex = 0; timeslotIndex < timeslots.size(); timeslotIndex++) {
//                    click(timeslots.get(timeslotIndex)); // Выбираем таймслот
//                    System.out.println("Выбран таймслот с индексом: " + timeslotIndex);
//
//                    // Пробуем подтвердить запись
//                    scrollToBottomAndConfirmTermin();
//
//                    // Проверяем успешность
//                    if (isSuccessNotificationDisplayed()) {
//                        System.out.println("Запись успешно забронирована на терапию: " + therapyName +
//                                ", дата с индексом: " + (dateIndex + 1) +
//                                ", таймслот с индексом: " + timeslotIndex);
//                        return this;
//                    } else if (isErrorNotificationDisplayed()) {
//                        System.out.println("Таймслот с индексом " + timeslotIndex + " уже занят.");
//                    }
//                }
//            }
//
//            if (!slotsAvailableForTherapy) {
//                System.out.println("У текущей терапии " + therapyName + " нет доступных таймслотов.");
//            }
//        }
//
//        System.out.println("Для всех терапий и дат не найдено доступных таймслотов.");
//        return this;
//    }


//    /**
//     * Выбрать дату и таймслот по индексу.
//     * Метод сначала выбирает одну из последних двух дат, затем таймслот.
//     */
//    public TerminVereibarenPage selectDateAndTimeslot(int dateIndex, int timeslotIndex) {
//        // Собираем все доступные даты
//        List<WebElement> dates = allDates;
//        if (dateIndex >= 0 && dateIndex < dates.size()) {
//            // Выбираем дату по индексу
//            click(dates.get(dateIndex));
//            System.out.println("Выбрана дата с индексом: " + dateIndex);
//
//            // После выбора даты собираем таймслоты
//            List<WebElement> timeslots = allTimeslots;
//            if (timeslotIndex >= 0 && timeslotIndex < timeslots.size()) {
//                // Выбираем таймслот по индексу
//                click(timeslots.get(timeslotIndex));
//                System.out.println("Выбран таймслот с индексом: " + timeslotIndex);
//            } else {
//                System.err.println("Индекс таймслота вне диапазона: " + timeslotIndex);
//            }
//        } else {
//            System.err.println("Индекс даты вне диапазона: " + dateIndex);
//        }
//        return this;
//    }
//    // Open timeslot menu
//    public TerminVereibarenPage openDaysSlotMenuNegative() {
//        click(selectDayNegative);
//        return this;
//    }


//    // Select the first available timeslot
//    public TerminVereibarenPage selectFirstAvailableTimeslotNegative() {
//        click(firstAvailableTimeslotNegative);
//        return this;
//    }
//
//    public TerminVereibarenPage selectFirstAvailableTimeslotPositive() {
//        click(firstAvailableTimeslotPositive);
//        return this;
//    }
//
//}

// Check if error notification is displayed








