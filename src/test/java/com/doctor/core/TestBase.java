package com.doctor.core;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);
    public static ApplicationManager app = new ApplicationManager(System.getProperty("browser", "chrome"));

    @BeforeSuite
    public void setUp() {
        logger.info("********************** TESTING IN PROGRESS *************************");
        app.init();
    }

    @BeforeMethod
    public void startTest(Method method) {
        logger.info("Test is started: [" + method.getName() + "]");
        Allure.step("Start test: " + method.getName());
    }

    @AfterMethod
    public void stopTest(Method method, ITestResult result, ITestContext context) {
        StringBuilder parameters = new StringBuilder();
        for (String key : context.getAttributeNames()) {
            if (!key.equals("ALLURE_UUID")) { // Исключаем служебные параметры
                Object value = context.getAttribute(key);
                parameters.append(key).append("=").append(value).append(", ");
            }
        }

        if (parameters.length() > 0) {
            parameters.setLength(parameters.length() - 2); // Удаляем лишнюю запятую
        }

        logger.info("Test is started with data: [" + parameters + "]");

        if (result.isSuccess()) {
            logger.info("Test is PASSED: [" + method.getName() + "], with data: [" + parameters + "]");
        } else {
            try {
                app.getHomePage().isAlertPresent();
            } catch (Exception ignore) {
            }

            // Логируем параметры для неудачного теста
            logger.error("Test is FAILED: [" + method.getName() + "], with data: [" + parameters + "]");

            // Делаем и прикрепляем скриншот
            String screenshotPath = app.getHomePage().takeScreenshot();
            attachScreenshot(screenshotPath);
            logger.error("Screenshot: [" + screenshotPath + "]");
        }

        logger.info("Test is ended: [" + method.getName() + "]");
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] attachScreenshot(String screenshotPath) {
        try {
            return Files.readAllBytes(Paths.get(screenshotPath));
        } catch (Exception e) {
            logger.error("Error reading screenshot file: " + screenshotPath, e);
            return new byte[0];
        }
    }

    @AfterSuite
    public void tearDown() {
        logger.info("********************** ALL TEST END *************************");
        app.stop();
    }
}