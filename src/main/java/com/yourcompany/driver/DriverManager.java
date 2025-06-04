package com.yourcompany.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
/**
 * Implements: Singleton Pattern. Use singleton for classes like DriverManager to ensure only one WebDriver instance exists.
 * Single Responsibility principle (SRP) implemented
 */
public class DriverManager {
    private static DriverManager instance;
    private static WebDriver driver;

    private DriverManager(){
        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("profile.default_content_setting_values.popups", 2);
        prefs.put("credentials_enable_service" , false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);

        options.addArguments("--password-store=basic");
        options.addArguments("--disable-features=PasswordLeakDetection");
        options.addArguments("--incognito");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--user-data-dir=/tmp/temporary-chrome-profile");

        driver = new ChromeDriver(options);
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