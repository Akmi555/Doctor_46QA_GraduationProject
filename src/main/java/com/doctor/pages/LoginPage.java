package com.doctor.pages;

import com.doctor.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(text(),'Anmelden')]")
    WebElement anmeldenButton;

    public boolean isAnmeldenButtonIsPresent() {
        isElementPresent(anmeldenButton);
        return isElementPresent(anmeldenButton);
    }

    @FindBy(xpath = "//input[@type='email']")
    WebElement emailInput;

    @FindBy(xpath = "//input[@type='password']")
    WebElement passwordInput;

    public LoginPage enterPersonalData(String email, String password) {
        type(emailInput, email);
        type(passwordInput, password);
        return this;
    }

    public HomePage clickLoginButton() {
        click(anmeldenButton);
        return new HomePage(driver, wait);
    }
}
