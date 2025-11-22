package settings.keywords;

import settings.drivers.DriverManager;
import settings.helpers.PropertiesHelper;
import settings.helpers.SystemHelper;
import settings.helpers.WaitHelper;
import settings.reports.AllureManager;
import settings.reports.ExtentTestManager;
import settings.utils.LogUtils;
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
import java.util.ArrayList;
import java.util.List;

import static settings.drivers.DriverManager.getDriver;

public class WebUI {

    private static int timeout = Integer.parseInt(PropertiesHelper.getValue("EXPLICIT_WAIT"));

    // Basic

    public static WebElement getWebElement(By by) {
        return getDriver().findElement(by);
    }

    public static void logConsole(String message) {
        LogUtils.info(message);
    }

    @Step("Open URL: {0}")
    public static void openURL(String url) {
        getDriver().get(url);
        WebUI.waitForPageLoaded();
        LogUtils.info("\uD83C\uDF10 Open URL: " + url);
        ExtentTestManager.logMessage(Status.PASS, "Open URL: " + url);
    }

    public static String getCurrentURL() {
        String currentUrl = getDriver().getCurrentUrl();
        LogUtils.info("Current URL: " + currentUrl);
        return currentUrl;
    }

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(500));
            wait.until(expectation);
            LogUtils.info("Page loaded successfully.");
        } catch (Throwable error) {
            LogUtils.info("Page not loaded after waiting for 10 seconds.");
            Assert.fail("Page not loaded after waiting for 10 seconds.");
        }
    }

    public static void waitForTableFullyLoaded(By rowLocator) {

        // Đợi table có ít nhất 1 dòng
        WebUI.waitForElementVisible(rowLocator);

        int oldCount = -1;
        int stableCount = 0;

        for (int i = 0; i < 20; i++) {
            int newCount = getWebElements(rowLocator).size();

            // Kiểm tra số dòng ổn định 2 lần liên tiếp
            if (newCount == oldCount && newCount > 0) {
                stableCount++;

                if (stableCount >= 2) {
                    return; // Table thực sự load xong
                }
            } else {
                stableCount = 0;
            }

            oldCount = newCount;
            sleep(200);
        }
    }


    public static void waitForPageRefresh(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));

            WebElement oldElement = getDriver().findElement(by);

            getDriver().navigate().refresh();

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
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.info("❌ Element " + by + " not found after waiting for 10 seconds.");
            Assert.fail("❌ Element " + by + " not found after waiting for 10 seconds.");
        }
    }

    public static void waitForElementVisible(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOf(element)); // ✅ dùng visibilityOf cho WebElement
        } catch (Throwable error) {
            LogUtils.info("❌ Element " + element + " not found after waiting for 10 seconds.");
            Assert.fail("❌ Element " + element + " not found after waiting for 10 seconds.");
        }
    }

    @Step("Click on element {0}")
    public static void clickElement(By by) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
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

    @Step("Click on element {0}")
    public static void clickElement(WebElement element) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
                WebElement clickable = wait.until(ExpectedConditions.refreshed(
                        ExpectedConditions.elementToBeClickable(element)
                ));
                clickable.click();

                LogUtils.info("Click element " + element);
                ExtentTestManager.logMessage(Status.PASS, "Click on element " + element);
                AllureManager.saveTextLog("Click on element " + element);
                return;

            } catch (StaleElementReferenceException e) {
                attempts++;
                LogUtils.warn("Retrying click due to stale element. Attempt: " + attempts);
            }
        }
        Assert.fail("❌ Failed to click element after retries: " + element);
    }

    @Step("Click {0} until element {1} is visible")
    public static void clickUntilVisible(By clickTarget, By waitTarget) {
        int attempts = 0;
        int maxAttempts = 5;

        while (attempts < maxAttempts) {
            try {
                WebUI.clickElement(clickTarget);
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
                wait.until(ExpectedConditions.visibilityOfElementLocated(waitTarget));

                LogUtils.info("✅ Target element appeared after click: " + waitTarget);
                return;

            } catch (TimeoutException e) {
                attempts++;
                LogUtils.warn("⚠️ Element not visible yet. Retry click (" + attempts + "/" + maxAttempts + ")");
            } catch (Exception e) {
                attempts++;
                LogUtils.warn("⚠️ Click attempt failed: " + e.getMessage());
            }
        }

        Assert.fail("❌ Element " + waitTarget + " not visible after clicking " + clickTarget + " " + maxAttempts + " times.");
    }

    @Step("Click {0} until success (max {1} attempts)")
    public static void clickUntilVisible(By clickTarget) {
        int attempts = 0;
        int maxAttempts = 5;

        while (attempts < maxAttempts) {
            try {
                WebUI.clickElement(clickTarget);
                LogUtils.info("✅ Clicked successfully on: " + clickTarget);
                return; // nếu click thành công thì dừng
            } catch (Exception e) {
                attempts++;
                LogUtils.warn("⚠️ Click attempt failed (" + attempts + "/" + maxAttempts + "): " + e.getMessage());
                WebUI.sleep(1); // thêm delay giữa các lần click
            }
        }

        Assert.fail("❌ Could not click " + clickTarget + " after " + maxAttempts + " attempts.");
    }

    @Step("Set text: {1} on element {0}")
    public static void setTextElement(By by, String text) {
        waitForElementVisible(by);
        clearText(by);
        getDriver().findElement(by).sendKeys(text);
        LogUtils.info("Set text: " + text + " on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Set text: " + text + " on element " + by);
        AllureManager.saveTextLog("==> TEXT " + text);
    }

    @Step("Clear text of element {0}")
    public static void clearText(By by) {
        waitForElementVisible(by);
        getDriver().findElement(by).clear();
        LogUtils.info("Clear text of element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Clear text of element " + by);
        AllureManager.saveTextLog("Clear text of element " + by);
    }

    @Step("Get text of element {0}")
    public static String getTextElement(By by) {
        waitForElementVisible(by);
        String text = getDriver().findElement(by).getText();
        LogUtils.info("Get text of element " + by + ": " + text);
        ExtentTestManager.logMessage(Status.PASS, "Get text of element " + by + ": " + text);
        ExtentTestManager.logMessage(Status.INFO, "==> TEXT " + text);
        AllureManager.saveTextLog("==> TEXT " + text);
        return text;
    }

    @Step("Get text of element {0}")
    public static String getTextElement(String locator) {
        By by;

        // Tự detect dạng locator
        if (locator.startsWith("//") || locator.startsWith("(//")) {
            by = By.xpath(locator);
        } else {
            by = By.cssSelector(locator);
        }

        return getTextElement(by);
    }

    @Step("Get attribute: {1} on element {0}")
    public static String getAttributeElement(By by, String attributeName) {
        waitForElementVisible(by);
        String value = getDriver().findElement(by).getAttribute(attributeName);
        LogUtils.info("Get attribute " + attributeName + " of element " + by + ": " + value);
        ExtentTestManager.logMessage(Status.PASS, "Get attribute " + attributeName + " of element " + by + ": " + value);
        ExtentTestManager.logMessage(Status.INFO, "==> ATTRIBUTE " + value);
        AllureManager.saveTextLog("==> ATTRIBUTE " + value);
        return value;
    }

    @Step("Get attribute: {1} on all provided elements")
    public static List<String> getAttributeElements(List<WebElement> elements, String attributeName) {
        List<String> attributeValues = new ArrayList<>();

        if (elements == null || elements.isEmpty()) {
            LogUtils.warn("⚠️ No elements provided to get attribute: " + attributeName);
            return attributeValues;
        }

        LogUtils.info("🔍 Found " + elements.size() + " elements to extract attribute: " + attributeName);

        for (int i = 0; i < elements.size(); i++) {
            WebElement el = elements.get(i);

            try {
                waitForElementVisible(el);
                String value = el.getAttribute(attributeName);
                attributeValues.add(value);

                LogUtils.info("👉 Element [" + i + "] attribute '" + attributeName + "': " + value);
                AllureManager.saveTextLog("==> ATTRIBUTE [" + i + "]: " + value);
            } catch (Exception e) {
                LogUtils.error("❌ Failed to get attribute '" + attributeName + "' for element [" + i + "]: " + e.getMessage());
                attributeValues.add(null);
            }
        }

        return attributeValues;
    }

    public static void waitForAllElementsVisible(By locator, int timeoutInSeconds) {
        LogUtils.info("⏳ Waiting for all elements to be visible...");

        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

        try {
            wait.until(driver1 -> {
                List<WebElement> elements = driver1.findElements(locator);
                if (elements.isEmpty()) return false;

                boolean allVisible = elements.stream().allMatch(WebElement::isDisplayed);
                if (!allVisible) {
                    LogUtils.info("🕓 Not all elements visible yet (" + elements.size() + " found)");
                }
                return allVisible;
            });

            LogUtils.info("✅ All elements are now visible (" +
                    DriverManager.getDriver().findElements(locator).size() + ")");
        } catch (TimeoutException e) {
            LogUtils.warn("⚠️ Timeout waiting for all elements visible after " + timeoutInSeconds + "s");
        }
    }


    public static List<WebElement> getWebElements(By by) {
        return getDriver().findElements(by);
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
        LogUtils.info("📁 Start uploading file using Robot class...");
        LogUtils.info("👉 File to upload: " + filePath);

        try {
            // Step 1: Click vào input form
            LogUtils.info("🔘 Clicking on file upload element: " + elementFileForm);
            WebUI.clickElement(elementFileForm);
            WaitHelper.sleep(3);

            // Step 2: Copy file path vào clipboard
            LogUtils.info("📋 Copying file path to clipboard...");
            StringSelection str = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

            // Step 3: Dán vào hộp thoại chọn file
            LogUtils.info("⌨️  Pasting file path (CTRL + V)...");
            Robot rb = new Robot();
            rb.keyPress(KeyEvent.VK_CONTROL);
            rb.keyPress(KeyEvent.VK_V);
            rb.keyRelease(KeyEvent.VK_CONTROL);
            rb.keyRelease(KeyEvent.VK_V);

            WaitHelper.sleep(3);

            // Step 4: Nhấn Enter để xác nhận chọn file
            LogUtils.info("⏎ Pressing ENTER to confirm upload...");
            rb.keyPress(KeyEvent.VK_ENTER);
            rb.keyRelease(KeyEvent.VK_ENTER);

            WaitHelper.sleep(3);

            // Step 5: Kết thúc
            LogUtils.info("✅ File uploaded successfully.");

        } catch (AWTException e) {
            LogUtils.error("❌ Robot initialization failed: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            LogUtils.error("❌ Upload failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static boolean checkElementDisplayed(By by) {
        try {
            waitForElementVisible(by);
            boolean isDisplayed = getDriver().findElement(by).isDisplayed();
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
            String uiDate = getDriver().findElement(by).getText().trim();

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
            WebElement element = getDriver().findElement(by);
            return element.isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Move to element {0}")
    public static void moveToElement(By by) {
        waitForElementVisible(by);
        Actions actions = new Actions(getDriver());
        actions.moveToElement(getDriver().findElement(by)).perform();
        LogUtils.info("Move to element " + by);
    }

    @Step("Move to element {0}")
    public static void moveToElement(WebElement element) {
        waitForElementVisible(element);
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).perform();
        String info = getElementInfo(element, null);
        LogUtils.info("Move to element " + info);
    }

    public static String getElementInfo(WebElement element, By by) {
        try {
            if (by != null) return by.toString(); // vẫn hiển thị locator khi có
            String text = element.getText();
            String id = element.getAttribute("id");
            String cls = element.getAttribute("class");
            String label = element.getAttribute("aria-label");

            String info = element.getTagName();
            if (!text.isEmpty()) info += " text='" + text + "'";
            if (id != null && !id.isEmpty()) info += " id='" + id + "'";
            if (cls != null && !cls.isEmpty()) info += " class='" + cls + "'";
            if (label != null && !label.isEmpty()) info += " aria-label='" + label + "'";

            return info;
        } catch (Exception e) {
            return element.toString();
        }
    }

    @Step("Accepted Alert")
    public static void acceptAlert() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = getDriver().switchTo().alert();
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
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = getDriver().switchTo().alert();
            alert.dismiss();
            LogUtils.info("Alert dismissed.");
        } catch (Throwable error) {
            LogUtils.info("No alert found after waiting for 10 seconds.");
            Assert.fail("No alert found after waiting for 10 seconds.");
        }
    }

    // Advanced

    public static void setTextAndKey(By by, String value, Keys key) {
        waitForPageLoaded();
        getWebElement(by).sendKeys(value, key);
        LogUtils.info("Set text: " + value + " on element " + by);
    }

    public static String getElementCssValue(By by, String cssPropertyName) {
        waitForElementVisible(by);
        LogUtils.info("Get CSS value " + cssPropertyName + " of element " + by);
        String value = getWebElement(by).getCssValue(cssPropertyName);
        LogUtils.info("==> CSS value: " + value);
        return value;
    }

    @Step("Scroll to element {0}")
    public static void scrollToElement(By by) {
        waitForElementVisible(by);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
    }

    @Step("Scroll to element {0}")
    public static void scrollToElementTrue(WebElement element) {
        waitForElementVisible(element);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Step("Scroll to element {0}")
    public static void scrollToCenterElement(By by) {
        LogUtils.info("🧭 Scrolling to element: " + by);
        waitForElementVisible(by);

        WebElement element = getWebElement(by);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'});",
                element
        );

        WebUI.sleep(1);

        waitForElementVisible(element);
    }

    @Step("Scroll to element {0}")
    public static void scrollToElement(WebElement element) {
        LogUtils.info("🧭 Scrolling to element: " + element);
        waitForElementVisible(element);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'});",
                element
        );
        WebUI.sleep(1);
    }


    public static void scrollToElementAtTop(By by) {
        waitForElementVisible(by);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
    }

    public static void scrollToElementAtBottom(By by) {
        waitForElementVisible(by);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
    }

    public static void scrollToTop() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            long lastScroll = (long) js.executeScript("return window.pageYOffset");

            while (true) {

                js.executeScript("window.scrollTo(0, 0);");
                WebUI.sleep(2);

                long newScroll = (long) js.executeScript("return window.pageYOffset");
                if (newScroll == lastScroll) {
                    break;
                }
                lastScroll = newScroll;
            }
        } catch (Exception e) {
            LogUtils.error("⚠️ Error while scrolling to top: " + e.getMessage());
        }
    }

    public static void scrollToBottom() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            long lastHeight = (long) js.executeScript("return document.body.scrollHeight");

            while (true) {
                js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                WebUI.sleep(2);

                long newHeight = (long) js.executeScript("return document.body.scrollHeight");
                if (newHeight == lastHeight) {
                    break;
                }
                lastHeight = newHeight;
            }
        } catch (Exception e) {
            LogUtils.error("⚠️ Error while scrolling to bottom: " + e.getMessage());
        }
    }

    public static boolean dragAndDrop(By fromElement, By toElement) {
        try {
            Actions action = new Actions(getDriver());
            action.dragAndDrop(getWebElement(fromElement), getWebElement(toElement)).perform();
            LogUtils.info("Dragged and dropped element from " + fromElement + " to " + toElement);
            return true;
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            return false;
        }
    }

    public static void switchToIframe(By iframe) {
        try {
            WebUI.waitForElementVisible(iframe);
            WebElement iframeElement = getWebElement(iframe);
            DriverManager.getDriver().switchTo().frame(iframeElement);
        } catch (Exception e) {
            LogUtils.error("Failed to switch to iframe: " + iframe.toString(), e);
            throw e;
        }
    }

    public static void switchToDefaultContent() {
        try {
            DriverManager.getDriver().switchTo().defaultContent();
        } catch (Exception e) {
            LogUtils.error("Failed to switch to default content.", e);
            throw e;
        }
    }

    public static void waitForElementInVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }




}
