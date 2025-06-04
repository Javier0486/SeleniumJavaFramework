package com.yourcompany.pages.saucedemopages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.yourcompany.locators.ProductLocatorBuilder;

public class CartPage {
    private WebDriver driver;

    private static final By checkoutButtonLocator = By.id("checkout");
    private static final By continueChopingButtonLocator = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean allProductsInCartDisplayed(List<String> products) {
        for(String product : products) {
            By productInCart = new ProductLocatorBuilder(product)
                    .asLabelOnly()
                    .build();
            try {
                if (!driver.findElement(productInCart).isDisplayed()) {
                    System.out.println("The product " + product + " is not displayed in the cart page.");
                    return false;
                }
            } catch (NoSuchElementException e) {
                System.out.println("The product " + product + " is not displayed in the cart page.");
            }
        }
        return true;
    }

    public boolean allPricesInCartDisplayed(List<String> products, List<String> prices) {
        if(products.size() != prices.size()) {
            System.out.println("Product and price lists are not the same size!");
            return false;
        }

        for(int i=0; i<products.size(); i++) {
            By priceInCart = new ProductLocatorBuilder(products.get(i))
                    .inCartPagePrice()
                    .build();
            String priceDisplayed;
            try {
                priceDisplayed = driver.findElement(priceInCart).getText();
            } catch (NoSuchElementException e) {
                System.out.println("Price not found for product " + products.get(i));
                return false;
            }
            if(!priceDisplayed.equals(prices.get(i))) {
                System.out.println("Price mistmach for product " + products.get(i) + ". Expected " + prices.get(i) + ", Found " + priceDisplayed);
                return false;
            }
        }
        return true;
    }

    public void clickingCheckout() {
        try {
            driver.findElement(checkoutButtonLocator).click();
        } catch (NoSuchElementException e) {
            System.out.println("Checkout button is not displayed in the page");
        }
    }

    public void clickingContinueShopping() {
        try {
            driver.findElement(continueChopingButtonLocator).click();
        } catch (NoSuchElementException e) {
            System.out.println("Continue Shopping button is not displayed in the page");
        }
    }
    
}
