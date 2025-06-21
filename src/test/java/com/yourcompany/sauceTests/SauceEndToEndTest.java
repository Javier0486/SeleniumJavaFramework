package com.yourcompany.sauceTests;

import org.testng.annotations.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import com.yourcompany.base.BaseTest;
import com.yourcompany.facade.LoginFacade;
import com.yourcompany.pages.saucedemopages.CartPage;
import com.yourcompany.pages.saucedemopages.SauceCheckoutCompletePage;
import com.yourcompany.pages.saucedemopages.SauceCheckoutInfoPage;
import com.yourcompany.pages.saucedemopages.SauceCheckoutOverview;
import com.yourcompany.pages.saucedemopages.SauceHomePage;
import com.yourcompany.utils.ScreenshotUtil;
import com.yourcompany.constants.TestData;

public class SauceEndToEndTest extends BaseTest {
    @Test
    public void endToEndTest() {
        System.out.println("Login to sauce Page");
        driver.get(TestData.SAUCE_URL);
        new LoginFacade(driver).loginToSauce(TestData.SAUCE_USER, TestData.SAUCE_PASS);
        String pathHomepage = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + "homepage.png";
        ScreenshotUtil.TakeScreenshot(driver, pathHomepage);
        test.addScreenCaptureFromPath(pathHomepage, "Home page");

        List<String> productsToSelect = Arrays.asList(
            "Sauce Labs Backpack",
            "Sauce Labs Bike Light",
            "Sauce Labs Bolt T-Shirt"
        );
        String firstName = "Francisco";
        String lastName = "Ferrero";
        String zipCode = "85114";

        SauceHomePage homePage = new SauceHomePage(driver);
        System.out.println("Adding products to the cart");
        homePage.addProductToCart(productsToSelect);
        
        System.out.println("getting the prices from Homepage");
        List<String> productPrices = homePage.getPrices(productsToSelect);
        
        System.out.println("Navigating to Cart page");
        homePage.clickCartIcon();
        String pathCartpage = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + "cartpage.png";
        ScreenshotUtil.TakeScreenshot(driver, pathCartpage);
        test.addScreenCaptureFromPath(pathCartpage, "Cart page");

        CartPage cartPage = new CartPage(driver);
        System.out.println("Validating all products added to the cart in Homepage are displayed in the Cart page");
        Assert.assertTrue(cartPage.allProductsInCartDisplayed(productsToSelect), "Not all products are displayed in the cart page!");

        System.out.println("Validationg prices are displayed correctly in Cart page");
        Assert.assertTrue(cartPage.allPricesInCartDisplayed(productsToSelect, productPrices), "Prices are not displayed in the cart page");

        System.out.println("Clicking checkout button");
        cartPage.clickingCheckout();

        SauceCheckoutInfoPage checkouInfoPage = new SauceCheckoutInfoPage(driver);
        System.out.println("Validate the info inputs are displayed in the Checkout Your Information page");
        Assert.assertTrue(checkouInfoPage.infoElementsDisplayed());
        String pathcheckoutInfo = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + "checkoutinfopage.png";
        ScreenshotUtil.TakeScreenshot(driver, pathcheckoutInfo);
        test.addScreenCaptureFromPath(pathcheckoutInfo, "Checkout Info page");

        System.out.println("Filling the fields and clicking continue button");
        checkouInfoPage.fillAndContinue(firstName, lastName, zipCode);

        SauceCheckoutOverview sauceOverview = new SauceCheckoutOverview(driver);        
        System.out.println("Validate the products with the expected prices are displayed in the Checkout Overview page");
        Assert.assertTrue(sauceOverview.validateProductWithPriceDisplayed(productsToSelect, productPrices), "There are mismatched or missed products and prices");
        String pathOverview = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + "checkoutOverview.png";
        ScreenshotUtil.TakeScreenshot(driver, pathOverview);
        test.addScreenCaptureFromPath(pathOverview, "Checkout overview page");

        SauceCheckoutCompletePage sauceCompletePage = new SauceCheckoutCompletePage(driver);
        System.out.println("Click Finish and validate the messages");
        sauceOverview.clickFinish();
        Assert.assertTrue(sauceCompletePage.thanksMessages(), "The thanks message is not complete in the Complete page");
        String pathGreetingsMsg = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + "loginpage.png";
        ScreenshotUtil.TakeScreenshot(driver, pathGreetingsMsg);
        test.addScreenCaptureFromPath(pathGreetingsMsg, "End of flow - greetings messages");

        System.out.println("Logout from the app and validate login page is displayed");
        new LoginFacade(driver).logoutFromSauce();
        String expectedUrl = TestData.SAUCE_URL;
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedUrl);
        String screenshotPath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + "loginpage.png";
        ScreenshotUtil.TakeScreenshot(driver, screenshotPath);
        test.addScreenCaptureFromPath(screenshotPath, "Login page after logout");
    }
}
