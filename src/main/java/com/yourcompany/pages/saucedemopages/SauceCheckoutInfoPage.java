package com.yourcompany.pages.saucedemopages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SauceCheckoutInfoPage {
    private WebDriver driver;

    // Locators
    private static final By firstNameInputLocator = By.id("first-name");
    private static final By lastNameInputLocator = By.id("last-name");
    private static final By zipCodeInputLocator = By.id("postal-code");
    private static final By continueButtonLocator = By.id("continue");
    private static final By cancelButtonLocator = By.id("cancel");

    public SauceCheckoutInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillAndContinue(String name, String lastName, String zipCode) {
        try {
            driver.findElement(firstNameInputLocator).sendKeys(name);
            driver.findElement(lastNameInputLocator).sendKeys(lastName);
            driver.findElement(zipCodeInputLocator).sendKeys(zipCode);
            driver.findElement(continueButtonLocator).click();
        } catch (NoSuchElementException e) {
            System.out.println("Input not found in page");
        }
    }

    public void CancelCheckoutInfo(){
        try {
            driver.findElement(cancelButtonLocator).click();
        } catch (NoSuchElementException e) {
            System.out.println("Cancel element not found in page");
        }
    }

    public boolean infoElementsDisplayed() {
        try {
            boolean firstName = driver.findElement(firstNameInputLocator).isDisplayed();
            boolean lastName = driver.findElement(lastNameInputLocator).isDisplayed();
            boolean zipcode = driver.findElement(zipCodeInputLocator).isDisplayed();
            return firstName && lastName && zipcode;
        } catch (NoSuchElementException e) {
            System.out.println("One or more elements are not displayed.");            
            return false;
        }
    }
    
}
