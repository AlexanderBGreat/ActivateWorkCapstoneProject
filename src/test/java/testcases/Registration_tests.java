package testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import library.SelectBrowser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.ProductPage;
import pages.RegistrationPage;

import java.time.Duration;

public class Registration_tests {
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
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/ProductTests.html");
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
    public void launchBrowser(){
        driver = SelectBrowser.StartBrowser("Chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.alexandnova.com/");
    }

    @Test(priority = 1)
    public void registrationPage_test(){
        test = extent.createTest("test_searchProduct","Test case passed");
        mainPage = new MainPage(driver);
        mainPage.accountSignIn();
        loginPage=new LoginPage(driver);
        loginPage.newRegistration();
        registrationPage=new RegistrationPage(driver);
        String actual =registrationPage.pageTitle();
        String expected="Sign up";
        Assert.assertEquals(expected,actual);
        }
        @Test(priority = 2)
    public void verifyRegisterNewUser_test(){
            test = extent.createTest("test_searchProduct","Test case passed");
            mainPage = new MainPage(driver);
            mainPage.accountSignIn();
            loginPage=new LoginPage(driver);
            loginPage.newRegistration();
            registrationPage=new RegistrationPage(driver);
            registrationPage.firstNameEntry("James");
            registrationPage.lastNameEntry("Smith");
            registrationPage.emailEntry("Test@gmail.com");
            registrationPage.passwordEntry("Test1234");
        }

}
