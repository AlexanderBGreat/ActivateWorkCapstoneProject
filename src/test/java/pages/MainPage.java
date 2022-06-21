package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
WebDriver driver;
By searchField = By.name("q");
By searchButton = By.className("header-search-button");

By products= By.id("navigation-shop");

By firstProduct= By.linkText("Elephant Opposites Early Education Cloth Book");
By  account= By.id("customer_login_link");
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void findProduct(String arg) {
        driver.findElement(searchField).click();
        driver.findElement(searchField).sendKeys(arg);
    }
    public void accountSignIn(){
        driver.findElement(account).click();
        }
    public void clickSearchButton(){
        driver.findElement(searchButton).click();
    }

    public void allProducts(){
        driver.findElement(products).click();
    }
    public void firstProduct(){
        driver.findElement(firstProduct).click();
    }



}
