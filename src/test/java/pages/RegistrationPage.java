package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {

WebDriver driver;
By firstNameField = By.id("ispbxii_1");
By pageTitle = By.className("page-title");
By lastNameField = By.id("ispbxii_2");

By emailField = By.id("ispbxii_3");
By passwordField= By.name("customer[password]");

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


}
