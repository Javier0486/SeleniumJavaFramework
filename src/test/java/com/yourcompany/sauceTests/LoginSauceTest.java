package com.yourcompany.sauceTests;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.yourcompany.base.BaseTest;
import com.yourcompany.facade.LoginFacade;
import com.yourcompany.constants.TestData;

public class LoginSauceTest extends BaseTest {
    @Test
    public void testSauceLogin() {
        driver.get(TestData.SAUCE_URL);
        new LoginFacade(driver).loginToSauce(TestData.SAUCE_USER, TestData.SAUCE_PASS);

        String expectedUrl = TestData.SAUCE_URL + "inventory.html";
        String currentUrl = driver.getCurrentUrl();

        Assert.assertEquals(currentUrl, expectedUrl, "The user should be redirected to inventory page after login");
    }   
}