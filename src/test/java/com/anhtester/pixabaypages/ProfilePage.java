package com.anhtester.pixabaypages;

import com.anhtester.helpers.SystemHelper;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;

public class ProfilePage {

    private By buttonUpload = By.xpath("//span[@class='label--Ngqjq'][normalize-space()='Upload']");
    private By checkboxAgree = By.xpath("//span[contains(@class,'checkboxIcon--wBTAF')]");
    private By buttonStartUploading = By.xpath("//button[.//span[normalize-space()='Start uploading']]");
    private By buttonBrowserFiles = By.xpath("//div[@class='infoSection--rneYK']//button[@type='button']");
    String filePath = SystemHelper.getCurrentDir() + "src\\test\\resources\\testdata\\download.jpg";
    private By inputTags = By.xpath("//input[@placeholder='Add tags']");
    private By optionBin = By.xpath("//div[normalize-space()='bin']");
    private By optionVietNam = By.xpath("//div[normalize-space()='vietnam']");
    private By optionPeople = By.xpath("//div[normalize-space()='people']");
    private By buttonSubmit = By.xpath("//span[normalize-space()='Submit']");


    public void clickOnButtonUpload()
    {
        WebUI.clickElement(buttonUpload);
    }

    public void clickOnCheckboxAgree()
    {
        WebUI.clickElement(checkboxAgree);
    }

    public void clickOnButtonStartUploading()
    {
        WebUI.clickElement(buttonStartUploading);
    }

    public void uploadNewAvatar() {
        WebUI.uploadFileWithRobotClass(buttonBrowserFiles, filePath);
    }

    public void inputTags1() {
        WebUI.setTextElement(inputTags, "bin");
    }

    public void selectOptionBin() {
        WebUI.clickElement(optionBin);
    }

    public void inputTags2() {
        WebUI.setTextElement(inputTags, "vietnam");
    }

    public void selectOptionVietNam() {
        WebUI.clickElement(optionVietNam);
    }

    public void inputTags3() {
        WebUI.setTextElement(inputTags, "people");
    }

    public void selectOptionPeople() {
        WebUI.clickElement(optionPeople);
    }

    public void clickonButtonSubmit() {
        WebUI.clickElement(buttonSubmit);
    }

}
