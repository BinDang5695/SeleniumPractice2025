package test.ui.pixabaypages;

import settings.helpers.AssertHelper;
import settings.keywords.WebUI;
import org.openqa.selenium.By;

public class ImageDetailsPage {

    private By buttonFollow = By.xpath("//button[.//span[normalize-space()='Follow']]");
    private By buttonFollowing = By.xpath("//button[.//span[normalize-space()='Following']]");
    private By buttonDownload = By.xpath("//button[.//span[normalize-space()='Download']]");
    private By buttonUnfollow = By.xpath("//button[.//span[normalize-space()='Unfollow']]");
    private By optionDownload = By.xpath("//a[contains(@class,'buttonBase') and normalize-space(.)='Download']");
    private By buttonX = By.xpath("(//button[@aria-label='Close'])[2]");

    public void clickOnButtonFollow()
    {
        WebUI.moveToElement(buttonFollow);
<<<<<<< HEAD
        WebUI.clickElement(buttonFollow);
=======
        WebUI.clickUntilVisible(buttonFollow);
>>>>>>> 505f0111a689153ed2faf36e7bbe7c06b69d0fc0
        WebUI.moveToElement(buttonDownload);
    }

    public void clickFollowingToUnfollow()
    {
        WebUI.moveToElement(buttonFollowing);
<<<<<<< HEAD
        WebUI.clickElement(buttonUnfollow);
=======
        WebUI.clickUntilVisible(buttonUnfollow);
>>>>>>> 505f0111a689153ed2faf36e7bbe7c06b69d0fc0
    }

    public void verifyButtonFollowChangeToFollowing()
    {
        AssertHelper.assertTrue(WebUI.checkElementExist(buttonFollowing), "The buttonFollowing not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(buttonFollowing), "Following", "The buttonFollowing text not match.");
    }

    public void downloadImage()
    {
<<<<<<< HEAD
        WebUI.clickElement(buttonDownload);
        WebUI.clickElement(optionDownload);
        WebUI.clickElement(buttonX);
=======
        WebUI.clickUntilVisible(buttonDownload);
        WebUI.clickUntilVisible(optionDownload);
        WebUI.clickUntilVisible(buttonX);
>>>>>>> 505f0111a689153ed2faf36e7bbe7c06b69d0fc0
    }

}
