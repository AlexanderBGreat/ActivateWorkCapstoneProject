package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {

WebDriver driver;
By firstNameField = By.xpath("//div/label[text()='First name']/following-sibling::input");
//By firstNameField = By.id("ispbxii_1");
By pageTitle = By.className("page-title");
By lastNameField = By.xpath("//div/label[text()='Last name']/following-sibling::input");

By emailField = By.xpath("//div/label[text()='Email']/following-sibling::input");
By passwordField= By.xpath("//div/label[text()='Password']/following-sibling::input");

By submitButton = By.xpath("//*[@id=\"create_customer\"]/div[5]/input");

By accountButton = By.id("customer_login_link");

By errorMessage = By.xpath("//*[@id='customer_login']/p");

By greetingMessage = By.xpath("//*[@id=\"keyboard-nav-3\"]/h1");
////*[@id="shopify-section-header"]/section/header/div[1]/div/div[2]/div[1]/a
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public String pageTitle(){
 String title=driver.findElement(pageTitle).getText();
    return title; }

public void firstNameEntry(String arg){
        driver.findElement(firstNameField).click();
        driver.findElement(firstNameField).sendKeys(arg);

}
    public void lastNameEntry(String arg){
        driver.findElement(lastNameField ).click();
        driver.findElement(lastNameField ).sendKeys(arg);

    }
    public void emailEntry(String arg){
        driver.findElement(emailField ).click();
        driver.findElement(emailField ).sendKeys(arg);
    }
    public void passwordEntry(String arg){
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).sendKeys(arg);

    }
    public void clickSubmitButton(){
   driver.findElement(submitButton).click();
    }
    public void clickAccountButton(){
        driver.findElement(accountButton).click();
    }
    public String checkGreeting(){
       String greeting= driver.findElement(greetingMessage).getText();
       return greeting;
    }
    public String checkErrorMessage(){
        String error = driver.findElement(errorMessage).getText();
        return error;
    }

}
