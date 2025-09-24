package com.anhtester.test.ui.pixabaypages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;

public class ImageDetailsPage {

    private By buttonFollow = By.xpath("//span[normalize-space()='Follow']");
    private By buttonFollowing = By.xpath("//span[normalize-space()='Following']");
    private By buttonDownload = By.xpath("//span[normalize-space()='Download']");
    private By optionDownload = By.xpath("//a[@class='buttonBase--r4opq primaryButton--h-+gi base--jzyee']//span[@class='label--Ngqjq'][normalize-space()='Download']");
    private By buttonClose = By.xpath("//button[@aria-label='Close']//span[@class='icon--L+lBh close--qCtdc']");

    public void clickOnButtonFollow()
    {
        WebUI.moveToElement(buttonFollow);
        WebUI.clickElement(buttonFollow);
        WebUI.moveToElement(buttonDownload);
    }

    public void verifyButtonFollowChangeToFollowing()
    {
        WebUI.assertTrue(WebUI.checkElementExist(buttonFollowing), "The buttonFollowing not display.");
        WebUI.assertEquals(WebUI.getTextElement(buttonFollowing), "Following", "The buttonFollowing text not match.");
    }

    public void downloadImage()
    {
        WebUI.clickElement(buttonDownload);
        WebUI.clickElement(optionDownload);
        WebUI.clickElement(buttonClose);
    }









}
