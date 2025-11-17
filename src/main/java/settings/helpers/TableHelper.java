package settings.helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import settings.drivers.DriverManager;
import settings.utils.LogUtils;

import java.util.List;

public class TableHelper {

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
