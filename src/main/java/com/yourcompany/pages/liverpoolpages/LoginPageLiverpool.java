package com.yourcompany.pages.liverpoolpages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

import com.yourcompany.locators.LoginLocators;
import com.yourcompany.pages.LoginPage;

public class LoginPageLiverpool implements LoginPage {
    private WebDriver driver;
    public static final By HomepageLogoLocator = By.className("header-icon");
    public static final By codeVerificationLocator = By.id("code");

    public LoginPageLiverpool(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void login(String username, String password) {
        WebElement headerLogin = driver.findElement(LoginLocators.Liverpool.HEADER_LOGIN);
        System.out.println("the element " + headerLogin + " is displayed");
        // wait for element to be clickable 
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.elementToBeClickable(headerLogin));
        // hover and double click using Actions
        new Actions(driver)
            .moveToElement(element)
            .pause(1000)
            .doubleClick()
            .pause(500)
            .perform();
        // wait condition after double click    
        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(d -> {
                WebElement usernameField = d.findElement(LoginLocators.Liverpool.USERNAME);
                return usernameField.isDisplayed() &&
                    usernameField.isEnabled() &&
                    usernameField.getAttribute("aria-hidden") == null;
            });
        driver.findElement(LoginLocators.Liverpool.USERNAME).sendKeys(username);
        driver.findElement(LoginLocators.Liverpool.PASSWORD).sendKeys(password);
        driver.findElement(LoginLocators.Liverpool.LOGIN_BUTTON).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isValidationPromptPresent() {
        try {
            return driver.findElement(codeVerificationLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}