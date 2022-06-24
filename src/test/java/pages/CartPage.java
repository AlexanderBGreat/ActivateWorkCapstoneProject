package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    By cartCount= By.className("cart-count-number");
    By quantity= By.id("updates_41212298166325");

    By updateCartButton= By.xpath("//*[@id=\"shopify-section-cart\"]/section/form/div/div/div[2]/input");

    By emptyMessage= By.partialLinkText("You don't");

    By oldPrice =By.xpath("//*[@id=\"shopify-section-cart\"]/section/form/div/div/p/div/p[1]/span/span");

    By productPrice= By.xpath("//*[@id=\"shopify-section-cart\"]/section/form/table/tbody/tr/td[2]/span/span");

    By checkoutButton=By.name("checkout");
    By productDescription=By.className("cart-variant");

    public String quantityUpdate(){
       String count= driver.findElement(cartCount).getText();
       return count;
    }
    public void setQuantity(String arg){
        driver.findElement(quantity).clear();
        driver.findElement(quantity).sendKeys(arg);
    }
    public void updateCart(){
       driver.findElement(updateCartButton).click();}

    public String checkEmptyMessage(){
       String message= driver.findElement(emptyMessage).getText();
       return message;
    }

    public String getOldPrice(){
        String oldprice= driver.findElement(oldPrice).getText();
        return oldprice;
    }
    public void goToCheckout(){
        driver.findElement(checkoutButton).click();
    }
    public String getProductDescription(){
        String actual=driver.findElement(productDescription).getText();
        return actual;
    }
   /* public Integer findTotalBeforeDiscount(){
       Integer count= Integer.valueOf(driver.findElement(cartCount).getText());
       Integer price= Integer.valueOf(driver.findElement(productPrice).getText());
        Integer priceBeforeDiscount=Integer.valueOf(count*price);
        return priceBeforeDiscount;
   }*/
}
