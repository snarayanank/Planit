package com.planittesting.cloud.jupiter.pageObjects;

import com.planittesting.cloud.jupiter.dao.ShopProfile;
import com.planittesting.cloud.jupiter.utilities.Constants;
import com.planittesting.cloud.jupiter.utilities.Helper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class CartPage {
    public WebDriver driver;
    public static Properties prop;
    WebDriverWait wait;
    private static Logger log = LogManager.getLogger(CartPage.class.getName());

    public CartPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 20);
    }

    @FindBy(xpath= "//p[@class='cart-msg']")
    WebElement cartMessage;

    @FindBy(xpath= "//strong[@class='total ng-binding']")
    WebElement cartTotal;

    @FindBy(xpath= "//p[@class='cart-msg']//span[@class='cart-count ng-binding']")
    WebElement cartCount;

    @FindBy(xpath= "//table[1]/tbody[1]/tr[2]/td[3]/input[1]")
    WebElement itemQuantity;

    public WebElement getProductByName(String productName){
        String newXPath = "//td[contains(text(),'Dummy Toy')]";
        newXPath= newXPath.replace("Dummy Toy", productName);
        return driver.findElement(By.xpath(newXPath));
    }

    public WebElement getProductPrice(String productName) {
        WebElement productScope = driver.findElement(By.xpath("//table[@class='table table-striped cart-items']//tbody"));
        List<WebElement> itemListCount = productScope.findElements(By.tagName("tr"));
        WebElement itemPrice = productScope.findElement(By.xpath("//table[1]/tbody[1]/tr[1]/td[2]"));
        for (int i = 1; i <= itemListCount.size(); i++) {
            WebElement itemName = productScope.findElement(By.xpath("//table[1]/tbody[1]/tr[" + i + "]/td[1]"));
            if(itemName.getText().contains(productName)) {
                itemPrice = productScope.findElement(By.xpath("//table[1]/tbody[1]/tr[" + i + "]/td[2]"));
                break;
            }
        }
        return itemPrice;
    }

    public WebElement getProductQty(String productName) {
        WebElement productScope = driver.findElement(By.xpath("//table[@class='table table-striped cart-items']//tbody"));
        List<WebElement> itemListCount = productScope.findElements(By.tagName("tr"));
        WebElement itemQty = productScope.findElement(By.xpath("//table[1]/tbody[1]/tr[1]/td[3]/input[1]"));
        for (int i = 1; i <= itemListCount.size(); i++) {
            WebElement itemName = productScope.findElement(By.xpath("//table[1]/tbody[1]/tr[" + i + "]/td[1]"));
            if(itemName.getText().contains(productName)) {
                itemQty = productScope.findElement(By.xpath("//table[1]/tbody[1]/tr[" + i + "]/td[3]/input[1]"));
                break;
            }
        }
        return itemQty;
    }

    public WebElement getProductSubtotal(String productName) {
        WebElement productScope = driver.findElement(By.xpath("//table[@class='table table-striped cart-items']//tbody"));
        List<WebElement> itemListCount = productScope.findElements(By.tagName("tr"));
        WebElement itemSubtotal = productScope.findElement(By.xpath("//table[1]/tbody[1]/tr[1]/td[4]"));
        for (int i = 1; i <= itemListCount.size(); i++) {
            WebElement itemName = productScope.findElement(By.xpath("//table[1]/tbody[1]/tr[" + i + "]/td[1]"));
            if(itemName.getText().contains(productName)) {
                itemSubtotal = productScope.findElement(By.xpath("//table[1]/tbody[1]/tr[" + i + "]/td[4]"));
                break;
            }
        }
        return itemSubtotal;
    }

    public void verifyCartCount(List<ShopProfile> itemList){
        int totalItemCount=0;
        int itemQty;
        wait.until(ExpectedConditions.visibilityOf(cartCount));
        for(ShopProfile s : itemList ) {
            Assert.assertTrue(getProductByName(s.getProductName()).isDisplayed());
            itemQty=Integer.parseInt(getProductQty(s.getProductName()).getAttribute("value"));
            Assert.assertEquals(s.getProductQuantity().intValue(),itemQty);
            log.info(s.getProductQuantity() + " quantity of " + s.getProductName() + " is added to cart");
            totalItemCount=totalItemCount+itemQty;
        }
        Assert.assertEquals(String.valueOf(totalItemCount), cartCount.getText());
        log.info("Total number of items added to cart is " + cartCount.getText());
    }

    public void verifyCartPriceTotal(List<ShopProfile> itemList){
        int itemQty;
        float itemPrice,totalPrice=0;
        String subTotal;
        wait.until(ExpectedConditions.visibilityOf(cartCount));
        for(ShopProfile s : itemList ) {
            itemPrice = Float.parseFloat(getProductPrice(s.getProductName()).getText().replace("$",""));
            Assert.assertEquals(String.valueOf(s.getProductPrice()),String.valueOf(itemPrice));
            log.info(s.getProductName() + " price in cart is same as listed price " + s.getProductPrice());
            itemQty=Integer.parseInt(getProductQty(s.getProductName()).getAttribute("value"));
            Assert.assertEquals(s.getProductQuantity().intValue(),itemQty);
            subTotal=getProductSubtotal(s.getProductName()).getText().replace("$","");
            Assert.assertEquals(String.format("%.2f",itemQty * itemPrice),subTotal);
            log.info(s.getProductName() + " subtotal added to cart is " + subTotal);
            totalPrice=totalPrice + Float.parseFloat(subTotal);
        }
        Assert.assertEquals(String.valueOf(totalPrice),cartTotal.getText().split(" ")[1]);
        log.info("Total price of items added to cart is " + totalPrice);
    }
}
