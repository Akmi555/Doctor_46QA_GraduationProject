package com.doctor.core;

import com.doctor.pages.ProfilePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

public class BasePage {
    public Logger logger = LoggerFactory.getLogger(BasePage.class);
    public static WebDriver driver;
    public static WebDriverWait wait;
    public JavascriptExecutor js;

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

    public void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        //options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); // неявное
    }
    public static void click(WebElement element) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // 10 секунд ожидания
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (TimeoutException e) {
            throw new RuntimeException("Element is not clickable after 10 seconds.", e);
        }
//    public void click(WebElement element) {
//        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
//        // element.click();
//        logger.info("[" + element + "] is pressed");
//    }
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


    public void scrollToElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        js.executeScript("arguments[0].scrollIntoView();", element); //js.executeScript("arguments[0].scrollIntoView(true);", element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void scrollTo(int y) {
        js.executeScript("window.scrollBy(" + 0 + "," + y + ")");
    }

    public void clickElementWithJs(WebElement element) {
        try {
            // Проверяем, отображается ли элемент на странице
            if (element.isDisplayed()) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].click();", element);
                System.out.println("Элемент успешно нажат с помощью JavaScript.");
            } else {
                System.err.println("Элемент не отображается на странице, клик через JS не выполнен.");
            }
        } catch (Exception e) {
            System.err.println("Ошибка при клике через JavaScript: " + e.getMessage());
        }
    }
}


//    public boolean isUserLoggedIn() {
//        try {
//            wait.until(ExpectedConditions.visibilityOf(accountButton));
//            return true; // User is logged in if the Account button is visible
//        } catch (TimeoutException e) {
//            // Account button not found within the timeout period, so user is likely NOT logged in
//            return false;
//        }
//    }

