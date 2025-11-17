package settings.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("reports/extentreport/extentreport.html");
        reporter.config().setReportName("Extent Report | Bin Tester");
        reporter.config().setTheme(Theme.DARK);
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Framework Name", "Selenium Java | Bin Tester");
        extentReports.setSystemInfo("Author", "Bin Tester");
        return extentReports;
    }

}
