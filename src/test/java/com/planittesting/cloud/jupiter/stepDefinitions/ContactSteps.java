package com.planittesting.cloud.jupiter.stepDefinitions;

import com.planittesting.cloud.jupiter.pageObjects.ContactPage;
import com.planittesting.cloud.jupiter.pageObjects.HomePage;
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

public class ContactSteps extends Base {

    private static Logger log = LogManager.getLogger(ContactSteps.class.getName());
    public WebDriver driver;
    public HomePage jToysHomePage;
    public ContactPage jToysContactPage;
    WebDriverWait wait;
    String pageTitle;

    public ContactSteps() throws IOException {
        driver = Hooks.driver;
        jToysHomePage = new HomePage(driver);
        jToysContactPage = new ContactPage(driver);
        wait = new WebDriverWait(driver, 20);
    }

    @Given("User is on Jupiter Toys home page")
    public void user_is_on_jupiter_toys_home_page() {
        // Write code here that turns the phrase above into concrete actions
        driver.get(prop.getProperty("baseUrl"));
        log.info("Page title is " + driver.getTitle());
        pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("Jupiter Toys"));
    }
    @Given("User goes to Contact Page")
    public void user_goes_to_contact_page() {
        // Write code here that turns the phrase above into concrete actions
        jToysHomePage.goToContactPage();
    }
    @When("User clicks submit button")
    public void user_clicks_submit_button() {
        // Write code here that turns the phrase above into concrete actions
        jToysContactPage.clickSubmit();
    }
    @Then("Validate errors on contact page")
    public void validate_errors_on_contact_page() {
        // Write code here that turns the phrase above into concrete actions
        jToysContactPage.validateErrors();
    }

    @Then("User populate mandatory fields")
    public void user_populate_mandatory_fields(DataTable contactDetails) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        List<Map<String, String>> mesgDetails = contactDetails.asMaps();
        for (Map<String, String> mesgDetail : mesgDetails) {
            String foreName = mesgDetail.get("ForeName");
            String email = mesgDetail.get("Email");
            String message = mesgDetail.get("Message");
            jToysContactPage.inputMandatoryFields(foreName,email,message);
        }
    }
    @Then("Validate no errors on contact page")
    public void validate_no_errors_on_contact_page() {
        // Write code here that turns the phrase above into concrete actions
        jToysContactPage.validateNoErrors();
    }

    @Given("User populate mandatory fields {string} {string} {string}")
    public void user_populate_mandatory_fields(String foreName, String email, String mesg) {
        // Write code here that turns the phrase above into concrete actions
        jToysContactPage.inputMandatoryFields(foreName,email,mesg);
    }

    @When("Validate successful submission message for user {string}")
    public void validate_successful_submission_message_for_user(String foreName) {
        // Write code here that turns the phrase above into concrete actions
        jToysContactPage.validateSuccessfulSubmission(foreName);
    }
}
