package com.media2359.pages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.media2359.helper.HelperFunc;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.TouchAction;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;

    // *********Web Elements*********

    // *********Constructor*********
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        wait = new WebDriverWait(driver, 15);
    }

    // *********Page Methods*********
    public void navigateToUrl(String expUrl) {
        driver.get(expUrl);
    }

    public void goBackPrevPage() {
        driver.navigate().back();
    }

    // get text Field
    public void getTextField(WebElement elementBy) {
        Reporter.log(readText(elementBy));
    }

    // Wait Wrapper Method
    public void waitVisibility(WebElement elementBy) {
        wait.until(ExpectedConditions.visibilityOf(elementBy));
    }

    public void waitVisibility(List<WebElement> elementBy) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elementBy));
    }

    // clear field
    public void clearField(WebElement elementBy) {
        waitVisibility(elementBy);
        int lengthField = readTextField(elementBy).length();
        for (int i = 0; i < lengthField; i++) {
            elementBy.sendKeys(Keys.BACK_SPACE);
        }
    }

    // Remove one multiselect value
    // public void removeOneMultiSelect(WebElement elementBy, String value) {
    // waitVisibility(elementBy);
    // List<WebElement> list = driver.findElements(elementBy);
    // Reporter.log(String.valueOf(list.size()));
    // for (WebElement we : list) {
    // if (we.getText().equals(value)) {
    // we.findElement(By.xpath(".//div[2]")).click();
    // }
    // }
    // }

    // Remove all value on multi select
    // public void removeAllMultiSelect(WebElement elementBy) {
    // waitVisibility(elementBy);
    // List<WebElement> list = driver.findElements(elementBy);
    // for (int i = 0; i < (list.size() - 1); i++) {
    // removeOneMultiSelect(elementBy, list.get(i).getText());
    // }
    // }

    // tap element
    public void tapElement(WebElement onElement) {
        TouchActions action = new TouchActions(driver);
        action.singleTap(onElement).perform();
    }

    // Click Method
    public void click(WebElement elementBy) {
        waitVisibility(elementBy);
        elementBy.click();
    }

    // Click Method
    public void clickAction(WebElement elementBy) {
        Actions action = new Actions(driver);
        action.moveToElement(elementBy).click().build().perform();
    }

    // Hover Button
    public void hoverButton(WebElement elementByStart, WebElement elementByEnd) throws InterruptedException {
        waitVisibility(elementByStart);
        Actions action = new Actions(driver);
        action.moveToElement(elementByStart).click().build().perform();
        Thread.sleep(1000);
        action.moveToElement(elementByEnd).click().build().perform();
    }

    // Input File
    public void inputFile(WebElement elementBy, String text) {
        WebElement El = elementBy;
        ((RemoteWebElement) El).setFileDetector(new LocalFileDetector());
        El.sendKeys(text);
    }

    // Write Text
    public void writeText(WebElement element, String text) {
        waitVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    // Read Text
    public String readText(WebElement elementBy) {
        waitVisibility(elementBy);
        return elementBy.getText().replace("\n", " ").replace("\r", " ").replaceAll("&nbsp;", "");
    }

    // Read Text
    public String readTextField(WebElement elementBy) {
        waitVisibility(elementBy);
        return elementBy.getAttribute("value");
    }

    public void dragNDrop(WebElement element1, WebElement element2) {
        // Using Action class for drag and drop.
        Actions act = new Actions(driver);

        // Dragged and dropped.
        act.dragAndDrop(element1, element2).build().perform();
    }

    WebElement oneRow;

    // to check the check box is checker or not
    public void listCBCheckedorNot(List<WebElement> elementBy, Boolean type) {

        for (WebElement var : elementBy) {
            if (type) {
                assertTrue(var.isSelected() == true);
            } else {
                assertTrue(var.isSelected() == false);
            }
        }

        Reporter.log(" | ");
    }

    // Get one row element table
    // public WebElement getOneElementRowTable(WebElement elementBy, String
    // containText) {
    // // put all table web element on
    // List<WebElement> listElementRoWTable = driver.findElements(elementBy);
    // // Get One row that contains specific text
    // for (int i = 0; i < listElementRoWTable.size(); i++) {
    // if (listElementRoWTable.get(i).getText().contains(containText)) {
    // oneRow = listElementRoWTable.get(i);
    // }
    // }
    // return oneRow;
    // }

    // IsDisplayed
    public Boolean isDisplay(WebElement elementBy) {
        waitVisibility(elementBy);
        Reporter.log(String.valueOf(elementBy.isDisplayed()));
        return elementBy.isDisplayed();
    }

    // Assert Field Empty
    public void assertFieldEmpty(WebElement elementBy) {
        String textInsideInputBox = elementBy.getAttribute("value");

        // Check whether input field is blank
        if (textInsideInputBox.isEmpty()) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    // Assert Equals
    public void assertContain(String expectedText) {
        WebElement we = driver.findElement(By.xpath("//*[contains(text(),'" + expectedText + "')]"));
        Assert.assertTrue(we.isDisplayed());
        Reporter.log(we.getText());
    }

    // Assert Equals
    public void assertContainText(String resultText, String expectedText) {
        Assert.assertTrue(resultText.contains(expectedText));
        Reporter.log(resultText + " | " + expectedText);
    }

    // Assert Equals
    public void assertEquals(WebElement elementBy, String expectedText) {
        waitVisibility(elementBy);
        Assert.assertEquals(readText(elementBy), expectedText);
        Reporter.log(readText(elementBy));
    }

    // Assert Equals Text Field
    public void assertEqualsTextField(WebElement elementBy, String expectedText) {
        waitVisibility(elementBy);
        Assert.assertEquals(readTextField(elementBy), expectedText);
        Reporter.log(readText(elementBy));
    }

    // Assert Equals
    public void assertEqualsText(String resultText, String expectedText) {
        Assert.assertEquals(resultText, expectedText);
        Reporter.log(resultText);
    }

    // Assert Visibility
    public void assertVisibility(WebElement elementBy) {
        waitVisibility(elementBy);
        Assert.assertTrue(isDisplay(elementBy));
    }

    // Assert Visibility
    public void assertVisibility(List<WebElement> elementBy) {
        waitVisibility(elementBy);
        Assert.assertTrue(elementBy != null && elementBy.size() > 0);
    }

    // Assert Visibility
    public void assertTrue(WebElement elementBy, String expectedText) {
        waitVisibility(elementBy);
        Assert.assertTrue(readText(elementBy).contains(expectedText));
        Reporter.log(readText(elementBy));
    }

    public void assertTrue(boolean result) {
        Assert.assertTrue(result);
    }

    // compare prev date
    public boolean compareNextDate(Date nowDate, Date date1) {
        Reporter.log(nowDate.toString() + " | " + date1.toString());
        if (nowDate.compareTo(date1) > 0) {
            return true;
        } else {
            return false;
        }
    }

    // compare next date
    public boolean comparePrevDate(Date nowDate, Date date1) {
        Reporter.log(date1.toString() + " | " + nowDate.toString());
        if (nowDate.compareTo(date1) < 0) {
            return true;
        } else {
            return false;
        }
    }

    // get one element using Equals
    public WebElement getOneElementEquals(List<WebElement> listElementBy, String expText) {
        oneElement = null;
        for (WebElement var : listElementBy) {
            if (readText(var).equals(expText)) {
                oneElement = var;
                break;
            }
        }
        return oneElement;
    }

    // get one element using Contains
    public WebElement getOneElementContains(List<WebElement> listElementBy, String expText) {
        oneElement = null;
        for (WebElement var : listElementBy) {
            if (readText(var).contains(expText)) {
                oneElement = var;
                break;
            }
        }
        return oneElement;
    }

    // get selected field text
    public String getSelectedText(WebElement elementBy) {
        return new Select(elementBy).getFirstSelectedOption().getText();
    }

    // select the select field
    public void selectByText(WebElement elementBy, String expectedText) {
        Select oSelect = new Select(elementBy);

        oSelect.selectByVisibleText(expectedText);
    }

    // get data table
    // public List<WebElement> getChildElement(WebElement elementBy) {
    // waitVisibility(elementBy);
    // ArrayList<String> data = new ArrayList<>();
    // // put all header web element on
    // List<WebElement> listHeaderColumn = driver.findElements(elementBy);
    // // put the column text to data
    // for (int i = 0; i < listHeaderColumn.size(); i++) {
    // data.add(listHeaderColumn.get(i).getText());
    // }

    // return data;
    // }

    // get data table that parameter is webelement
    public ArrayList<String> getDataTableRowWE(WebElement elementBy) {
        ArrayList<String> data = new ArrayList<>();
        // put all header web element on
        List<WebElement> listHeaderColumn = elementBy.findElements(By.tagName("td"));
        // put the column text to data
        for (int i = 0; i < listHeaderColumn.size(); i++) {
            data.add(listHeaderColumn.get(i).getText());
        }

        return data;
    }

    // Verify Header on table
    // public void verifyHeaderTable(String expectedText, WebElement headerTableBy)
    // {
    // waitVisibility(headerTableBy);
    // // split the expected header text
    // List<String> expHeader = Arrays.asList(expectedText.split(";"));
    // // Get list Header Row
    // ArrayList<String> headerList = getDataTableRow(headerTableBy);
    // for (int i = 0; i < expHeader.size(); i++) {
    // // validate the result text with expected
    // assertEqualsText(headerList.get(i), expHeader.get(i));
    // Reporter.log(headerList.get(i) + " : " + expHeader.get(i));
    // }
    // }
    WebElement oneElement;

    // get element that same iteration
    public WebElement getElementSameIteration(String expectedText, List<WebElement> listElement1,
            List<WebElement> listElement2) {

        for (int i = 0; i < listElement1.size(); i++) {
            if (readText(listElement1.get(i)).contains(expectedText)) {
                oneElement = listElement2.get(i);
                break;
            }
        }
        return oneElement;
    }

    // verify content on table one row that parameter is webelement
    public void verifyContentTableWE(String expectedText, WebElement contentTableBy) {
        // split the expected Content text
        List<String> expContent = Arrays.asList(expectedText.split(";"));
        // Get list Content Row
        ArrayList<String> contentList = getDataTableRowWE(contentTableBy);
        for (int i = 0; i < expContent.size(); i++) {
            // validate the result text with expected
            if (!expContent.get(i).contains("(dynamic)")) {
                assertEqualsText(contentList.get(i).replaceAll("\\r\\n|\\r|\\n", " "), expContent.get(i));
            }
            Reporter.log(contentList.get(i) + " : " + expContent.get(i));
        }
    }

    // Choose Date Picker
    public void chooseDateOnDP(Date expDate, WebElement prevMonthDatePicker2, WebElement nextMonthDatePicker2,
            WebElement yearTxtDatePicker2, WebElement dateTxtDatePicker2) throws ParseException, InterruptedException {

        // choose the date
        Date dateDP = new Date();
        // change expDate into yearMonth
        YearMonth yearMonthExp = YearMonth.of(expDate.getYear(), expDate.getMonth());

        while (true) {
            Thread.sleep(1000);
            // get date Picker date
            dateDP = HelperFunc.stringToDate(readText(dateTxtDatePicker2) + " " + readText(yearTxtDatePicker2),
                    "dd MMMM yyyy");
            YearMonth yearMonthDP = YearMonth.of(dateDP.getYear(), dateDP.getMonth());

            if (yearMonthExp.compareTo(yearMonthDP) > 0) {
                // Date1 is after Date2
                click(nextMonthDatePicker2);
                Thread.sleep(500);
                click(driver.findElement(By.xpath("//div[@class='rdt rdtOpen']//td[@class='rdtDay'][contains(text(),'"
                        + expDate.getDate() + "')]")));
                // click(driver.findElementByXPath(
                // "//android.view.View[contains(@content-desc, '" + expDate.getDate() +
                // "')]"));
            } else if (yearMonthExp.compareTo(yearMonthDP) < 0) {
                // Date1 is before Date2
                click(prevMonthDatePicker2);
                Thread.sleep(500);
                click(driver.findElement(By.xpath("//div[@class='rdt rdtOpen']//td[@class='rdtDay'][contains(text(),'"
                        + expDate.getDate() + "')]")));
            } else {
                // stop until year month date picker equal with expected
                click(driver.findElement(By.xpath("//div[@class='rdt rdtOpen']//td[@class='rdtDay'][contains(text(),'"
                        + expDate.getDate() + "')]")));
                break;
            }
        }
    }

    public void selectHourOrMinutePicker(WebElement elementBy, WebElement elementUp, WebElement elementDown,
            String expTime) {
        while (true) {
            if (readText(elementBy).equals(expTime)) {
                break;
            } else {
                Integer exp = Integer.parseInt(expTime);
                Integer actual = Integer.parseInt(readText(elementBy));
                if (actual < exp) {
                    click(elementUp);
                } else {
                    click(elementDown);
                }
            }
        }
    }

    public static String removeLeadingZeroes(String value) {
        return new Integer(value).toString();
    }

    // generate File
    public void generateFile(String originalPath, String copiedPath) throws IOException {

        File original = new File(originalPath);
        File copied = new File(copiedPath);
        FileUtils.copyFile(original, copied);
    }

    // generate Random number
    public String generateRandomNumber(int range, String digit) {
        Random rand = new Random();

        // Obtain a number between [0 - 49].
        int n = rand.nextInt(range);

        // Add 1 to the result to get a number from the required range
        // (i.e., [1 - 50]).
        n += 1;
        String formatted = String.format(digit, n);
        return formatted;
    }

    // to generate date
    public String generateDate() {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    // to generate date with hour
    public String generateDateNHour() {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy-HH");
        Date date = new Date();
        return dateFormat.format(date);
    }

    // to generate file with 2 row value
    public void generateFile2Row(String fileName, String rowOne, String rowTwo)
            throws UnsupportedEncodingException, FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        writer.println(rowOne);
        writer.println(rowTwo);
        writer.close();
    }

    // get value By on multi select field
    public By byxpathcontain(String classDiv, String text) {
        return By.xpath("//div[@class='" + classDiv + "']//div[contains(text(),'" + text + "')]");
    }

    // verify file downloaded on directory
    public boolean isFileDownloaded(String downloadPath) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();

        for (int i = 0; i < dirContents.length; i++) {
            System.out.println(dirContents[i].getName());
            // if (dirContents[i].getName().equals(fileName)) {
            // // File has been found, it can now be deleted:
            // dirContents[i].delete();
            // return true;
            // }
        }

        // verify the count of file on directory
        if (dirContents.length > 0) {
            return true;
        }

        return false;
    }

    // *********Helper Method*********
}