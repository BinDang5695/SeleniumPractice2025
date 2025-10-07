package settings.drivers;

import org.openqa.selenium.WebDriver;
import java.io.File;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private static final String DOWNLOAD_DIR = System.getProperty("user.dir") + File.separator + "downloads";

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driver) {
        DriverManager.driver.set(driver);
    }

    public static void quit() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    public static String getDownloadPath() {
        return DOWNLOAD_DIR;
    }
}
