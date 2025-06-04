package com.yourcompany.pages.saucedemopages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.yourcompany.locators.ProductLocatorBuilder;

public class SauceCheckoutOverview {
    private WebDriver driver;

    // Locators
    public static final By finishButtonLocator = By.id("finish");
    public static final By cancelButtonLocator = By.id("cancel");

    public SauceCheckoutOverview(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFinish() {
        try {
            driver.findElement(finishButtonLocator).click();
        } catch (NoSuchElementException e) {
            System.out.println("Finish button is not displayed in the page");
        }
    }

    public void clickCancel() {
        try {
            driver.findElement(cancelButtonLocator).click();
        } catch (NoSuchElementException e) {
            System.out.println("Cancel button is not displayed in the page");
        }
    }

    public boolean validateProductWithPriceDisplayed(List<String> products, List<String> prices) {
        if(products.size() != prices.size()){
            System.out.println("Product and price lists are not the same size!");
            return false;
        }

        for(int i=0; i<products.size(); i++) {
            By priceInOverview = new ProductLocatorBuilder(products.get(i))
                    .inOverviewPagePrice()
                    .build();
            String priceDisplayed;
            try {
                priceDisplayed = driver.findElement(priceInOverview).getText();
            } catch (NoSuchElementException e) {
                System.out.println("Article with expected price not displayed");
                return false;
            }
            if(!priceDisplayed.equals(prices.get(i))) {
                System.out.println("Price mismatch for product " + products.get(i) + ". Expected " + prices.get(i));
                return false;
            }
        }
        return true;
    }
}
