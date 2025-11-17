package test.ui.pexelspages;

import org.openqa.selenium.By;
import settings.helpers.AssertHelper;
import settings.helpers.SystemHelper;
import settings.keywords.WebUI;
import settings.utils.LogUtils;


public class ProfilePage {

    By buttonEditProfile = By.xpath("//span[contains(text(),'Edit profile')]");
    By buttonChangeImage = By.xpath("//label[@for='changeImage']");
    String filePath = SystemHelper.getCurrentDir() + "src\\test\\resources\\testdata\\UK.jpg";
    By inputFirstName = By.xpath("//input[@name='first_name']");
    By inputLastName = By.xpath("//input[@name='last_name']");
    By buttonSaveProfile = By.xpath("//span[contains(text(),'Save profile')]");
    By imageUploaded = By.xpath("(//img[@alt='Bin Dang'])[2]");
    By yourLikes = By.xpath("//h4[normalize-space()='Your Likes']");
    By myCollection = By.xpath("//h4[normalize-space()='My collection']");
    By yourDownloads = By.xpath("//h4[normalize-space()='Your Downloads']");

    public void uploadImageAndUpdateProfile() {
        WebUI.clickElement(buttonEditProfile);
        WebUI.uploadFileWithRobotClass(buttonChangeImage, filePath);
        WebUI.setTextElement(inputFirstName, "Bin");
        WebUI.setTextElement(inputLastName, "Dang");
        WebUI.scrollToElementTest(buttonSaveProfile);
        WebUI.clickElement(buttonSaveProfile);
    }

    public void verifyNewAvatarUploaded() {
        LogUtils.info("🚀 Start verifying avatar upload...");

        WebUI.scrollToElementAtTop(imageUploaded);
        String actualSrc = WebUI.getAttributeElement(imageUploaded, "src");
        LogUtils.info("🖼️ Avatar image src: " + actualSrc);

        AssertHelper.assertTrue(actualSrc != null && !actualSrc.isEmpty(),
                "❌ Avatar src is empty or not found.");

        AssertHelper.assertTrue(WebUI.checkElementExist(imageUploaded), "❌ Avatar image not found on page.");

        LogUtils.info("✅ Avatar uploaded successfully.");
    }

    public void clickOnYourLikes()
    {
        WebUI.clickElement(yourLikes);
    }

    public void clickOnMyCollection()
    {
        WebUI.clickElement(myCollection);
    }

    public void goToDownloadHistoryPage()
    {
        WebUI.openURL("https://www.pexels.com/@bin-dang-2156917326/collections/");
        WebUI.clickElement(yourDownloads);
    }




}
