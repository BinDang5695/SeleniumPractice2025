package com.anhtester.common;

import com.anhtester.pages.CommonPage;
import com.anhtester.drivers.DriverManager;
import com.anhtester.helpers.CaptureHelper;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.listeners.TestListener;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.time.Duration;

@Listeners(TestListener.class)
public class BaseTest extends CommonPage {

        @BeforeSuite
        public void setUpEnvironment() {
            System.out.println("Before Suite: Load Config Files");
            String env = System.getProperty("env", "test");
            PropertiesHelper.loadByEnvironment(env);
            LogUtils.info("Loaded config for ENV = " + env);
            //PropertiesHelper.loadAllFiles();
        }

        @BeforeMethod
        @Parameters({"browser"})
        @Step("Run with Browser: {0}")
        public void createDriver(@Optional("chrome") String browser){
            WebDriver driver;

            if(PropertiesHelper.getValue("BROWSER").isEmpty() && PropertiesHelper.getValue("BROWSER").isBlank()) {
                browser = browser; // Default browser
            } else {
                browser = PropertiesHelper.getValue("BROWSER");
            }

            switch (browser){
                case "chrome":
                    driver = new ChromeDriver();
                    System.out.println("Launching Chrome browser");
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    System.out.println("Launching Edge browser");
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    System.out.println("Launching Firefox browser");
                    break;
                default:
                    driver = new ChromeDriver();
                    System.out.println("Browser not supported, launching Chrome browser by default");
                    break;
            }
            DriverManager.setDriver(driver); // Set the driver in ThreadLocal

            DriverManager.getDriver().manage().window().maximize();
            DriverManager.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        }

        @AfterMethod
        public void closeDriver()
        {
            if(DriverManager.getDriver() != null)
                DriverManager.quit();
        }

}
