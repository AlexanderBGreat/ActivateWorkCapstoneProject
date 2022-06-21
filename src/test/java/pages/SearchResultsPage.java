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
    By pageTitle = By.className("page-title");

    public String pageTitle(){

        String title=driver.findElement(pageTitle).getText();
        return title; }


    @FindBy(xpath = "$x(\"//li[@class='x-refine__main__list--value'][1]/div/a/div/span/input\")\n")
    List<WebElement> checkButtonList;

    public SearchResultsPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
}
