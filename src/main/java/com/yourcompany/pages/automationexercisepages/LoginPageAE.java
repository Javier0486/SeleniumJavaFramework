package com.yourcompany.pages.automationexercisepages;

import org.openqa.selenium.WebDriver;

import com.yourcompany.pages.LoginPage;
import com.yourcompany.locators.LoginLocators;

public class LoginPageAE implements LoginPage {
    private WebDriver driver;

    public LoginPageAE(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void login(String username, String password) {
        driver.findElement(LoginLocators.AutomationExercise.HEADERSIGNLOG).click();
        driver.findElement(LoginLocators.AutomationExercise.USERNAME).clear();
        driver.findElement(LoginLocators.AutomationExercise.USERNAME).sendKeys(username);
        driver.findElement(LoginLocators.AutomationExercise.PASSWORD).clear();
        driver.findElement(LoginLocators.AutomationExercise.PASSWORD).sendKeys(password);
        driver.findElement(LoginLocators.AutomationExercise.LOGIN_BUTTON).click();
    }
}