package com.anhtester.keywords;

import com.anhtester.drivers.DriverManager;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.helpers.SystemHelper;
import com.anhtester.reports.AllureManager;
import com.anhtester.reports.ExtentTestManager;
import com.anhtester.utils.LogUtils;
import com.aventstack.extentreports.Status;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WebUI {

    private static double STEP_TIME = 0;
    private static int timeout = Integer.parseInt(PropertiesHelper.getValue("EXPLICIT_WAIT"));

    // Basic

    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    public static void logConsole(String message) {
        LogUtils.info(message);
    }

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Open URL: {0}")
    public static void openURL(String url) {
        DriverManager.getDriver().get(url);
        sleep(STEP_TIME);
        LogUtils.info("\uD83C\uDF10 Open URL: " + url);
        ExtentTestManager.logMessage(Status.PASS, "Open URL: " + url);
    }

    public static String getCurrentURL() {
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        LogUtils.info("Current URL: " + currentUrl);
        return currentUrl;
    }

    public static void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(500));
            wait.until(expectation);
            LogUtils.info("Page loaded successfully.");
        } catch (Throwable error) {
            LogUtils.info("Page not loaded after waiting for 10 seconds.");
            Assert.fail("Page not loaded after waiting for 10 seconds.");
        }
    }

    public static void waitForPageRefresh(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));

            WebElement oldElement = DriverManager.getDriver().findElement(by);

            DriverManager.getDriver().navigate().refresh();

            wait.until(ExpectedConditions.stalenessOf(oldElement));

            wait.until(ExpectedConditions.visibilityOfElementLocated(by));

            System.out.println("✅ Page is refreshed and element is reloaded successfully.");
        } catch (TimeoutException e) {
            System.out.println("⏳ Timeout waiting for page to refresh: " + by);
        } catch (NoSuchElementException e) {
            System.out.println("❌ Element not found before refresh: " + by);
        }
    }

    public static void waitForElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.info("❌ Element " + by + " not found after waiting for 10 seconds.");
            Assert.fail("❌ Element " + by + " not found after waiting for 10 seconds.");
        }
    }

    @Step("Click on element {0}")
    public static void clickElement(By by) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
                WebElement element = wait.until(ExpectedConditions.refreshed(
                        ExpectedConditions.elementToBeClickable(by)
                ));
                element.click();

                LogUtils.info("Click element " + by);
                ExtentTestManager.logMessage(Status.PASS, "Click on element " + by);
                AllureManager.saveTextLog("Click on element " + by);
                return;

            } catch (StaleElementReferenceException e) {
                attempts++;
                LogUtils.warn("Retrying click due to stale element. Attempt: " + attempts);
            }
        }
        Assert.fail("❌ Failed to click element after retries: " + by);
    }

    @Step("Set text: {1} on element {0}")
    public static void setTextElement(By by, String text) {
        waitForElementVisible(by);
        sleep(STEP_TIME);
        clearText(by);
        DriverManager.getDriver().findElement(by).sendKeys(text);
        LogUtils.info("Set text: " + text + " on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Set text: " + text + " on element " + by);
        AllureManager.saveTextLog("==> TEXT " + text);
    }

    @Step("Clear text of element {0}")
    public static void clearText(By by) {
        waitForElementVisible(by);
        sleep(STEP_TIME);
        DriverManager.getDriver().findElement(by).clear();
        LogUtils.info("Clear text of element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Clear text of element " + by);
        AllureManager.saveTextLog("Clear text of element " + by);
    }

    @Step("Get text of element {0}")
    public static String getTextElement(By by) {
        waitForElementVisible(by);
        sleep(STEP_TIME);
        String text = DriverManager.getDriver().findElement(by).getText();
        LogUtils.info("Get text of element " + by + ": " + text);
        ExtentTestManager.logMessage(Status.PASS, "Get text of element " + by + ": " + text);
        ExtentTestManager.logMessage(Status.INFO, "==> TEXT " + text);
        AllureManager.saveTextLog("==> TEXT " + text);
        return text;
    }

    @Step("Get attribute: {1} on element {0}")
    public static String getAttributeElement(By by, String attributeName) {
        waitForElementVisible(by);
        sleep(STEP_TIME);
        String value = DriverManager.getDriver().findElement(by).getAttribute(attributeName);
        LogUtils.info("Get attribute " + attributeName + " of element " + by + ": " + value);
        ExtentTestManager.logMessage(Status.PASS, "Get attribute " + attributeName + " of element " + by + ": " + value);
        ExtentTestManager.logMessage(Status.INFO, "==> ATTRIBUTE " + value);
        AllureManager.saveTextLog("==> ATTRIBUTE " + value);
        return value;
    }

    public static List<WebElement> getWebElements(By by) {
        return DriverManager.getDriver().findElements(by);
    }

    public static Boolean checkElementExist(By by) {
        waitForElementVisible(by);
        List<WebElement> listElement = getWebElements(by);

        if (listElement.size() > 0) {
            LogUtils.info("checkElementExist: " + true + " --- " + by);
            return true;
        } else {
            LogUtils.info("checkElementExist: " + false + " --- " + by);
            return false;
        }
    }

    public static void uploadFileWithRobotClass(By elementFileForm, String filePath) {

        WebUI.clickElement(elementFileForm);
        WebUI.sleep(2);

        Robot rb = null;
        try {
            rb = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        StringSelection str = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);

        WebUI.sleep(1);

        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);

        WebUI.sleep(2);
    }

    public static boolean checkElementDisplayed(By by) {
        try {
            waitForElementVisible(by);
            boolean isDisplayed = DriverManager.getDriver().findElement(by).isDisplayed();
            LogUtils.info("Element " + by + " is displayed: " + isDisplayed);
            return isDisplayed;
        } catch (NoSuchElementException e) {
            LogUtils.info("Element " + by + " not found.");
            return false;
        }
    }

    public static boolean checkFieldIsToday(By by, String pattern) {
        try {
            waitForElementVisible(by);
            String uiDate = DriverManager.getDriver().findElement(by).getText().trim();

            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            String todayStr = today.format(formatter);

            LogUtils.info("UI date: " + uiDate + " | Expected today: " + todayStr);

            return uiDate.equals(todayStr);

        } catch (Exception e) {
            LogUtils.error("Error checking field date: " + e.getMessage());
            return false;
        }
    }

    public static boolean isElementSelected(By by) {
        try {
            WebElement element = DriverManager.getDriver().findElement(by);
            return element.isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Move to element {0}")
    public static void moveToElement(By by) {
        waitForElementVisible(by);
        sleep(STEP_TIME);
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(DriverManager.getDriver().findElement(by)).perform();
        LogUtils.info("Move to element " + by);
    }

    @Step("Accepted Alert")
    public static void acceptAlert() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = DriverManager.getDriver().switchTo().alert();
            alert.accept();
            LogUtils.info("Alert accepted.");
        } catch (Throwable error) {
            LogUtils.info("No alert found after waiting for 10 seconds.");
            Assert.fail("No alert found after waiting for 10 seconds.");
        }
    }

    @Step("Dismissed Alert")
    public static void dismissAlert() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = DriverManager.getDriver().switchTo().alert();
            alert.dismiss();
            LogUtils.info("Alert dismissed.");
        } catch (Throwable error) {
            LogUtils.info("No alert found after waiting for 10 seconds.");
            Assert.fail("No alert found after waiting for 10 seconds.");
        }
    }

    // Advanced

    public static void scrollToElement(By by) {
        waitForElementVisible(by);
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
    }

    public static void scrollToElementAtTop(By by) {
        waitForElementVisible(by);
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
    }

    public static void scrollToElementAtBottom(By by) {
        waitForElementVisible(by);
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
    }

    public static boolean dragAndDrop(By fromElement, By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.dragAndDrop(getWebElement(fromElement), getWebElement(toElement)).perform();
            LogUtils.info("Dragged and dropped element from " + fromElement + " to " + toElement);
            return true;
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            return false;
        }
    }

    // Verify

    public static boolean verifyEquals(Object actual, Object expected) {
        waitForPageLoaded();
        LogUtils.info("Verify equals: " + actual + " \uD83D\uDFF0 " + expected);
        boolean check = actual.equals(expected);
        return check;
    }

    public static void assertEquals(Object actual, Object expected, String message) {
        waitForPageLoaded();
        LogUtils.info("Assert equals: " + actual + " \uD83D\uDFF0 " + expected);
        Assert.assertEquals(actual, expected, message);
    }

    public static boolean verifyTrue(boolean condition) {
        waitForPageLoaded();
        LogUtils.info("Verify true: " + condition);
        return condition;
    }

    public static void assertTrue(boolean condition, String message) {
        waitForPageLoaded();
        LogUtils.info("Assert true: " + condition);
        Assert.assertTrue(condition, message);
    }

    public static void assertFalse(boolean condition, String message) {
        waitForPageLoaded();
        LogUtils.info("Assert false: " + condition);
        Assert.assertTrue(condition, message);
    }

    public static boolean verifyContains(String actual, String expected) {
        waitForPageLoaded();
        LogUtils.info("Verify contains: " + actual + " ↔\uFE0F " + expected);
        boolean check = actual.contains(expected);
        return check;
    }

    public static void assertContains(String actual, String expected, String message) {
        waitForPageLoaded();
        LogUtils.info("Assert contains: " + actual + " ↔\uFE0F " + expected);
        boolean check = actual.contains(expected);
        Assert.assertTrue(check, message);
    }

    public static void assertNotContains(String actual, String expected, String message) {
        waitForPageLoaded();
        LogUtils.info("Assert not contains: " + actual + " ↔\uFE0F " + expected);
        boolean check = actual.contains(expected);
        Assert.assertFalse(check, message);
    }

    @Step("Check data: {1} in table by column {2}")

    public static void checkDataInTableByColumn_Contains(int column, String value, String columnName) {

        List<WebElement> row = DriverManager.getDriver().findElements(By.xpath("//table//tbody/tr"));
        int rowTotal = row.size();
        System.out.println(("Number of lines found: " + rowTotal));

        for (int i = 1; i <= rowTotal; i++) {
            WebElement elementCheck = DriverManager.getDriver().findElement(By.xpath("//table//tbody/tr[" + i + "]/td[" + column + "]"));

            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].scrollIntoView(true);", elementCheck);

            //System.out.println((value + " - "));
            System.out.println(elementCheck.getText());
            Assert.assertTrue(SystemHelper.removeSpecialCharacters(elementCheck.getText()).toUpperCase().contains(SystemHelper.removeSpecialCharacters(value).toUpperCase()), "The number line " + i + " does not contain the search value.");
        }

    }

    @Step("Check data: {1} in table by column {2}")

    public static void checkDataInTableByColumn_Equals(int column, String value, String columnName) {

        List<WebElement> row = DriverManager.getDriver().findElements(By.xpath("//table//tbody/tr"));
        int rowTotal = row.size();
        LogUtils.info("Number of lines found: " + rowTotal);

        for (int i = 1; i <= rowTotal; i++) {
            WebElement elementCheck = DriverManager.getDriver().findElement(By.xpath("//table//tbody/tr[" + i + "]/td[" + column + "]"));

            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].scrollIntoView(true);", elementCheck);

            //System.out.println((value + " - "));
            System.out.println(elementCheck.getText());
            Assert.assertTrue(elementCheck.getText().toUpperCase().equals(value.toUpperCase()), "The number line " + i + " does not contain the search value.");
        }

    }


}
