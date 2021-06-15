package com.planittesting.cloud.jupiter.pageObjects;

import com.planittesting.cloud.jupiter.dao.ShopProfile;
import com.planittesting.cloud.jupiter.utilities.Constants;
import com.planittesting.cloud.jupiter.utilities.Helper;
import lombok.val;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class ShopPage {
    public WebDriver driver;
    public static Properties prop;
    WebDriverWait wait;
    private static Logger log = LogManager.getLogger(ShopPage.class.getName());
    protected ShopProfile productProfile = new Helper().getShopProfile();

    public ShopPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 20);
    }

    @FindBy(xpath= "//a[@href='#/cart']")
    WebElement cartPageLink;

    public WebElement getProductByName(String productName){
        String newXPath = "//h4[contains(text(),'Dummy Toy')]";
        newXPath= newXPath.replace("Dummy Toy", productName);
        return driver.findElement(By.xpath(newXPath));
    }

    public WebElement getProductBuyBtn(String productName) {
        WebElement productScope = driver.findElement(By.xpath("//div[@class='products ng-scope']"));
        List<WebElement> itemListCount = productScope.findElements(By.tagName("li"));
        WebElement itemBuyBtn = productScope.findElement(By.xpath("//a[@class='btn btn-success'][contains(text(),'Buy')]"));
        for (int i = 1; i <= itemListCount.size(); i++) {
            WebElement itemName = productScope.findElement(By.xpath("//li[@id='product-" + i + "']//div//h4"));
            if(itemName.getText().contains(productName)) {
                itemBuyBtn = productScope.findElement(By.xpath("//li[@id='product-" + i + "']//div//p//a[@class='btn btn-success'][contains(text(),'Buy')]"));
                break;
            }
        }
        return itemBuyBtn;
    }

    public WebElement getProductPrice(String productName) {
        WebElement productScope = driver.findElement(By.xpath("//div[@class='products ng-scope']"));
        List<WebElement> itemListCount = productScope.findElements(By.tagName("li"));
        WebElement itemPrice = productScope.findElement(By.xpath("//li[@id='product-1']//div//p//span[@class='product-price ng-binding']"));
        for (int i = 1; i <= itemListCount.size(); i++) {
            WebElement itemName = productScope.findElement(By.xpath("//li[@id='product-" + i + "']//div//h4"));
            if(itemName.getText().contains(productName)) {
                itemPrice = productScope.findElement(By.xpath("//li[@id='product-" + i + "']//div//p//span[@class='product-price ng-binding']"));
                break;
            }
        }
        return itemPrice;
    }

    public void addToCart(String pName) {
        getProductBuyBtn(pName).click();
        log.info("Added " + pName + " to cart");
    }

    public void goToCartPage() {
        cartPageLink.click();
        wait.until(ExpectedConditions.urlContains("cart"));
    }

}
