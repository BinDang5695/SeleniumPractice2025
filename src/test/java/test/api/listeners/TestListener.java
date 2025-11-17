package test.api.listeners;

import settings.globals.ConfigsGlobal;
import settings.helpers.PropertiesHelper;
import settings.reports.AllureManager;
import settings.utils.LogUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    @Override
    public void onStart(ITestContext result) {
        LogUtils.info("⚙\uFE0F Setup Environment on Start:" + result.getStartDate());
        PropertiesHelper.loadAllFiles();
    }

    @Override
    public void onFinish(ITestContext result) {
        LogUtils.info("End test: " + result.getEndDate());
        LogUtils.info("⭐ Test Total: " + ConfigsGlobal.TCS_TOTAL);
        LogUtils.info("⭐ Test Passed Total: " + ConfigsGlobal.PASSED_TOTAL);
        LogUtils.info("⭐ Test Failed Total: " + ConfigsGlobal.FAILED_TOTAL);
        LogUtils.info("⭐ Test Skipped Total: " + ConfigsGlobal.SKIPPED_TOTAL);
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("▶\uFE0F Start run test case: " + result.getName());
        ConfigsGlobal.TCS_TOTAL++;
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("✅ Test case " + result.getName() + " is passed.");
        ConfigsGlobal.PASSED_TOTAL++;
        AllureManager.saveTextLog("✅ Test case " + result.getName() + " is passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("❌ Test case " + result.getName() + " is failed.");
        LogUtils.error(result.getThrowable());
        ConfigsGlobal.FAILED_TOTAL++;
        AllureManager.saveTextLog("❌ Test case " + result.getName() + " is failed.");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.warn("⚠️ Test case " + result.getName() + " is skipped.");
        LogUtils.error(result.getThrowable());
        AllureManager.saveTextLog("⚠️ Test case " + result.getName() + " is skipped.");
    }
}