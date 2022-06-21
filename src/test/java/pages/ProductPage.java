package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    By price=By.xpath("//*[@id=\"shopify-section-product\"]/section/div/div[2]/div/p/span[1]/span");

   public String priceCheck(){
       driver.findElement(price).getText();
        return  driver.findElement(price).getText();
   }
}
