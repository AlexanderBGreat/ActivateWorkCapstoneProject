package library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SelectBrowser {

    static WebDriver driver;

    public static WebDriver StartBrowser(String browserName){
        // ---If the browser is Firefox----
        if (browserName.equalsIgnoreCase("Firefox"))
        {
            System.setProperty("webdriver.firefox.marionette", "geckodriver-v0.31.0-win64/geckodriver.exe");

            driver = new FirefoxDriver();
        }
        //---- If the browser is Chrome--
        else if(browserName.equalsIgnoreCase("Chrome"))
        {
            System.setProperty("webdriver.chrome.driver", "chromedriver_win32/chromedriver.exe");

            driver = new ChromeDriver();

        }

        // ----If the browser is  EdgeIE----

        else if(browserName.equalsIgnoreCase("EdgeExplore"))
        {
            System.setProperty("webdriver.edge.driver", "edgedriver_win64/msedgedriver.exe");
            EdgeOptions options = new EdgeOptions();
            driver = new EdgeDriver(options);

        }
        driver.manage().window().maximize();
        return driver;

    //WebDriverManager.chromedriver().setup();
        //https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager

    }
}
