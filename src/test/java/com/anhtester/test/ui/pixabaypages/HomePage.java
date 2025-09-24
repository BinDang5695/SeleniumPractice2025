package com.anhtester.test.ui.pixabaypages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;

public class HomePage {

    private By firstImage = By.xpath("(//img)[3]");
    private By secondImage = By.xpath("(//img)[7]");
    private By thirdImage = By.xpath("(//img)[11]");
    private By imageProfile = By.xpath("//span[@class='label--Ngqjq']//div[@class='container--eBEkE md--vF-bE']");
    private By optionMyProfile = By.xpath("//label[normalize-space()='My profile']");
    private By optionSettings = By.xpath("//label[normalize-space()='Settings']");
    private By optionLibrary = By.xpath("//label[normalize-space()='Library']");
    private By heartImage1 = By.xpath("(//button[.//div[@role='img' and @aria-label='HeartOutline']])[1]");
    private By heartImage2 = By.xpath("(//button[.//div[@role='img' and @aria-label='HeartOutline']])[2]");
    private By heartImage3 = By.xpath("(//button[.//div[@role='img' and @aria-label='HeartOutline']])[3]");
    private By addImage1ToCollection = By.xpath("(//button[.//div[@role='img' and @aria-label='BookmarkOutline']])[1]");
    private By addImage2ToCollection = By.xpath("(//button[.//div[@role='img' and @aria-label='BookmarkOutline']])[2]");
    private By buttonAdd = By.xpath("//div[@class='collectionsContainer--ddRnN']//div[1]//button[1]");
    private By buttonX = By.xpath("//div[@class='container--oGFZI dialogContainer--H+0sJ dialogContainer--DZr5T lg--CQTj3']//button[@class='closeButton--iH9l-']");



    public void clickOnFirstImage()
    {
        WebUI.clickElement(firstImage);
    }

    public void clickOnImageProfile()
    {
        WebUI.clickElement(imageProfile);
    }

    public void clickOnOptionSettings()
    {
        WebUI.clickElement(optionSettings);
    }

    public void clickOnHeartImage1()
    {
        WebUI.scrollToElementAtBottom(firstImage);
        WebUI.moveToElement(firstImage);
        WebUI.clickElement(heartImage1);
    }

    public void clickOnHeartImage2()
    {
        WebUI.scrollToElementAtBottom(secondImage);
        WebUI.moveToElement(secondImage);
        WebUI.waitForPageLoaded();
        WebUI.clickElement(heartImage2);
    }

    public void clickOnHeartImage3()
    {
        WebUI.scrollToElementAtBottom(thirdImage);
        WebUI.moveToElement(thirdImage);
        WebUI.waitForPageLoaded();
        WebUI.clickElement(heartImage3);
    }

    public void addImage1ToCollection()
    {
        WebUI.scrollToElementAtBottom(firstImage);
        WebUI.moveToElement(firstImage);
        WebUI.clickElement(addImage1ToCollection);
        WebUI.clickElement(buttonAdd);
        WebUI.clickElement(buttonX);
    }

    public void addImage2ToCollection()
    {
        WebUI.scrollToElementAtBottom(secondImage);
        WebUI.moveToElement(secondImage);
        WebUI.clickElement(addImage2ToCollection);
        WebUI.clickElement(buttonAdd);
        WebUI.clickElement(buttonX);
    }

    public void goToCollection()
    {
        WebUI.openURL("https://pixabay.com/accounts/collections/");
    }

    public void clickOnOptionLibrary()
    {
        WebUI.clickElement(optionLibrary);
    }


}
