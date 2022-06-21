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
import org.example.BaseTests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.MainPage;
import pages.ProductPage;

import java.lang.reflect.Method;
import java.time.Duration;

public class AddToCart_tests {
WebDriver driver;


    ProductPage productPage;

    MainPage mainPage;


    Method method;
    static ExtentHtmlReporter htmlReporter;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/AddToCart_Test.html");
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.alexandnova.com/");
    }




    // SearchResultsPage searchResultsPage;

  /*  @BeforeClass
    public void launchBrowser(){
        driver = SelectBrowser.StartBrowser("Chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.alexandnova.com/");
  } */

    @Test(priority = 1)
    public void test_PriceCheck() {
        test = extent.createTest("test_PriceCheck", "Test case passed");
        mainPage = new MainPage(driver);
        mainPage.allProducts();
        mainPage.firstProduct();
        productPage = new ProductPage(driver);
        String Actual_price = productPage.priceCheck();
        String Expected_price = "$29.95 USD";
        Assert.assertEquals(Actual_price, Expected_price);
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
