package com.yourcompany.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Utility Pattern
 * Single Responsibility Principle (SRP)
 */
public class ScreenshotUtil {

    public static void TakeScreenshot(WebDriver driver, String filePath) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(screenshot.toPath(), Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}