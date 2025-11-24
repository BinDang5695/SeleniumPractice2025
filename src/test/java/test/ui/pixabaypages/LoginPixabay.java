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
    private By linkForgotPassword = By.xpath("//button[normalize-space()='Forgot password?']");
    private By inputResetEmail = By.xpath("//input[@id='reset_password_email_input']");
    private By buttonResetPassword = By.xpath("//div[@class='container--oGFZI']//button[@type='button']");

    public HomePage login()
    {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(linkLogin);
        WebUI.scrollToElement(linkLogin);
        WebUI.moveToElement(linkLogin);
        WebUI.clickUntilVisible(linkLogin, inputEmail);
        WebUI.setTextElement(inputEmail, PropertiesHelper.getValue("EMAIL"));
        WebUI.setTextElement(inputPassword, PropertiesHelper.getValue("PASSWORD"));
        WebUI.clickUntilVisible(buttonLogin, imageProfile);
        return new HomePage();
    }

    public HomePage forgotPassword(String email)
    {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(linkLogin);
        WebUI.scrollToElement(linkLogin);
        WebUI.moveToElement(linkLogin);
        WebUI.clickUntilVisible(linkLogin, linkForgotPassword);
        WebUI.clickElement(linkForgotPassword);
        WebUI.setTextElement(inputResetEmail, email);
        WebUI.waitForElementVisible(buttonResetPassword);
        WebUI.clickElement(buttonResetPassword);
        return new HomePage();
    }

}
