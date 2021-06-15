package com.planittesting.cloud.jupiter.pageObjects;

import com.planittesting.cloud.jupiter.utilities.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class HomePage {
    public WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver) {

        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    @FindBy(xpath= "//a[@class='brand']")
    WebElement jupiterToysLink;

    @FindBy(xpath= "//a[contains(text(),'Home')]")
    WebElement homePageLink;

    @FindBy(xpath= "//a[contains(text(),'Shop')]")
    WebElement shopPageLink;

    @FindBy(xpath= "//a[contains(text(),'Contact')]")
    WebElement contactPageLink;

    @FindBy(partialLinkText = "Start Shopping")
    WebElement startShoppingLink;

    public void goToContactPage() {
        contactPageLink.click();
        wait.until(ExpectedConditions.urlContains("contact"));
    }

    public void goToShopPage() {
        shopPageLink.click();
        wait.until(ExpectedConditions.urlContains("shop"));
    }
}
