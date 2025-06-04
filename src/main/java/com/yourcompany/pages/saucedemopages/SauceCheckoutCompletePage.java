package com.yourcompany.pages.saucedemopages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SauceCheckoutCompletePage {
    WebDriver driver;

    // Locators
    private static final By thanksLabelLocator = By.xpath("//div//h2[contains(text(), 'Thank you for your order!')]");
    private static final By orderDisplatchedLabelLocator = By.xpath("//div[normalize-space(text())='Your order has been dispatched, and will arrive just as fast as the pony can get there!']");
    private static final By backHomeButtonLocator = By.id("back-to-products");

    public SauceCheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickBackButton() {
        try {
            driver.findElement(backHomeButtonLocator).click();
        } catch (NoSuchElementException e) {
            System.out.println("Back Home button is not displayed in the page");
        }
    }

    public boolean thanksMessages() {
        try {
            boolean thanksMessage = driver.findElement(thanksLabelLocator).isDisplayed();
            boolean orderDisplatchedMessage = driver.findElement(orderDisplatchedLabelLocator).isDisplayed();
            return thanksMessage && orderDisplatchedMessage;
        } catch (NoSuchElementException e) {
            System.out.println("One or more elements are not displayed.");
            return false;
        }
    }
    
}
