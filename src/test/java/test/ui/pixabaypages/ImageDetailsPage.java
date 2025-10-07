package test.ui.pixabaypages;

import settings.helpers.AssertHelper;
import settings.keywords.WebUI;
import org.openqa.selenium.By;

public class ImageDetailsPage {

    private By buttonFollow = By.xpath("//div[@class='container--KMomB fullWidth---6Rqv']//button[@type='button']");
    private By buttonFollowing = By.xpath("//span[normalize-space()='Following']");
    private By buttonDownload = By.xpath("//span[normalize-space()='Download']");
    private By buttonUnfollow = By.xpath("//div[@class='unfollow--4xutc']//button[@type='button']");
    private By optionDownload = By.xpath("//a[@class='buttonBase--r4opq primaryButton--h-+gi base--jzyee']//span[@class='label--Ngqjq'][normalize-space()='Download']");
    private By buttonClose = By.xpath("//button[@aria-label='Close']//span[@class='icon--L+lBh close--qCtdc']");

    public void clickOnButtonFollow()
    {
        WebUI.moveToElement(buttonFollow);
        WebUI.clickElement(buttonFollow);
        WebUI.moveToElement(buttonDownload);
    }

    public void clickFollowingToUnfollow()
    {
        WebUI.moveToElement(buttonFollowing);
        WebUI.clickElement(buttonUnfollow);
    }

    public void verifyButtonFollowChangeToFollowing()
    {
        AssertHelper.assertTrue(WebUI.checkElementExist(buttonFollowing), "The buttonFollowing not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(buttonFollowing), "Following", "The buttonFollowing text not match.");
    }

    public void downloadImage()
    {
        WebUI.clickElement(buttonDownload);
        WebUI.clickElement(optionDownload);
        WebUI.clickElement(buttonClose);
    }









}
