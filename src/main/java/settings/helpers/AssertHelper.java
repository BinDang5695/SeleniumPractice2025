package settings.helpers;

import org.testng.Assert;
import settings.keywords.WebUI;
import settings.utils.LogUtils;

public class AssertHelper {

    public static boolean verifyEquals(Object actual, Object expected) {
        WebUI.waitForPageLoaded();
        LogUtils.info("Verify equals: " + actual + " \uD83D\uDFF0 " + expected);
        boolean check = actual.equals(expected);
        return check;
    }

    public static void assertEquals(Object actual, Object expected, String message) {
        WebUI.waitForPageLoaded();
        LogUtils.info("Assert equals: " + actual + " \uD83D\uDFF0 " + expected);
        Assert.assertEquals(actual, expected, message);
    }

    public static boolean verifyTrue(boolean condition) {
        WebUI.waitForPageLoaded();
        LogUtils.info("Verify true: " + condition);
        return condition;
    }

    public static void assertTrue(boolean condition, String message) {
        WebUI.waitForPageLoaded();
        LogUtils.info("Assert true: " + condition);
        Assert.assertTrue(condition, message);
    }

    public static void assertFalse(boolean condition, String message) {
        WebUI.waitForPageLoaded();
        LogUtils.info("Assert false: " + condition);
        Assert.assertTrue(condition, message);
    }

    public static boolean verifyContains(String actual, String expected) {
        WebUI.waitForPageLoaded();
        LogUtils.info("Verify contains: " + actual + " ↔\uFE0F " + expected);
        boolean check = actual.contains(expected);
        return check;
    }

    public static void assertContains(String actual, String expected, String message) {
        WebUI.waitForPageLoaded();
        LogUtils.info("Assert contains: " + actual + " ↔\uFE0F " + expected);
        boolean check = actual.contains(expected);
        Assert.assertTrue(check, message);
    }

    public static void assertNotContains(String actual, String expected, String message) {
        WebUI.waitForPageLoaded();
        LogUtils.info("Assert not contains: " + actual + " ↔\uFE0F " + expected);
        boolean check = actual.contains(expected);
        Assert.assertFalse(check, message);
    }
}
