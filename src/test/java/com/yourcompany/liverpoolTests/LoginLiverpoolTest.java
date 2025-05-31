package com.yourcompany.liverpoolTests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.SkipException;

import com.yourcompany.base.BaseTest;
import com.yourcompany.facade.LoginFacade;
import com.yourcompany.pages.liverpoolpages.LoginPageLiverpool;
import com.yourcompany.constants.TestData;

public class LoginLiverpoolTest extends BaseTest {
    @Test
    public void testLiverpoolLogin() {
        driver.get(TestData.LIVERPOOL_URL);

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        new LoginFacade(driver).loginToLiverpool(TestData.LIVERPOOL_USER, TestData.LIVERPOOL_PASS);

        LoginPageLiverpool loginPageLiverpool = new LoginPageLiverpool(driver);

        if (loginPageLiverpool.isValidationPromptPresent()) {
            throw new SkipException("null");
        }

        String expectedUrl = TestData.LIVERPOOL_URL + "tienda/home";
        String currentUrl = driver.getCurrentUrl();

        Assert.assertEquals(currentUrl, expectedUrl, "The user should be redirected to liverpool homepage");
    }
}