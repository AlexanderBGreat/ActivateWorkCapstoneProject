package testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import library.SelectBrowser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.CartPage;
import pages.CheckoutPage;
import pages.MainPage;
import pages.ProductPage;

import java.beans.Visibility;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Collections;

public class Checkout_tests {
   WebDriver driver;
    CartPage cartPage;
    ProductPage productPage;

    MainPage mainPage;


    Method method;
    CheckoutPage checkoutPage;
    static ExtentHtmlReporter htmlReporter;
    protected static ExtentReports extent;
    protected static ExtentTest test;
    @BeforeSuite
    public void setUpReport() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/Checkout_Tests.html");
        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "DESKTOP-5G64RED");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Alexander");

        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("CartTestresults");
        htmlReporter.config().setReportName("AddToCart_tests");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
    }

    @BeforeMethod
    public void launchBrowser(){
        driver = SelectBrowser.StartBrowser("Chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.get("https://www.alexandnova.com/");
    }
    @Test(priority = 16)
    public void test_CartDiscountCheck() throws InterruptedException, IOException {
        test = extent.createTest("test_CartDiscountCheck", "Test case passed");
        mainPage = new MainPage(driver);
        mainPage.allProducts();
        mainPage.firstProduct();
        productPage = new ProductPage(driver);
        productPage.addToCart();
        Thread.sleep(1000);
        productPage.openCart();
        cartPage = new CartPage(driver);
        cartPage.goToCheckout();
        checkoutPage =new CheckoutPage(driver);
        checkoutPage.enterDiscountcode("OHBABY15");
        checkoutPage.applyDiscount();
        String actual = checkoutPage.checkDiscountApplied();
        String expected = "OHBABY15";
        Assert.assertEquals(actual,expected);
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("test-output/Screenshots/test_CartDiscountCheck.png"));;
        driver.close();

    }
    @Test(priority = 17)
    public void test_PaymentMethodCheck() throws InterruptedException, IOException {
        test = extent.createTest("test_PaymentMethodCheck", "Test case passed");
        mainPage = new MainPage(driver);
        mainPage.allProducts();
        mainPage.firstProduct();
        productPage = new ProductPage(driver);
        productPage.addToCart();
        Thread.sleep(1000);
        productPage.openCart();
        cartPage = new CartPage(driver);
        cartPage.goToCheckout();
        checkoutPage =new CheckoutPage(driver);

        checkoutPage.enterEmail("Test6544@gmail.com");
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Fink");
        checkoutPage.enterAddress("123 Main St");
        checkoutPage.enterCity("New York City");
        checkoutPage.enterZipcode("80601");

        checkoutPage.clickContinueToShipping();
        Thread.sleep(5000);
        checkoutPage.clickContinueToPayment();
        Assert.assertTrue(checkoutPage.checkForVisa());
        Assert.assertTrue(checkoutPage.checkForMastercard());
        Assert.assertTrue(checkoutPage.checkForPaypal());
        Assert.assertTrue(checkoutPage.checkForShoppay());

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("test-output/Screenshots/test_PaymentMethodCheck.png"));;
        driver.close();

    }
    @Test(priority = 18)
    public void test_NegativePaymentMethodCheck() throws InterruptedException, IOException {
        test = extent.createTest("test_NegativePaymentMethodCheck", "Test case passed");
        mainPage = new MainPage(driver);
        mainPage.allProducts();
        mainPage.firstProduct();
        productPage = new ProductPage(driver);
        productPage.addToCart();
        Thread.sleep(1000);
        productPage.openCart();
        cartPage = new CartPage(driver);
        cartPage.goToCheckout();
        checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterEmail("Test6544@gmail.com");
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Fink");
        checkoutPage.enterAddress("123 Main St");
        checkoutPage.enterCity("New York City");
        checkoutPage.enterZipcode("80601");

        checkoutPage.clickContinueToShipping();
        Thread.sleep(3000);
        checkoutPage.clickContinueToPayment();
        checkoutPage.clickContinueToPayment();
        Thread.sleep(5000);
        String actual = checkoutPage.getErrorNotice();
        String expected ="Your payment details couldn’t be verified. Check your card details and try again.";
        Assert.assertEquals(actual, expected);
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("test-output/Screenshots/test_NegativePaymentMethodCheck.png"));;
        driver.close();

    }

    @Test(priority = 19)
    public void test_PositivePaymentMethodCheck() throws InterruptedException, IOException {
        test = extent.createTest("test_NegativePaymentMethodCheck", "Test case passed");
        mainPage = new MainPage(driver);
        mainPage.allProducts();
        mainPage.firstProduct();
        productPage = new ProductPage(driver);
        productPage.addToCart();
        Thread.sleep(1000);
        productPage.openCart();
        cartPage = new CartPage(driver);
        cartPage.goToCheckout();
        checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterEmail("Test6544@gmail.com");
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Fink");
        checkoutPage.enterAddress("123 Main St");
        checkoutPage.enterCity("New York City");
        checkoutPage.enterZipcode("80601");

        checkoutPage.clickContinueToShipping();
        Thread.sleep(3000);
        checkoutPage.clickContinueToPayment();

        checkoutPage.switchToCardFrame();
        checkoutPage.setCardNumber("3563");
        checkoutPage.setCardNumber("4255");
        checkoutPage.setCardNumber("7346");
        checkoutPage.setCardNumber("3127");

        driver.switchTo().parentFrame();
        checkoutPage.switchToNameFrame();
        checkoutPage.setNameOnCard("John Fink");

        driver.switchTo().parentFrame();
        checkoutPage.switchToExpirationDateFrame();
        checkoutPage.setExpDate("06");
        checkoutPage.setExpDate("2024");

        driver.switchTo().parentFrame();
        checkoutPage.switchToSecurityCodeFrame();
        checkoutPage.setSecurityCode("707");

        driver.switchTo().parentFrame();
        checkoutPage.clickPayNowButton();

        String actual = checkoutPage.getErrorNotice();
        String expected ="Your payment details couldn’t be verified. Check your card details and try again.";
        Assert.assertEquals(actual, expected);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("test-output/Screenshots/test_PositivePaymentMethodCheck.png"));;
        driver.close();

    }
    @AfterMethod
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
        } else {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
            test.skip(result.getThrowable());

        }
    }
    @AfterSuite
    public void tearDown()
    {
        extent.flush();
    }

}
