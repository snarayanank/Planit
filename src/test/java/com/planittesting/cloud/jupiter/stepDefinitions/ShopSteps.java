package com.planittesting.cloud.jupiter.stepDefinitions;

import com.planittesting.cloud.jupiter.dao.ShopProfile;
import com.planittesting.cloud.jupiter.pageObjects.CartPage;
import com.planittesting.cloud.jupiter.pageObjects.ContactPage;
import com.planittesting.cloud.jupiter.pageObjects.HomePage;
import com.planittesting.cloud.jupiter.pageObjects.ShopPage;
import com.planittesting.cloud.jupiter.utilities.Base;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ShopSteps extends Base {

    private static Logger log = LogManager.getLogger(ShopSteps.class.getName());
    public WebDriver driver;
    public HomePage jToysHomePage;
    public ContactPage jToysContactPage;
    public ShopPage jToysShopPage;
    public CartPage jToysCartPage;
    WebDriverWait wait;
    String pageTitle;

    public ShopSteps() throws IOException {
        driver = Hooks.driver;
        jToysHomePage = new HomePage(driver);
        jToysContactPage = new ContactPage(driver);
        jToysShopPage = new ShopPage(driver);
        jToysCartPage = new CartPage(driver);
        wait = new WebDriverWait(driver, 20);
    }

    @Given("User goes to Shop Page")
    public void user_goes_to_shop_page() {
        // Go to Shop Page
        jToysHomePage.goToShopPage();
    }

    @When("User clicks buy button {int} times on {string}")
    public void user_clicks_buy_button_times_on(Integer times, String productName) {
        // Click buy button on a selected product
        for(int count=0; count<times; count++){
            jToysShopPage.addToCart(productName);
        }
        Float prodPrice = Float.parseFloat(jToysShopPage.getProductPrice(productName).getText().replace("$", ""));
        itemList.add(new ShopProfile(productName,times,prodPrice));
    }

    @Then("User clicks cart menu")
    public void user_clicks_cart_menu() {
        // Write code here that turns the phrase above into concrete actions
        jToysShopPage.goToCartPage();
        System.out.println("Total items in the list is " + itemList.size());
    }

    @Then("User verifies the items are in the cart")
    public void user_verifies_the_items_are_in_the_cart() {
        // Write code here that turns the phrase above into concrete actions
        jToysCartPage.verifyCartCount(itemList);
    }

    @Then("User verifies the price and total for each product")
    public void user_verifies_the_price_and_total_for_each_product() {
        // Write code here that turns the phrase above into concrete actions
        jToysCartPage.verifyCartPriceTotal(itemList);
    }

}
