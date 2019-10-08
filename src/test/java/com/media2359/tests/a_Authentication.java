package com.media2359.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import com.media2359.pages.AuthenticationPage;

import com.media2359.bases.BaseTest;

/**
 * a_DutyHour
 */
public class a_Authentication extends BaseTest {

    /*
     * Feature : Navigated to contact us page and assert that display contact
     * person, payment instruction, payment info Given : User already logged in to
     * Goldbell Portal as Admin When : User click on "Contact Us" menu Then : User
     * will be able to see "Payment" contact person information, "Customer Service"
     * contact person information, payment information, and payment instruction.
     */
    @Test(priority = 2)
    @Parameters({ "baseUrl", "adidPC", "password", "accountName" })
    public void A_001_validLogin(String baseUrl, String adid, String password, String accountName) {

        // *************PAGE INSTANTIATIONS*************
        AuthenticationPage authenticationPage = new AuthenticationPage(driver);

        // *************PAGE METHODS********************
        try {
            authenticationPage.openNHGWEB(baseUrl).inputADIDEmail(adid).verifyAfterNextADID(adid)
                    .inputPassword(password).verifyAfterLogin(accountName);
            System.out.println("Test completed successfully");
        } catch (Exception e) {
            // TODO: handle exception
            Assert.fail(e.getMessage());
        }

    }

    @Test(priority = 0)
    @Parameters({ "baseUrl", "statementFP" })
    public void A_002_ForgotPassword(String baseUrl, String statementFP) {

        // *************PAGE INSTANTIATIONS*************
        AuthenticationPage authenticationPage = new AuthenticationPage(driver);

        // *************PAGE METHODS********************
        try {
            authenticationPage.openNHGWEB(baseUrl).openAndVerifyFP(statementFP);
            System.out.println("Test completed successfully");
        } catch (Exception e) {
            // TODO: handle exception
            Assert.fail(e.getMessage());
        }

    }

    @Test(priority = 1)
    @Parameters({ "baseUrl", "statementDisclaimer" })
    public void A_003_ViewDisclaimer(String baseUrl, String statementDisclaimer) {

        // *************PAGE INSTANTIATIONS*************
        AuthenticationPage authenticationPage = new AuthenticationPage(driver);

        // *************PAGE METHODS********************
        try {
            authenticationPage.openNHGWEB(baseUrl).openAndVerifyDisclaimer(statementDisclaimer);
            System.out.println("Test completed successfully");
        } catch (Exception e) {
            // TODO: handle exception
            Assert.fail(e.getMessage());
        }

    }
}