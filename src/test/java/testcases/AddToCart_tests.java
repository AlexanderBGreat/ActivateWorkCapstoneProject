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
import org.example.BaseTests;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.CartPage;
import pages.MainPage;
import pages.ProductPage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

public class AddToCart_tests {
WebDriver driver;

    CartPage cartPage;
    ProductPage productPage;

    MainPage mainPage;


    Method method;
    static ExtentHtmlReporter htmlReporter;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/AddToCart_Tests.html");
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




    // SearchResultsPage searchResultsPage;

  /*  @BeforeClass
    public void launchBrowser(){
        driver = SelectBrowser.StartBrowser("Chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.alexandnova.com/");
  } */

    @Test(priority = 10)
    public void test_PriceCheck() throws IOException {
        test = extent.createTest("test_PriceCheck", "Test case passed");
        mainPage = new MainPage(driver);
        mainPage.allProducts();
        mainPage.firstProduct();
        productPage = new ProductPage(driver);
        String Actual_price = productPage.priceCheck();
        String Expected_price = "$29.95 USD";
        Assert.assertEquals(Actual_price, Expected_price);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("test-output/Screenshots/test_PriceCheck.png"));;
        driver.close();
    }


    @Test(priority = 11)
    public void test_CartCheck() throws InterruptedException, IOException {
        test = extent.createTest("test_CartCheck", "Test case passed");
        mainPage = new MainPage(driver);

        mainPage.clickOnJacketPanel();
        productPage = new ProductPage(driver);
        productPage.clicktwoThreeYearsButton();
        productPage.clickBlueJacketButton();
        productPage.addToCart();
        Thread.sleep(1000);
        productPage.openCart();
        cartPage = new CartPage(driver);



        String actual = cartPage.getProductDescription();
        String expected = "2-3 Years / Blue";
        Assert.assertEquals(actual,expected);
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("test-output/Screenshots/test_CartCheck.png"));;
        driver.close();
    }

    @Test(priority = 12)
    public void test_cartRefreshCheck() throws InterruptedException, IOException {
        test = extent.createTest("test_CartRefreshCheck", "Test case passed");
        mainPage = new MainPage(driver);
        mainPage.allProducts();
        mainPage.firstProduct();
        productPage = new ProductPage(driver);
        productPage.addToCart();
        Thread.sleep(1000);
        productPage.openCart();
        cartPage = new CartPage(driver);



        driver.navigate().refresh();
        String actual = cartPage.quantityUpdate();
        String expected = "1";
        Assert.assertEquals(actual,expected);
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("test-output/Screenshots/test_cartRefreshCheck.png"));;
        driver.close();
    }

    @Test(priority = 13)
    public void test_CartQuantityChange() throws InterruptedException, IOException {
        test = extent.createTest("test_CartQuantityChange", "Test case passed");
        mainPage = new MainPage(driver);
        mainPage.allProducts();
        mainPage.firstProduct();
        productPage = new ProductPage(driver);
        productPage.addToCart();
        Thread.sleep(1000);
        productPage.openCart();
        cartPage = new CartPage(driver);



        cartPage.setQuantity("3");
        cartPage.updateCart();
       String actual = cartPage.quantityUpdate();
        String expected = "3";
        Assert.assertEquals(actual,expected);
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("test-output/Screenshots/test_CartQuantityChange.png"));;
        driver.close();
    }
    @Test(priority = 14)
    public void test_CartTotal() throws InterruptedException, IOException {
        test = extent.createTest("test_CartTotal", "Test case passed");
        mainPage = new MainPage(driver);
        mainPage.allProducts();
        mainPage.firstProduct();
        productPage = new ProductPage(driver);
        productPage.addToCart();
        Thread.sleep(1000);
        productPage.openCart();
        cartPage = new CartPage(driver);



        cartPage.setQuantity("3");
        cartPage.updateCart();
        String actual = cartPage.getOldPrice();
        String expected = "$89.85 USD";
        Assert.assertEquals(actual, expected);
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("test-output/Screenshots/test_CartTotal.png"));;
        driver.close();


    }
    @Test(priority = 15)
    public void test_CartZeroQuantityChange() throws InterruptedException, IOException {
        test = extent.createTest("test_CartZeroQuantityChange", "Test case passed");
        mainPage = new MainPage(driver);
        mainPage.allProducts();
        mainPage.firstProduct();
        productPage = new ProductPage(driver);
        productPage.addToCart();
        Thread.sleep(1000);
        productPage.openCart();
        cartPage = new CartPage(driver);



        cartPage.setQuantity("0");
        cartPage.updateCart();
        String actual = cartPage.quantityUpdate();
        String expected = "0";
        Assert.assertEquals(actual,expected);
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("test-output/Screenshots/test_CartZeroQuantityChange.png"));;
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
