package com.yourcompany.base;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import com.yourcompany.driver.DriverManager;
import com.yourcompany.reports.ExtentManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

/**
 * Implements: Singleton Pattern. Use singleton for classes like DriverManager to ensure only one WebDriver instance exists.
 */
public class BaseTest {
    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void setUp(Method method) {
        driver = DriverManager.getInstance().getDriver();
        driver.manage().window().maximize();
        test = extent.createTest(method.getName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed");
        }
        DriverManager.getInstance().quitDriver();
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }
}