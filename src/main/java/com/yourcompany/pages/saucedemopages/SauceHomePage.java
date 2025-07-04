package com.yourcompany.pages.saucedemopages;

import com.yourcompany.locators.ProductLocatorBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SauceHomePage {
    private WebDriver driver;

    //locators
    private static final By CartLogoLocator = By.className("shopping_cart_badge");
    

    public SauceHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void addProductToCart(List<String> products) {
        for (String product : products) {
            By addToCartLocator = new ProductLocatorBuilder(product)
                        .inHomePageAddToCart()
                        .build();
            try {
                driver.findElement(addToCartLocator).click();
            } catch (NoSuchElementException e) {
                System.out.println(product + " not found in the catalog");
            }
        }
    }
    
    public List<String> getPrices(List<String> products) {
        List<String> pricesList = new ArrayList<>();

        for (String product : products) {
            By getProductPrices = new ProductLocatorBuilder(product)
                        .inHomePagePrice()
                        .build();
            try {
                pricesList.add(driver.findElement(getProductPrices).getText());
            } catch (NoSuchElementException e) {
                System.out.println("Price not dounf for product: " + product);
                pricesList.add("N/A");
            }
        }
        return pricesList;
    }

    public void clickCartIcon() {
        driver.findElement(CartLogoLocator).click();
    }

}
