package com.yourcompany.locators;

import org.openqa.selenium.By;

/**
 * Centralizes all login/logout element locators for each site.
 * Usage: Page classes (e.g. LoginPageSauce) reference locators from LoginLocators instead of hardcoding them.
 */
public class LoginLocators {
    public static class SauceDemo {
        public static final By USERNAME = By.id("user-name");
        public static final By PASSWORD = By.id("password");
        public static final By LOGIN_BUTTON = By.id("login-button");
    }

    public static class AutomationExercise {
        public static final By HEADERSIGNLOG = By.xpath("//a[normalize-space(text())='Signup / Login']");
        public static final By USERNAME = By.name("email");
        public static final By PASSWORD = By.name("password");
        public static final By LOGIN_BUTTON = By.xpath("//button[normalize-space(text())='Login']");
    }

    public static class Liverpool {
        public static final By HEADER_LOGIN = By.className("user-button");
        public static final By LOGIN_HIDEN_HEADER = By.xpath("//a[normalize-space(text())='Iniciar sesión']");
        public static final By LOGOUT = By.xpath("//a[normalize-space(text())='Cerrar sesión'][contains(@href, 'logout')]");
        public static final By USERNAME = By.id("username");
        public static final By PASSWORD = By.id("password");
        public static final By LOGIN_BUTTON = By.xpath("//button[normalize-space(text())='Iniciar sesión']");
    }
}
