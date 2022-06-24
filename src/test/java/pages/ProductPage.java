package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    By price=By.xpath("//*[@id=\"shopify-section-product\"]/section/div/div[2]/div/p/span[1]/span");

    //By addToCartButton=By.xpath("//*[@id=\"product_form_6933429354549\"]/div[4]/input");
    By addToCartButton=By.className("add-to-cart");

    By firstProductQuantity=By.xpath("//*[@id=\"shopify-section-header\"]/section/header/div[1]/div/div[2]/div[2]/div/div[2]/article/div/p[1]/span");

    By two_ThreeYearsButton=By.xpath("//*[@id=\"bcpo-select-option-0\"]/div[3]/label");
    By blueJacketButton=By.xpath("//*[@id=\"bcpo-select-option-1\"]/div[2]/label");
    By cartButton=By.className("cart-count-text");
   public String priceCheck(){
      String price1 =driver.findElement(price).getText();
        return  price1;
   }
    public void addToCart(){
       driver.findElement(addToCartButton).click();
    }
    public String quantityCheck(){
      String quantity= driver.findElement(firstProductQuantity).getText();
       return quantity;
    }
    public void openCart(){
       driver.findElement(cartButton).click();
    }
    public void clicktwoThreeYearsButton(){
       driver.findElement(two_ThreeYearsButton).click();
    }
    public void clickBlueJacketButton(){
       driver.findElement(blueJacketButton).click();
    }

}
