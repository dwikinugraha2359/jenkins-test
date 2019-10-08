package com.media2359.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * AuthenticationPage
 */
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class AuthenticationPage extends BasePage {
    // *********Variable*********

    // *********Constructor*********
    public AuthenticationPage(WebDriver driver) {
        super(driver);
    }

    // *********Web Elements*********
    // @CacheLookup
    @FindBy(xpath = "//input[@name='Username']")
    private WebElement adidEmailFieldWE;
    // @CacheLookup
    @FindBy(xpath = "/html[1]/body[1]/div[2]/div[1]/div[1]/section[1]/form[1]/div[2]/div[1]/p[1]")
    private WebElement adidEmailTextWE;
    // @CacheLookup
    @FindBy(xpath = "//input[@name='Password']")
    private WebElement passwordFieldWE;
    // @CacheLookup
    @FindBy(xpath = "/html[1]/body[1]/div[2]/div[1]/div[1]/section[1]/form[1]/div[3]/button[1]")
    private WebElement nextBtnWE;
    // @CacheLookup
    @FindBy(xpath = "/html[1]/body[1]/div[2]/div[1]/div[1]/section[1]/form[1]/div[3]/button[1]")
    private WebElement loginBtnWE;
    // @CacheLookup
    @FindBy(xpath = "//span[@class='vertical-align-middle name-section']")
    private WebElement accountHomeWE;

    // Forgot Password
    // @CacheLookup
    @FindBy(xpath = "/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/h4[1]/a[1]")
    private WebElement FPCollapse;
    // @CacheLookup
    @FindBy(xpath = "//div[@id='collapseTwo']//div[1]")
    private WebElement FPStatementText;

    // Disclaimer
    // @CacheLookup
    @FindBy(xpath = "/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/h4[1]/a[1]")
    private WebElement DisclCollapse;
    // @CacheLookup
    @FindBy(xpath = "/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]")
    private WebElement DisclStatementText;

    // *********Page Methods*********
    // Go to Homepage
    public AuthenticationPage openNHGWEB(String url) {
        // Open NHG URL
        driver.get(url);
        return this;
    }

    // Go to Homepage
    public AuthenticationPage inputADIDEmail(String adidEmail) throws InterruptedException {
        // wait visibilty element
        waitVisibility(adidEmailFieldWE);
        // input username
        writeText(adidEmailFieldWE, adidEmail);
        // click next button
        click(nextBtnWE);
        Thread.sleep(1000);
        return this;
    }

    // Go to Homepage
    public AuthenticationPage verifyAfterNextADID(String expAdidEmail) {
        // wait visibilty element
        waitVisibility(adidEmailTextWE);
        // verify ADID or Email Text
        assertEquals(adidEmailTextWE, expAdidEmail);
        // wait visibilty element
        waitVisibility(passwordFieldWE);
        // Verify password is blank
        assertEquals(passwordFieldWE, "");
        return this;
    }

    // Go to Homepage
    public AuthenticationPage inputPassword(String password) throws InterruptedException {
        // wait visibilty element
        waitVisibility(passwordFieldWE);
        // input password
        writeText(passwordFieldWE, password);
        // click next button
        click(loginBtnWE);
        Thread.sleep(1000);
        return this;
    }

    // Go to Homepage
    public AuthenticationPage verifyAfterLogin(String accountName) {
        // wait visibilty element
        waitVisibility(accountHomeWE);
        // wait until login complete
        assertEquals(accountHomeWE, accountName);
        return this;
    }

    // Go to Homepage
    public AuthenticationPage openAndVerifyFP(String expText) {
        // wait visibilty element
        waitVisibility(FPCollapse);
        // Click on collapse button
        click(FPCollapse);
        // wait visibilty element
        waitVisibility(FPStatementText);
        // verify the statement
        assertEquals(FPStatementText, expText);
        return this;
    }

    // Go to Homepage
    public AuthenticationPage openAndVerifyDisclaimer(String expText) {
        // wait visibilty element
        waitVisibility(DisclCollapse);
        // Click on collapse button
        click(DisclCollapse);
        // wait visibilty element
        waitVisibility(FPCollapse);
        // verify the statement
        assertEquals(DisclStatementText, expText);
        return this;
    }
}