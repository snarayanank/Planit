package com.planittesting.cloud.jupiter.pageObjects;

import com.planittesting.cloud.jupiter.utilities.Base;
import com.planittesting.cloud.jupiter.utilities.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

public class ContactPage {
    public WebDriver driver;
    public static Properties prop;
    WebDriverWait wait;
    private static Logger log = LogManager.getLogger(ContactPage.class.getName());

    public ContactPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    @FindBy(xpath= "//label[contains(text(),'Forename')]")
    WebElement lblForename;

    @FindBy(xpath= "//label[contains(text(),'Surname')]")
    WebElement lblSurname;

    @FindBy(xpath= "//label[contains(text(),'Email')]")
    WebElement lblEmail;

    @FindBy(xpath= "//label[contains(text(),'Telephone')]")
    WebElement lblTelephone;

    @FindBy(xpath= "//label[contains(text(),'Message')]")
    WebElement lblMessage;

    @FindBy(xpath= "//input[@id='forename']")
    WebElement txtForename;

    @FindBy(xpath= "//input[@id='surname']")
    WebElement txtSurname;

    @FindBy(xpath= "//input[@id='email']")
    WebElement txtEmail;

    @FindBy(xpath= "//input[@id='telephone']")
    WebElement txtTelephone;

    @FindBy(xpath= "//textarea[@id='message']")
    WebElement txtMessage;

    @FindBy(xpath= "//a[@class='btn-contact btn btn-primary']")
    WebElement btnSubmit;

    @FindBy(xpath= "//span[@id='forename-err']")
    WebElement msgForename;

    @FindBy(xpath= "//span[@id='email-err']")
    WebElement msgEmail;

    @FindBy(xpath= "//span[@id='message-err']")
    WebElement msgMessage;

    @FindBy(xpath= "//div[@class='alert alert-success']")
    WebElement successMsg;

    public void clickSubmit() {
        btnSubmit.click();
        log.info("Clicked Submit button on Contact Page");
    }

    public void validateErrors(){
        Assert.assertEquals(Constants.ContactPageConstants.FORENAME_ERR_MSG,msgForename.getText());
        log.info("Validated Forename error message on contact page as " + msgForename.getText());
        Assert.assertEquals(Constants.ContactPageConstants.EMAIL_ERR_MSG,msgEmail.getText());
        log.info("Validated Email error message on contact page as " + msgEmail.getText());
        Assert.assertEquals(Constants.ContactPageConstants.MESSAGE_ERR_MSG,msgMessage.getText());
        log.info("Validated Message error message on contact page as " + msgMessage.getText());
    }

    public void inputMandatoryFields(String fName, String email, String msg){
        txtForename.sendKeys(fName);
        log.info("Entered Forename on contact page as " + fName);
        txtEmail.sendKeys(email);
        log.info("Entered Email on contact page as " + email);
        txtMessage.sendKeys(msg);
        log.info("Entered Message on contact page as " + msg);
    }

    public void validateNoErrors(){
        Assert.assertFalse(isForenameErrMsgDisplayed());
        Assert.assertFalse(isEmailErrMsgDisplayed());
        Assert.assertFalse(isMessageErrMsgDisplayed());
    }

    public void validateSuccessfulSubmission(String fName){
        wait.until(ExpectedConditions.visibilityOf(successMsg));
        Assert.assertEquals("Thanks " + fName + Constants.ContactPageConstants.MESSAGE_SUCCESS_MSG,successMsg.getText());
        log.info("Feedback submission confirmation => " + successMsg.getText());
    }

    public boolean isForenameErrMsgDisplayed() {
        try {
            Assert.assertTrue(msgForename.isDisplayed());
            log.info("Forename validation errors are present on the contact page");
            return true;
        } catch (final NoSuchElementException exp) {
            log.info("No Forename validation errors are present on the contact page");
            return false;
        }
    }

    public boolean isEmailErrMsgDisplayed() {
        try {
            Assert.assertTrue(msgEmail.isDisplayed());
            log.info("Email validation errors are present on the contact page");
            return true;
        } catch (final NoSuchElementException exp) {
            log.info("No email validation errors are present on the contact page");
            return false;
        }
    }
    public boolean isMessageErrMsgDisplayed() {
        try {
            Assert.assertTrue(msgMessage.isDisplayed());
            log.info("Message validation errors are present on the contact page");
            return true;
        } catch (final NoSuchElementException exp) {
            log.info("No message validation errors are present on the contact page");
            return false;
        }
    }
}
