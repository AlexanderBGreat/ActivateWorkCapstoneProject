package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    By  registerButton= By.xpath("//*[@id=\"keyboard-nav-3\"]/div[2]/div[3]/a")
    ;

    By emailField=By.xpath("//*[@id=\"ispbxii_1\"]");


    By passwordField=By.name("customer[password]");


    By loginButton =By.xpath( "//*[@id=\"customer_login\"]/div[3]/input");

    By pageTitle=By.className("page-title");
   By errorMessage = By.xpath("//*[@id=\"customer_login\"]/p");



    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void newRegistration(){
         driver.findElement(registerButton).click();
    }
    public void enterEmail(String arg){
         driver.findElement(emailField).click();
         driver.findElement(emailField).sendKeys(arg);
    }
    public void enterPassword(String arg){
         driver.findElement(passwordField).click();
       driver.findElement(passwordField).sendKeys(arg);
    }
    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }
    public String getPageTitle(){
       String message= driver.findElement(pageTitle).getText();
       return message;
    }
   public String checkErrorMessage(){
        String error = driver.findElement(errorMessage).getText();
        return error;
    }
}
