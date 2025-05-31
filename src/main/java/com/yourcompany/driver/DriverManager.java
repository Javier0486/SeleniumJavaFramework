package com.yourcompany.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
/**
 * Implements: Singleton Pattern. Use singleton for classes like DriverManager to ensure only one WebDriver instance exists.
 * Single Responsibility principle (SRP) implemented
 */
public class DriverManager {
    private static DriverManager instance;
    private static WebDriver driver;

    private DriverManager(){
        // here WebDriver setup can be added
        driver = new ChromeDriver();
    }

    public static DriverManager getInstance() {
        if(driver == null) {
            synchronized (DriverManager.class) {
                if (instance == null) {
                    instance = new DriverManager();
                }
            }
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quitDriver() {
        if(driver != null) {
            driver.quit();
            driver = null;
        }
        instance = null;
    }
}