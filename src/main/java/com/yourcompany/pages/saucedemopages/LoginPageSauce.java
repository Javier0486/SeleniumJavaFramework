package com.yourcompany.pages.saucedemopages;

import org.openqa.selenium.WebDriver;

import com.yourcompany.locators.LoginLocators;
import com.yourcompany.pages.LoginPage;

public class LoginPageSauce implements LoginPage{
    private WebDriver driver;

    public LoginPageSauce(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void login(String username, String password) {
        driver.findElement(LoginLocators.SauceDemo.USERNAME).sendKeys(username);
        driver.findElement(LoginLocators.SauceDemo.PASSWORD).sendKeys(password);
        driver.findElement(LoginLocators.SauceDemo.LOGIN_BUTTON).click();
    }
}