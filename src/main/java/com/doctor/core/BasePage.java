package com.doctor.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class BasePage {
    Logger logger = LoggerFactory.getLogger(BasePage.class);
    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }


        public boolean isElementPresent(WebElement element) {
            try {
                return element.isDisplayed();
            } catch (NoSuchElementException e) {
                return false;
            } catch (StaleElementReferenceException e) {
                return false;
            }
        }


    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        //logger.info("[" + locator + "] is pressed");
    }

    protected void type(WebElement element, String text) {
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    public boolean isAlertPresent() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        try {
            logger.warn("Alert has text: [" + alert.getText() + "]");
            alert.accept();
            return true;
        } catch (Exception ignore) {
            return false;
        }
    }


    public String alertTextPresent() {
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }

    public String takeScreenshot() {
        // Создаем объект File для сохранения скриншота, добавляя текущую метку времени в название файла
        File screenshot = new File("src/test_screenshots/screen-" + System.currentTimeMillis() + ".png");

        try {
            // Получаем временный файл скриншота с помощью интерфейса TakesScreenshot
            File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Копируем временный файл в постоянное место назначения (в созданный ранее файл screenshot)
            Files.copy(tmp.toPath(), screenshot.toPath());
        } catch (NoSuchSessionException e) {
            logger.error("WebDriver session is closed, cannot take screenshot", e);
            return ""; // Возвращаем пустую строку, чтобы тест не падал
        } catch (IOException e) {
            // Логируем ошибку при сохранении скриншота и выбрасываем исключение RuntimeException
            logger.error("Failed to save screenshot", e);
            throw new RuntimeException(e);
        }
        // Возвращаем путь к сохраненному скриншоту
        return screenshot.getAbsolutePath();
    }





}
