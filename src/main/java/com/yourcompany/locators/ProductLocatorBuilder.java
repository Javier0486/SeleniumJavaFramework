package com.yourcompany.locators;

import org.openqa.selenium.By;

public class ProductLocatorBuilder {
    private final String productName;
    private String suffix = ""; // default to base locator only

    public ProductLocatorBuilder(String productName) {
        this.productName = productName;
    }

    public String getBaseLocator() {
        return String.format("//div[contains(text(), '%s')]", productName);
    }

    public ProductLocatorBuilder inHomePagePrice() {
        this.suffix = "/ancestor::div[@class='inventory_item_description']//div[@class='inventory_item_price']";
        return this;
    }

    public ProductLocatorBuilder inHomePageAddToCart() {
        this.suffix = String.format("/ancestor::div[@class='inventory_item_description']//div//button[@id='add-to-cart-%s']", productName.toLowerCase().replace(" ", "-"));
        return this;
    }

    public ProductLocatorBuilder inCartPagePrice() {
        this.suffix = "/ancestor::div[@class='cart_item_label']//div[@class='inventory_item_price']";
        return this;
    }

    public ProductLocatorBuilder inOverviewPagePrice() {
        this.suffix = "/ancestor::div[@class='cart_item_label']//div[@class='inventory_item_price']";
        return this;
    }

    public ProductLocatorBuilder inCartPageRemoveButton() {
        this.suffix = String.format("/ancestor::div[@class='cart_item_label']//button[@id='remove-%s']", productName.toLowerCase().replace(" ", "-"));
        return this;
    }

    public ProductLocatorBuilder asLabelOnly() {
        this.suffix = ""; // just the base productlabel
        return this;
    }

    public By build() {
        return By.xpath(getBaseLocator() + suffix);
    }
}
