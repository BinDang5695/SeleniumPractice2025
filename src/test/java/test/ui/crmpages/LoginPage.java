package test.ui.crmpages;

import settings.helpers.AssertHelper;
import settings.helpers.PropertiesHelper;
import settings.keywords.WebUI;
import settings.reports.AllureManager;
import org.openqa.selenium.By;

public class LoginPage {

        private By headerLogin = By.xpath("//h1[normalize-space()='Login']");
        private By inputEmail = By.xpath("//input[@id='email']");
        private By inputPassword = By.xpath("//input[@id='password']");
        private By checkboxRememberme = By.xpath("//input[@id='remember']");
        private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
        private By linkForgotPassword = By.xpath("//a[normalize-space()='Forgot Password?']");
        private By alertErrorMessage = By.xpath("//div[contains(@class,'alert alert-danger')]");
        private By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");

        public DashboardPage loginCRM(String email, String password)
        {
            WebUI.openURL(PropertiesHelper.getValue("URL"));
            WebUI.waitForPageLoaded();
            WebUI.setTextElement(inputEmail, email);
            WebUI.setTextElement(inputPassword, password);
            WebUI.clickElement(buttonLogin);
            return new DashboardPage();
        }

        public DashboardPage loginCRM()
        {
            WebUI.openURL(PropertiesHelper.getValue("URL"));
            WebUI.waitForPageLoaded();
            WebUI.setTextElement(inputEmail, PropertiesHelper.getValue("EMAIL"));
            WebUI.setTextElement(inputPassword, PropertiesHelper.getValue("PASSWORD"));
            WebUI.clickElement(buttonLogin);
            return new DashboardPage();
        }

        public DashboardPage waitForMenuDashboard()
        {
            WebUI.waitForElementVisible(menuDashboard);
            return new DashboardPage();
        }

        public void verifyLoginSuccess()
        {
            WebUI.waitForElementVisible(menuDashboard);
            AssertHelper.assertNotContains(WebUI.getCurrentURL(), "authentication", "Still in Login page, but expected to be in Dashboard page");
        }

        public void verifyLoginFail(String text)
        {
            AssertHelper.assertContains(WebUI.getCurrentURL(), "authentication", "Login success, but expected to fail");
            AssertHelper.assertTrue(WebUI.checkElementDisplayed(alertErrorMessage), "Login success, but expected to fail");
            AssertHelper.assertEquals(WebUI.getTextElement(alertErrorMessage), text, "Error message is not as expected");
            AllureManager.saveTextLog("==> Login failed with error message: " + text);
        }









}
