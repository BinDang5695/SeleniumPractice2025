package com.anhtester.pixabaypages;

import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;

public class LoginPixabayPage {

    private By linkLogin = By.xpath("//body/div[@id='app']/div[@data-hid='root']/header[@class='header--TuwRF dark--vRbZ4']/div[@class='innerContainer--yNnyc']/div[@class='actionsRight--cgZPe']/div[2]/button[1]");
    private By inputEmail = By.xpath("//input[@name='login_user']");
    private By inputPassword = By.xpath("//input[@name='login_pass']");
    private By buttonLogin = By.xpath("(//button[.//span[normalize-space()='Log in']])[2]");

    public HomePage login()
    {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.waitForPageLoaded();
        WebUI.clickElement(linkLogin);
        WebUI.setTextElement(inputEmail, PropertiesHelper.getValue("EMAIL"));
        WebUI.setTextElement(inputPassword, PropertiesHelper.getValue("PASSWORD"));
        WebUI.clickElement(buttonLogin);
        return new HomePage();
    }

}
