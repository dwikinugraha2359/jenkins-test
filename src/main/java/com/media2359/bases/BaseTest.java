package com.media2359.bases;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import com.media2359.helper.HelperFunc;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import org.apache.commons.io.FileUtils;
import java.io.IOException;
import java.net.ServerSocket;

import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class BaseTest {
    // String downloadFilepath =
    // "/Users/2359id/developments/office/GoldBell-Web-Automation/download/";
    public WebDriver driver;
    public WebDriverWait wait;
    HelperFunc utilsTest = new HelperFunc();

    @BeforeClass
    @Parameters({ "platformName", "headless" })
    public void setup(String platformName, Boolean headless) throws MalformedURLException {
        // Prepare Appium session
        if (platformName.contains("Chrome")) {
            initDriverChrome(headless);
        } else {
            initDriverMozilla(headless);
        }
    }

    @AfterClass
    public void tearDown() {
        // Close session
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethodStart() throws IOException {
        // Clean download Directory on test result
        utilsTest.cleanDownloadDir();
    }

    @AfterMethod // AfterMethod annotation - This method executes after every test execution
    public void screenShot(ITestResult result) {
        // using ITestResult.FAILURE is equals to result.getStatus then it enter into if
        // condition
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                // To create reference of TakesScreenshot
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                // Call method to capture screenshotF
                File src = screenshot.getScreenshotAs(OutputType.FILE);
                // Copy files to specific location
                // result.getName() will return name of test case so that screenshot name will
                // be same as test case name
                FileUtils.copyFile(src, new File("screenshoot/" + utilsTest.generateDateNHour() + "/" + result.getName()
                        + "-" + result.getEndMillis() + ".png"));
                System.out.println("Successfully captured a screenshot");
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
    }

    private void initDriverChrome(Boolean headless) throws MalformedURLException {
        System.out.println("Inside initDriver Chrome method");
        ChromeOptions chromeOptions = new ChromeOptions();
        if (headless) {
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--window-size=1920,1080");
            chromeOptions.addArguments("--headless"); // to set headless
            chromeOptions.addArguments("disable-gpu");
        }
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        // Instantiate Web Driver
        driver = new ChromeDriver(cap);

        // Maximize Window
        driver.manage().window().maximize();
    }

    private void initDriverMozilla(Boolean headless) throws MalformedURLException {
        // Set Firefox Headless mode as TRUE
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.setHeadless(true);
        }
        // Instantiate Web Driver
        driver = new FirefoxDriver(options);

        // Maximize Window
        driver.manage().window().maximize();
    }

    // ------ to set download path ------
    // private void initDriverChrome() throws MalformedURLException {
    // System.out.println("Inside initDriver Chrome method");

    // String downloadFilepath =
    // "/Users/2359id/developments/office/GoldBell-Web-Automation/download/";
    // HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
    // chromePrefs.put("profile.default_content_settings.popups", 0);
    // // chromePrefs.put("download.default_directory", downloadFilepath);
    // ChromeOptions options = new ChromeOptions();
    // options.setExperimentalOption("prefs", chromePrefs);
    // options.addArguments("--headless");
    // options.addArguments("disable-gpu");
    // DesiredCapabilities cap = DesiredCapabilities.chrome();
    // cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    // cap.setCapability(ChromeOptions.CAPABILITY, options);

    // // Create a Chrome driver. All test classes use this.
    // driver = new ChromeDriver(cap);
    // // driver = new ChromeDriver();

    // // Maximize Window
    // driver.manage().window().maximize();
    // }

    // private void initDriverMozilla() throws MalformedURLException {
    // System.out.println("Inside initDriver Mozilla method");

    // // Setting Firefox driver path
    // System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");

    // // Creating firefox profile
    // FirefoxProfile profile = new FirefoxProfile();

    // // Instructing firefox to use custom download location
    // profile.setPreference("browser.download.folderList", 2);

    // // Setting custom download directory
    // profile.setPreference("browser.download.dir", downloadFilepath);

    // // Skipping Save As dialog box for types of files with their MIME
    // profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
    // "text/csv,application/java-archive,
    // application/x-msexcel,application/excel,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/vnd.microsoft.portable-executable");

    // // Creating FirefoxOptions to set profile
    // FirefoxOptions option = new FirefoxOptions();
    // option.setProfile(profile);
    // // Launching browser with desired capabilities
    // driver = new FirefoxDriver(option);

    // // Maximize Window
    // driver.manage().window().maximize();
    // }
}