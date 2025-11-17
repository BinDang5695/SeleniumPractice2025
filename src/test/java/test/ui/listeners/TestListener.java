package test.ui.listeners;

import settings.helpers.CaptureHelper;
import settings.reports.AllureManager;
import settings.reports.ExtentReportManager;
import settings.reports.ExtentTestManager;
import settings.utils.LogUtils;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static int test_total;
    private static int test_passed_total;
    private static int test_failed_total;
    private static int test_skipped_total;

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext result) {
        LogUtils.info("⚙\uFE0F Setup Environment onStart:" + result.getStartDate());
        //CaptureHelper.startRecord(result.getName());
    }

    @Override
    public void onFinish(ITestContext result) {
        LogUtils.info("End test: " + result.getEndDate());
        LogUtils.info("⭐ Test Total: " + test_total);
        LogUtils.info("⭐ Test Passed Total: " + test_passed_total);
        LogUtils.info("⭐ Test Failed Total: " + test_failed_total);
        LogUtils.info("⭐ Test Skipped Total: " + test_skipped_total);
        ExtentReportManager.getExtentReports().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("▶\uFE0F Start run test case: " + result.getName());
        test_total++;
        ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));
        CaptureHelper.startRecord(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("✅ Test case " + result.getName() + " is passed.");
        ExtentTestManager.logMessage(Status.PASS, "✅ Test case " + result.getName() + " is passed");
        AllureManager.saveTextLog("✅ Test case " + result.getName() + " is passed.");
        test_passed_total++;
        CaptureHelper.stopRecord(1);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("❌ Test case " + result.getName() + " is failed.");
        LogUtils.error(result.getThrowable());

        //ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
        //ExtentTestManager.addScreenshot(result.getName());
        //ExtentTestManager.logMessage(Status.FAIL, "❌ Test case " + result.getName() + " is failed.");

        AllureManager.saveTextLog("❌ Test case " + result.getName() + " is failed.");
        AllureManager.saveScreenshotPNG();
        test_failed_total++;
        CaptureHelper.captureScreenshot(result.getName());
        CaptureHelper.stopRecord(1);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.warn("⚠️ Test case " + result.getName() + " is skipped.");
        LogUtils.error(result.getThrowable());
        //ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString() + " is skipped");
        //ExtentTestManager.logMessage(Status.SKIP, result.getName() + "is skipped");
        AllureManager.saveTextLog("⚠️ Test case " + result.getName() + " is skipped.");
        test_skipped_total++;
        CaptureHelper.stopRecord(1);
    }
}
