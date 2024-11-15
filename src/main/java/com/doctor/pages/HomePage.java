package com.doctor.pages;

import com.doctor.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//a[сontains(text(),'no')]")
    WebElement teamButton;


    public boolean isHomeComponentPresent() {
        System.out.println("Look for 'HomeComponent' on the home page");

        return teamButton.isDisplayed();
    }
//    public boolean isTeamButtonPresent() {
//        return teamButton.isDisplayed();
//    }
}
