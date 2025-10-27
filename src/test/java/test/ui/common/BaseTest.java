package test.ui.common;

import settings.drivers.DriverFactory;
import settings.drivers.DriverManager;
import settings.helpers.PropertiesHelper;
import settings.utils.LogUtils;
import test.ui.listeners.TestListener;
import test.ui.crmpages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.time.Duration;

@Listeners(TestListener.class)
public class BaseTest extends BasePage {

        @BeforeSuite
        public void setUpEnvironment() {
            System.out.println("Before Suite: Load Config Files");
            PropertiesHelper.loadAllFiles();
        }

        @BeforeMethod
        @Parameters({"browser"})
        @Step("Run with Browser: {0}")
        public void createDriver(@Optional("chrome") String browser){

            WebDriver driver = DriverFactory.createDriver(browser);

            String configBrowser = PropertiesHelper.getValue("BROWSER");
            if (configBrowser != null && !configBrowser.isBlank()) {
                browser = configBrowser;
            }

            DriverManager.setDriver(driver); // Set the driver in ThreadLocal
            DriverManager.getDriver().manage().window().maximize();
            DriverManager.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            LogUtils.info("✅ Browser launched: " + browser);
        }

        @AfterMethod()
        public void closeDriver()
        {
            if(DriverManager.getDriver() != null)
                DriverManager.quit();
            LogUtils.info("🛑 Browser closed");
        }

}
