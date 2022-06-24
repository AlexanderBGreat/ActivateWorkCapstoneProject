package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultsPage {
    WebDriver driver;

    //page factory
    By pageTitle = By.xpath("//*[@id=\"keyboard-nav-3\"]/h1");

    public String pageTitle(){

        String title=driver.findElement(pageTitle).getText();
        return title; }




    public SearchResultsPage(WebDriver driver){
        this.driver=driver;

    }
}
