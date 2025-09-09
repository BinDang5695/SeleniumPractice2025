package com.anhtester.pixabaypages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;

public class LibraryPage {

    private By tabLikes = By.xpath("//span[normalize-space()='Likes']");
    private By image1 = By.xpath("//div[@class='column--HhhwH'][1]");
    private By image2 = By.xpath("//div[@class='column--HhhwH'][2]");
    private By image3 = By.xpath("//div[@class='column--HhhwH'][3]");
    private By inputCollectionName = By.xpath("//div[@class='content--AsYbE']//input[@class='interactiveInputStyle--JrJMF textInput--yG-0W']");
    private By linkCreate = By.xpath("//button[@class='createButton--Z+cWm buttonBase--r4opq tertiaryButton--+4ehJ base--jzyee light--uBcBI']");
    private By createdCollection = By.xpath("//a[normalize-space()='Bin Create New Collection']");
    private By buttonFindMedia = By.xpath("//span[normalize-space()='Find media']");
    private By buttonCreate = By.xpath("//span[@class='label--9n8oV']");
    private By removeFromCollection = By.xpath("//div[@class='verticalMasonry--xIvQk multiColumn--qWb9Y lgColumnGap--ICoun']//div[2]//div[1]//div[1]//div[1]//div[1]//div[1]//button[1]//span[1]");
    private By buttonRemove = By.xpath("//div[@class='rightButtons--wQ+Wt']//button[@type='button']");
    private By downloadHistoryPage = By.xpath("//span[normalize-space()='Download history']");


    private By totalImageOnCollection = By.xpath("//span[normalize-space()='1 item']");


    public void clickOnTabLikes()
    {
        WebUI.clickElement(tabLikes);
    }

    public void verifyLikedImages() {
        WebUI.assertTrue(WebUI.checkElementExist(image1), "The image1Likes not exist.");
        WebUI.assertTrue(WebUI.checkElementDisplayed(image1), "The image1Likes not display");
        WebUI.assertTrue(WebUI.checkElementExist(image2), "The image2Likes not exist.");
        WebUI.assertTrue(WebUI.checkElementDisplayed(image2),"The image1Likes not display.");
        WebUI.assertTrue(WebUI.checkElementExist(image3), "The image3Likes not exist.");
        WebUI.assertTrue(WebUI.checkElementDisplayed(image3),"The image1Likes not display.");
    }

    public void verifyImagesOnCollection() {
        WebUI.assertTrue(WebUI.checkElementExist(image1), "The image1 not exist.");
        WebUI.assertTrue(WebUI.checkElementDisplayed(image1), "The image1 not display");
        //WebUI.assertFalse(WebUI.checkElementExist(image2), "The image2 still exist.");
        //WebUI.assertFalse(WebUI.checkElementDisplayed(image2),"The image2 still display.");
        WebUI.assertTrue(WebUI.checkElementExist(totalImageOnCollection), "The totalImageOnCollection not exist.");
        WebUI.assertTrue(WebUI.checkElementDisplayed(totalImageOnCollection),"The totalImageOnCollection not display.");
        WebUI.assertEquals(WebUI.getTextElement(totalImageOnCollection),"1 item", "The totalImageOnCollection not match.");
    }

    public void verifyImagesOnDownloadHistory() {
        WebUI.assertTrue(WebUI.checkElementExist(image1), "The image1 not exist.");
        WebUI.assertTrue(WebUI.checkElementDisplayed(image1), "The image1 not display");
    }

    public void createNewCollection()
    {
        WebUI.clickElement(linkCreate);
        WebUI.setTextElement(inputCollectionName, "Bin Create New Collection");
        WebUI.clickElement(buttonCreate);
    }

    public void clickOnCreatedCollection()
    {
        WebUI.clickElement(createdCollection);
    }

    public void clickOnButtonFindMedia()
    {
        WebUI.clickElement(buttonFindMedia);
    }

    public void removeImage2FromCollection()
    {
        WebUI.moveToElement(image2);
        WebUI.clickElement(removeFromCollection);
        WebUI.clickElement(buttonRemove);
    }

    public void goToDownloadHistoryPage()
    {
        WebUI.openURL("https://pixabay.com/accounts/collections/");
        WebUI.clickElement(downloadHistoryPage);
    }


}
