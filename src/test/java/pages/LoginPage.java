package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;
    By registerButton = By.xpath("//*[@id=\"keyboard-nav-3\"]/div[2]/div[3]/a");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void newRegistration(){
        driver.findElement(registerButton).click();
    }
}
