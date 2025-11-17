package test.ui.pixabaypages;

import settings.helpers.AssertHelper;
import settings.helpers.SystemHelper;
import settings.keywords.WebUI;
import org.openqa.selenium.By;
import settings.utils.LogUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SettingsPage {

    private By buttonEdit = By.xpath("//button[contains(@class, 'editButton')]");
    private By buttonBrowseFiles = By.xpath("//span[normalize-space()='Browse files']");
    String filePath = SystemHelper.getCurrentDir() + "src\\test\\resources\\testdata\\UK.jpg";
    private By buttonApply = By.xpath("(//button[.//span[text()='Apply']])[2]");
    private By buttonClose = By.xpath("//span[normalize-space()='Close']");
    private By inputFirstName = By.xpath("//input[@placeholder='Enter your first name']");
    private By inputLastName = By.xpath("//input[@placeholder='Enter your last name']");
    private By inputAboutMe = By.xpath("//textarea[@placeholder='In a few words, tell us about yourself']");
    private By inputCity = By.xpath("//input[@placeholder='Enter your city']");
    private By buttonSaveChanges = By.xpath("//span[normalize-space()='Save changes']");
    private By imageUploaded = By.xpath("(//img[contains(@class, 'image')])[4]");
    private By allImagesInSettingPage = By.xpath("//img[contains(@class, 'image')]");

    public void clickOnButtonEdit()
    {
        WebUI.waitForAllElementsVisible(allImagesInSettingPage, 10);
        WebUI.moveToElement(imageUploaded);
        WebUI.scrollToElement(imageUploaded);
        WebUI.clickUntilVisible(buttonEdit);
    }

    public void uploadNewAvatar() {
        WebUI.uploadFileWithRobotClass(buttonBrowseFiles, filePath);
    }

    public void clickOnButtonApply() {
        WebUI.clickElement(buttonApply);
    }

    public void clickOnButtonClose() {
        WebUI.clickElement(buttonClose);
    }

    public void inputInformationAndSave() {
        WebUI.setTextElement(inputFirstName, "Bin");
        WebUI.setTextElement(inputLastName, "Dang");
        WebUI.setTextElement(inputAboutMe, "Bindz");
        WebUI.setTextElement(inputCity, "DN");
        WebUI.clickElement(buttonSaveChanges);
        WebUI.getTextElement(imageUploaded);
    }

    public void verifyNewAvatarUploaded() {
        LogUtils.info("🚀 Start verifying avatar upload...");

        WebUI.waitForElementVisible(imageUploaded);
        String actualSrc = WebUI.getAttributeElement(imageUploaded, "src");
        LogUtils.info("🖼️ Avatar image src: " + actualSrc);

        AssertHelper.assertTrue(actualSrc != null && !actualSrc.isEmpty(),
                "❌ Avatar src is empty or not found.");

        AssertHelper.assertTrue(actualSrc.startsWith("blob:https://pixabay.com/"),
                "❌ Avatar src is not a blob URL after upload. Found: " + actualSrc);

        LogUtils.info("✅ Avatar uploaded successfully and previewed via blob URL.");
    }
}
