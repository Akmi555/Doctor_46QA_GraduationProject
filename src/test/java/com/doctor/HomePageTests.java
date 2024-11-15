package com.doctor;

import com.doctor.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {

    @BeforeMethod
    public void preCondition() {

        app.driver.get("https://gesundheitspraxis-wertvoll.de");
    }

    @Test
    public void isHomeComponentPresentTest() {
        HomePage homePage = new HomePage(app.driver, app.wait);
        Assert.assertTrue(homePage.isHomeComponentPresent(),"Item not found on page");
        System.out.println("'HomeComponent' element found on the home page");
    }

}
