package com.yourcompany.automationExeTests;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.yourcompany.base.BaseTest;
import com.yourcompany.facade.LoginFacade;
import com.yourcompany.constants.TestData;

public class LoginAutomationExerciseTest extends BaseTest {
    @Test
    public void testAutomationExerciseLogin() {
        driver.get(TestData.AE_URL);
        new LoginFacade(driver).loginToAE(TestData.AE_USERNAME, TestData.AE_PASSWORD);

        String expectedUrl = TestData.AE_URL;
        String currentUrl = driver.getCurrentUrl();

        Assert.assertEquals(currentUrl, expectedUrl, "The user should be redirect to homepage after login");
    }
}