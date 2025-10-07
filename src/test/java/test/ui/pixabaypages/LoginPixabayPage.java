package test.ui.pixabaypages;

import settings.helpers.PropertiesHelper;
import settings.keywords.WebUI;
import org.openqa.selenium.By;

public class LoginPixabayPage {

    private By linkLogin = By.xpath("//button[.//span[normalize-space()='Log in']]");
    private By inputEmail = By.xpath("//input[@name='login_user']");
    private By inputPassword = By.xpath("//input[@name='login_pass']");
    private By buttonLogin = By.xpath("(//button[.//span[normalize-space()='Log in']])[2]");

    public HomePage login()
    {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(linkLogin);
        WebUI.scrollToElement(linkLogin);
        WebUI.moveToElement(linkLogin);
        WebUI.sleep(5);
        WebUI.clickElement(linkLogin);
        WebUI.setTextElement(inputEmail, PropertiesHelper.getValue("EMAIL"));
        WebUI.setTextElement(inputPassword, PropertiesHelper.getValue("PASSWORD"));
        WebUI.clickElement(buttonLogin);
        return new HomePage();
    }

}
