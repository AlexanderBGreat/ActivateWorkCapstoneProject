package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sun.awt.windows.WEmbeddedFrame;

public class CheckoutPage {
WebDriver driver;


//*//iframe[contains(@id,'card-fields-iframe')]
    //"//*[@id='card-fields-number-pja9822tlkg00000-scope-www.alexandnova.com']"
    By cardNameFrame = By.className("card-fields-iframe");
    By nameCardFrame = By.xpath("/html/body/div/div/div/main/div[1]/div/form/div[1]/div[2]/div[2]/fieldset/div[3]/div[3]/div[2]/div/div/iframe");

    By expirationDateFrame = By.xpath("/html/body/div/div/div/main/div[1]/div/form/div[1]/div[2]/div[2]/fieldset/div[3]/div[3]/div[3]/div/div/iframe");
    By securityCodeFrame= By.xpath("/html/body/div/div/div/main/div[1]/div/form/div[1]/div[2]/div[2]/fieldset/div[3]/div[3]/div[4]/div/div[1]/iframe");
    By discountField = By.id("checkout_reduction_code");
    By applyDiscountButton = By.name("checkout[submit]");
    By discountConfirmation = By.className("reduction-code__text");
    By emailField = By.name("checkout[email]");
    By firstNameField = By.id("checkout_shipping_address_first_name");
    By lastNameField = By.id("checkout_shipping_address_last_name");
    By shippingAddressField = By.id("checkout_shipping_address_address1");
    By cityField = By.id("checkout_shipping_address_city");
    By zipcodeField = By.id("checkout_shipping_address_zip");
    By continueToShippingButton = By.id("continue_button");
    By continueToPaymentButton = By.id("continue_button");
    By visaIcon = By.xpath("/html/body/div/div/div/main/div[1]/div/form/div[1]/div[2]/div[2]/fieldset/div[1]/div[2]/div/ul/li[1]");
    By mastercardIcon = By.xpath("/html/body/div/div/div/main/div[1]/div/form/div[1]/div[2]/div[2]/fieldset/div[1]/div[2]/div/ul/li[2]");
    By paypalIcon=By.xpath(("/html/body/div/div/div/main/div[1]/div/form/div[1]/div[2]/div[2]/fieldset/div[6]/div[2]/label/img"));
    By shoppayIcon=By.xpath("/html/body/div/div/div/main/div[1]/div/form/div[1]/div[2]/div[2]/fieldset/div[4]/div[2]/label/img");
    By errorNotice = By.xpath("/html/body/div/div/div/main/div[1]/div/form/div[1]/div[2]/div[2]/div/p");
    By cardNumberField = By.id("number");
    By nameOnCardField = By.id("name");
    By expirationDateField = By.id("expiry");
    By securityCodeField = By.id("verification_value");
    By payNowButton = By.id("continue_button");
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterDiscountcode(String arg){
        driver.findElement(discountField).sendKeys(arg);
    }
    public void applyDiscount(){
        driver.findElement(applyDiscountButton).click();
    }

    public String checkDiscountApplied(){
       String discount=driver.findElement(discountConfirmation).getText();
        return discount;
    }
    public void enterEmail(String arg){
        driver.findElement(emailField).click();
        driver.findElement(emailField).sendKeys(arg);
    }
    public void enterFirstName(String arg){
        driver.findElement(firstNameField).click();
        driver.findElement(firstNameField).sendKeys(arg);
    }
    public void enterLastName(String arg){
        driver.findElement(lastNameField).click();
        driver.findElement(lastNameField).sendKeys(arg);
    }
    public void enterAddress(String arg){
        driver.findElement(shippingAddressField).click();
        driver.findElement(shippingAddressField).sendKeys(arg);
    }
    public void enterCity(String arg){
        driver.findElement(cityField).click();
        driver.findElement(cityField).sendKeys(arg);
    }
    public void enterZipcode(String arg){
        driver.findElement(zipcodeField).click();
        driver.findElement(zipcodeField).sendKeys(arg);
    }

    public void clickContinueToShipping(){
        driver.findElement(continueToShippingButton).click();
    }
    public void clickContinueToPayment(){
        driver.findElement(continueToPaymentButton).click();
    }
   public boolean checkForVisa(){
        boolean t = driver.findElement((visaIcon)).isDisplayed();
        return t;
  }
    public boolean checkForMastercard(){
        boolean t = driver.findElement((mastercardIcon)).isDisplayed();
        return t;
    }
    public boolean checkForPaypal(){
        boolean t = driver.findElement((paypalIcon)).isDisplayed();
        return t;

    }
    public boolean checkForShoppay() {
        boolean t = driver.findElement((shoppayIcon)).isDisplayed();
        return t;
    }
    public String getErrorNotice(){
        String error=driver.findElement(errorNotice).getText();
        return error;
    }
    public void switchToCardFrame(){
        WebElement iframe=driver.findElement(cardNameFrame);
        driver.switchTo().frame(iframe);
    }
    public void setCardNumber(String arg){
        driver.findElement(cardNumberField).click();
        driver.findElement(cardNumberField).sendKeys(arg);
    }
    public void switchToNameFrame() {
        WebElement iframe = driver.findElement(nameCardFrame);
        driver.switchTo().frame(iframe);
    }
    public void setNameOnCard(String arg){
    driver.findElement(nameOnCardField).click();
    driver.findElement(nameOnCardField).sendKeys(arg);
    }
    public void setExpDate(String arg){
        driver.findElement(expirationDateField).click();
        driver.findElement(expirationDateField).sendKeys(arg);
    }
    public void setSecurityCode(String arg){
        driver.findElement(securityCodeField).click();
        driver.findElement(securityCodeField).sendKeys(arg);
    }

    public void switchToExpirationDateFrame(){
        WebElement iframe = driver.findElement(expirationDateFrame);
        driver.switchTo().frame(iframe);
    }
    public void switchToSecurityCodeFrame() {
        WebElement iframe = driver.findElement(securityCodeFrame);
        driver.switchTo().frame(iframe);
    }

    public void clickPayNowButton(){
        driver.findElement(payNowButton).click();
    }
}
