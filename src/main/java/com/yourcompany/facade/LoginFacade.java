package com.yourcompany.facade;

import org.openqa.selenium.WebDriver;

import com.yourcompany.pages.saucedemopages.LoginPageSauce;
import com.yourcompany.pages.LoginPage;
import com.yourcompany.pages.automationexercisepages.LoginPageAE;
import com.yourcompany.pages.liverpoolpages.LoginPageLiverpool;

/**
 * implements Facade pattern
 * Responsibility: unify access for multi-site login
 * Single Responsibility principle (SRP): each loginPage class is only responsible for handling the login logic of its
 * own site.
 * Open/Close principle (OCP): More login sites can be added just extending the LoginFacade with another method;
 * without modifying the existing login logic of other sites
 * 
 * Dependency Inversion Principle (DIP): High-level modules (like LoginFacade) should depend on abstractions (interfaces), not concrete classes;
 * LoginFacade use the LoginPage interface
 */
public class LoginFacade {
    private WebDriver driver;

    public LoginFacade(WebDriver driver) {
        this.driver = driver;
    }

    public void loginToSauce(String user, String pass) {
        LoginPage loginPage = new LoginPageSauce(driver);
        loginPage.login(user, pass);
    }

    public void loginToAE(String user, String pass) {
        LoginPage loginPage = new LoginPageAE(driver);
        loginPage.login(user, pass);
    }

    public void loginToLiverpool(String user, String pass) {
        LoginPage loginPage = new LoginPageLiverpool(driver);
        loginPage.login(user, pass);
    }
}