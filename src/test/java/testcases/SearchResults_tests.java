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
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.MainPage;
import pages.SearchResultsPage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SearchResults_tests {

    WebDriver driver;
    SearchResultsPage searchResultsPage;

    MainPage mainPage;
    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;
    private static ExtentTest test;
    WebElement webElement;
    @BeforeSuite
    public void setUp()
    {

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/SearchFeature_tests.html");
        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "DESKTOP-5G64RED");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Alexander");
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("AutomationTesting Demo Report");
        htmlReporter.config().setReportName("My Own Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setTheme(Theme.STANDARD);
    }
    @BeforeMethod
    public void browserLauncher() {
        //Change browsername if you want to test other browser
        driver = SelectBrowser.StartBrowser("Chrome");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.alexandnova.com/");
    }



    @Test(priority = 8)
    public void test_searchProduct() throws IOException {     test = extent.createTest("test_searchProduct","Test case passed");
        mainPage = new MainPage(driver);
        mainPage.findProduct("baby shoes");
        mainPage.clickSearchButton();

        String expectedSearchResult ="Search results";
        searchResultsPage = new SearchResultsPage(driver);
        String actual= searchResultsPage.pageTitle();
        Assert.assertEquals(actual, expectedSearchResult);
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("test-output/Screenshots/test_searchProduct.png"));;
        driver.close();
    }
    @Test(priority = 9)
    public void test_emptySearch() throws IOException {
        test=extent.createTest("test_emptySearch","Test case passed" );
        mainPage=new MainPage(driver);
        mainPage.clickSearchButton();
        searchResultsPage=new SearchResultsPage(driver);

        String expectedSearchResult ="Search";
        String actual= String.valueOf(searchResultsPage.pageTitle());
        Assert.assertEquals(actual,expectedSearchResult);
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("test-output/Screenshots/test_emptySearch.png"));;
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
