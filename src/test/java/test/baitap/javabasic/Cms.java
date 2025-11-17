package test.baitap.javabasic;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import settings.keywords.WebUI;
import test.ui.common.BaseTest;

public class Cms extends BaseTest {

    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private By menuProducts = By.xpath("//span[normalize-space()='Products']");
    private By subMenuAddNewProduct = By.xpath("//span[normalize-space()='Add New Product']");

    @Test
    public void testCMS() {

        WebUI.openURL("https://cms.anhtester.com/login");
        WebUI.waitForPageLoaded();
        WebUI.setTextElement(inputEmail, "admin@example.com");
        WebUI.setTextElement(inputPassword, "123456");
        WebUI.clickElement(buttonLogin);
        WebUI.clickElement(menuProducts);
        WebUI.clickElement(subMenuAddNewProduct);


    }

}
