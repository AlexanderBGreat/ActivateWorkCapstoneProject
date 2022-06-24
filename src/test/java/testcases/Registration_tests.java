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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/Registration_Tests.html");
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


    @BeforeMethod
    public void launchBrowser(){
        driver = SelectBrowser.StartBrowser("Chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.alexandnova.com/");
    }

    @Test(priority = 1)
    public void test_registrationPage() throws IOException {
        test = extent.createTest("test_searchProduct","Test case passed");
        mainPage = new MainPage(driver);
        mainPage.accountSignIn();
        loginPage=new LoginPage(driver);
        loginPage.newRegistration();
        registrationPage=new RegistrationPage(driver);
        String actual =registrationPage.pageTitle();
        String expected="Sign up";
        Assert.assertEquals(expected,actual);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("test-output/Screenshots/test_registrationPage.png"));;
        driver.close();
        }
        @Test(priority = 2)
    public void test_verifyRegisterNewUser() throws InterruptedException, IOException {
            test = extent.createTest("test_searchProduct","Test case passed");
            mainPage = new MainPage(driver);
            mainPage.accountSignIn();
            loginPage=new LoginPage(driver);
            loginPage.newRegistration();
            registrationPage=new RegistrationPage(driver);
            registrationPage.firstNameEntry("James");
            registrationPage.lastNameEntry("Smith");
            registrationPage.emailEntry("Testsforyou@gmail.com");
            registrationPage.passwordEntry("Test1234");
            registrationPage.clickSubmitButton();
            Thread.sleep(15000);
           driver.navigate().to("https://www.alexandnova.com/account");
           String actual= registrationPage.checkGreeting();
           String expected = "Welcome, James";
           Assert.assertEquals(actual,expected);
            File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("test-output/Screenshots/test_verifyRegisterNewUser.png"));;
            driver.close();

        }
        @Test(priority = 3)
    public void test_emailValidation() throws InterruptedException, IOException {
            test = extent.createTest("test_searchProduct","Test case passed");
            mainPage = new MainPage(driver);
            mainPage.accountSignIn();

            loginPage=new LoginPage(driver);
            loginPage.newRegistration();

            registrationPage=new RegistrationPage(driver);
            registrationPage.firstNameEntry("James");
            registrationPage.lastNameEntry("Smith");
            registrationPage.emailEntry("Testgmail.com");
            registrationPage.passwordEntry("Test1234");
            registrationPage.clickSubmitButton();
            Thread.sleep(15000);
            String actual = registrationPage.checkErrorMessage();
            String expected ="Sorry! Please try that again.";
            Assert.assertEquals(actual,expected);
          //  WebDriverWait wait = new WebDriverWait(driver, 20);
         //  WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message banner")));
            File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("test-output/Screenshots/test_emailValidation.png"));;
            driver.close();

        }
        @Test(priority = 4)
    public void test_negativeValidation() throws IOException, InterruptedException {
            test = extent.createTest("test_searchProduct","Test case passed");
            mainPage = new MainPage(driver);
            mainPage.accountSignIn();

            loginPage=new LoginPage(driver);
            loginPage.newRegistration();

            registrationPage=new RegistrationPage(driver);

            registrationPage.clickSubmitButton();
            Thread.sleep(15000);

            String actual = registrationPage.checkErrorMessage();
            String expected ="Sorry! Please try that again.";
            Assert.assertEquals(actual,expected);
            File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("test-output/Screenshots/test_negativeValidation.png"));;
            driver.close();
        }

        @Test(priority = 5)
        public void test_passwordValidation() throws IOException, InterruptedException {
            test = extent.createTest("test_searchProduct","Test case passed");
            mainPage = new MainPage(driver);
            mainPage.accountSignIn();

            loginPage=new LoginPage(driver);
            loginPage.newRegistration();

            registrationPage=new RegistrationPage(driver);
            registrationPage.firstNameEntry("James");
            registrationPage.lastNameEntry("Smith");
            registrationPage.emailEntry("Test@gmail.com");
            registrationPage.passwordEntry("passw");
            registrationPage.clickSubmitButton();
            Thread.sleep(15000);
            String actual = registrationPage.checkErrorMessage();
            String expected ="Sorry! Please try that again.";
            Assert.assertEquals(actual,expected);
            File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("test-output/Screenshots/test_passwordValidation.png"));;
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
