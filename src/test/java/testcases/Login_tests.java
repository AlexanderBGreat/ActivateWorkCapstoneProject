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
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.MainPage;
import pages.ProductPage;
import pages.RegistrationPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Login_tests {
    WebDriver driver;
    ProductPage productPage;
    LoginPage loginPage;

    RegistrationPage registrationPage;
    MainPage mainPage;
    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;
    private static ExtentTest test;
    WebElement webElement;


    @BeforeSuite
    public void setUpReport() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/Login_Tests.html");
        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "DESKTOP-5G64RED");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Alexander");

        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("ProductTestReport");
        htmlReporter.config().setReportName("My Own Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
    }


    @BeforeTest
    public void launchBrowser() {
        driver = SelectBrowser.StartBrowser("Chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.alexandnova.com/");
    }

    @Test(priority = 6)
    public void test_verifyLogin() throws InterruptedException, IOException {
        test = extent.createTest("test_searchProduct", "Test case passed");
        mainPage = new MainPage(driver);
        mainPage.accountSignIn();
        loginPage = new LoginPage(driver);
        loginPage.enterEmail("Testdrive@gmail.com");
        Thread.sleep(3000);
        loginPage.enterPassword("Test1234");
        loginPage.clickLoginButton();
        Thread.sleep(15000);

        String actual=loginPage.getPageTitle();
        String expected="Welcome, James";
        Assert.assertEquals(actual, expected);
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("test-output/Screenshots/test_verifyLogin.png"));;
        driver.close();

    }
    @Test(priority = 7)
    public void test_negativeLogin() throws InterruptedException, IOException {
        test = extent.createTest("test_searchProduct", "Test case passed");
        mainPage = new MainPage(driver);
        mainPage.accountSignIn();
        loginPage = new LoginPage(driver);
        loginPage.enterEmail(" ");
        Thread.sleep(5000);
        loginPage.enterPassword("Test1234");
        loginPage.clickLoginButton();
        Thread.sleep(15000);
        String actual=loginPage.checkErrorMessage();
        String expected="Sorry! Please try that again.";
        Assert.assertEquals(actual, expected);
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("test-output/Screenshots/test_negativeLogin.png"));;
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
