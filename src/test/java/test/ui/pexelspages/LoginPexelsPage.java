package test.ui.pexelspages;

import org.openqa.selenium.By;
import settings.helpers.PropertiesHelper;
import settings.keywords.WebUI;

public class LoginPexelsPage {

    private By threeDots = By.xpath("(//button[contains(@class, 'Navbar_transparentButton')])[1]");
    private By optionLogin = By.xpath("//a[normalize-space()='Log in']");
    private By inputEmail = By.xpath("//input[@placeholder='name@email.com']");
    private By inputPassword = By.xpath("//input[@placeholder='Password']");
    private By buttonLogin = By.xpath("//span[contains(text(),'Log in')]");
    private By logoIcon = By.xpath("//div[contains(@class, 'Navbar_left')]");

    public void login() {
        WebUI.openURL("https://www.pexels.com/");
        WebUI.waitForPageLoaded();
        WebUI.clickUntilVisible(threeDots, optionLogin);
        WebUI.clickUntilVisible(optionLogin, inputEmail);
        WebUI.setTextElement(inputEmail, PropertiesHelper.getValue("EMAIL"));
        WebUI.setTextElement(inputPassword, PropertiesHelper.getValue("PASSWORD"));
        WebUI.clickUntilVisible(buttonLogin, logoIcon);
    }

    public void clickOnPexelsIcon()
    {
        WebUI.clickElement(logoIcon);
    }



}
