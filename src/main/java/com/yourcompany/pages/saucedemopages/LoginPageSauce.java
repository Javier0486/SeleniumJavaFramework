package com.yourcompany.pages.saucedemopages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.yourcompany.locators.LoginLocators;
import com.yourcompany.pages.LoginPage;
import com.yourcompany.pages.LogoutPage;

public class LoginPageSauce implements LoginPage, LogoutPage{
    private WebDriver driver;

    // Locators
    private static final By burgerMenuIconLocator = By.xpath("//button[@id=\"react-burger-menu-btn\"]");
    private static final By logoutSidebarLinkLocator = By.id("logout_sidebar_link");

    public LoginPageSauce(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void login(String username, String password) {
        driver.findElement(LoginLocators.SauceDemo.USERNAME).sendKeys(username);
        driver.findElement(LoginLocators.SauceDemo.PASSWORD).sendKeys(password);
        driver.findElement(LoginLocators.SauceDemo.LOGIN_BUTTON).click();
    }

    public void logout() {
        try {
            driver.findElement(burgerMenuIconLocator).click();
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.elementToBeClickable(logoutSidebarLinkLocator));
            driver.findElement(logoutSidebarLinkLocator).click();
        } catch (NoSuchElementException e) {
            System.out.println("One of the elements was not found.");
        }
    }
}