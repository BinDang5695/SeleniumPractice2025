package com.anhtester.pixabaypages;

import com.anhtester.helpers.SystemHelper;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;

public class SettingsPage {

    private By buttonEdit = By.xpath("//div[@class='container--eBEkE clickable--6WIEU xl--zIL1f']");
    private By buttonBrowseFiles = By.xpath("//span[normalize-space()='Browse files']");
    String filePath = SystemHelper.getCurrentDir() + "src\\test\\resources\\testdata\\UK.jpg";
    private By buttonApply = By.xpath("(//button[.//span[text()='Apply']])[2]");
    private By buttonClose = By.xpath("//span[normalize-space()='Close']");
    private By inputFirstName = By.xpath("//input[@placeholder='Enter your first name']");
    private By inputLastName = By.xpath("//input[@placeholder='Enter your last name']");
    private By inputAboutMe = By.xpath("//textarea[@placeholder='In a few words, tell us about yourself']");
    private By inputCity = By.xpath("//input[@placeholder='Enter your city']");
    private By buttonSaveChanges = By.xpath("//span[normalize-space()='Save changes']");
    private By imageAfterUploaded = By.xpath("(//div[contains(@class,'container--eBEkE')]//img[contains(@class,'image--vdlQM')])[3]");

    public void clickOnButtonEdit()
    {
        WebUI.moveToElement(buttonEdit);
        WebUI.clickElement(buttonEdit);
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
        WebUI.getTextElement(imageAfterUploaded);
    }

    public void verifyNewAvatarUploaded() {
        WebUI.assertTrue(WebUI.checkElementExist(imageAfterUploaded), "The new avatar image not display.");
        WebUI.assertTrue(WebUI.getAttributeElement(imageAfterUploaded, "src").contains("pixabay.com"),"The new avatar image src not match.");
    }

}
