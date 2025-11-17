package test.ui.pixabaypages;

import settings.helpers.PropertiesHelper;
import settings.keywords.WebUI;
import org.openqa.selenium.By;

public class LoginPixabay {

    private By linkLogin = By.xpath("//button[.//span[normalize-space()='Log in']]");
    private By inputEmail = By.xpath("//input[@name='login_user']");
    private By inputPassword = By.xpath("//input[@name='login_pass']");
    private By buttonLogin = By.xpath("(//button[.//span[normalize-space()='Log in']])[2]");
    private By imageProfile = By.xpath("(//div[.//img[@alt='Bin Dang']])[5]");

<<<<<<< HEAD
    public HomePage login()
    {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.waitForPageLoaded();
=======
    public void login()
    {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
>>>>>>> 505f0111a689153ed2faf36e7bbe7c06b69d0fc0
        WebUI.waitForElementVisible(linkLogin);
        WebUI.scrollToElement(linkLogin);
        WebUI.moveToElement(linkLogin);
        WebUI.clickUntilVisible(linkLogin, inputEmail);
        WebUI.setTextElement(inputEmail, PropertiesHelper.getValue("EMAIL"));
        WebUI.setTextElement(inputPassword, PropertiesHelper.getValue("PASSWORD"));
        WebUI.clickUntilVisible(buttonLogin, imageProfile);
<<<<<<< HEAD
        return new HomePage();
=======
>>>>>>> 505f0111a689153ed2faf36e7bbe7c06b69d0fc0
    }

}
