package settings.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import settings.helpers.PropertiesHelper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    public static WebDriver createDriver(String browserName) {
        String headlessValue = PropertiesHelper.getValue("HEADLESS");
        boolean isHeadless = headlessValue != null && headlessValue.equalsIgnoreCase("true");

        String downloadDir = DriverManager.getDownloadPath();
        File folder = new File(downloadDir);
        if (!folder.exists()) folder.mkdirs();

        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("download.default_directory", downloadDir);
                prefs.put("download.prompt_for_download", false);
                prefs.put("download.directory_upgrade", true);
                prefs.put("safebrowsing.enabled", true);
                chromeOptions.setExperimentalOption("prefs", prefs);

                if (isHeadless) chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--remote-allow-origins=*");

                return new ChromeDriver(chromeOptions);

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();

                Map<String, Object> edgePrefs = new HashMap<>();
                edgePrefs.put("download.default_directory", downloadDir);
                edgePrefs.put("download.prompt_for_download", false);
                edgePrefs.put("download.directory_upgrade", true);
                edgePrefs.put("safebrowsing.enabled", true);
                edgeOptions.setExperimentalOption("prefs", edgePrefs);

                if (isHeadless) edgeOptions.addArguments("--headless");
                edgeOptions.addArguments("--start-maximized");

                return new EdgeDriver(edgeOptions);

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                firefoxOptions.addPreference("browser.download.folderList", 2);
                firefoxOptions.addPreference("browser.download.dir", downloadDir);
                firefoxOptions.addPreference("browser.download.useDownloadDir", true);
                firefoxOptions.addPreference("browser.download.viewableInternally.enabledTypes", "");
                firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk",
                        "application/pdf,application/octet-stream,image/jpeg,image/png,text/csv,application/zip");
                firefoxOptions.addPreference("pdfjs.disabled", true);

                if (isHeadless) firefoxOptions.addArguments("--headless");

                return new FirefoxDriver(firefoxOptions);

            default:
                System.out.println("❗ Unsupported browser: " + browserName + ", using Chrome by default.");
                return new ChromeDriver();
        }
    }
}