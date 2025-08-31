package com.anhtester.pages;

import com.anhtester.helpers.SystemHelper;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;

public class ContactsPage extends CommonPage {

    private By buttonNewContact = By.xpath("//a[normalize-space()='New Contact']");
    private By fieldFirstName = By.xpath("//input[@id='firstname']");
    private By fieldLastName = By.xpath("//input[@id='lastname']");
    private By fieldEmail = By.xpath("//input[@id='email']");
    private By fieldPassword = By.xpath("//input[@name='password']");
    private By buttonChooseFile = By.xpath("//input[@type='file']");
    String filePath = SystemHelper.getCurrentDir() + "src/test/resources/testdata/UK.jpg";
    private By buttonSave = By.xpath("//button[normalize-space()='Save']");
    private By alertCreatedContact = By.xpath("//span[@class='alert-title']");
    private By createdContact = By.xpath("//a[normalize-space()='Bin Dang']");
    private By buttonX = By.xpath("(//span[normalize-space()='×'])[1]");

    public void clickButtonNewContact() {
        WebUI.waitForElementVisible(buttonNewContact);
        WebUI.clickElement(buttonNewContact);
    }

    public void addNewContact(String firstName, String lastName) {
        WebUI.setTextElement(buttonChooseFile, filePath);
        WebUI.setTextElement(fieldFirstName, firstName);
        WebUI.setTextElement(fieldLastName, lastName);
        WebUI.setTextElement(fieldEmail, "vbin561995@gmail.com");
        WebUI.setTextElement(fieldPassword, "123456");
        WebUI.clickElement(buttonSave);
    }

    public void verifyCreatedContact(String firstName, String lastName) {
        WebUI.waitForElementVisible(alertCreatedContact);
        WebUI.assertEquals(WebUI.getTextElement(alertCreatedContact), "Contact added successfully.", "Contact creation failed");
        WebUI.assertTrue(WebUI.checkElementExist(createdContact), "New contact not found in the list");
        WebUI.clickElement(createdContact);
        WebUI.assertEquals(WebUI.getAttributeElement(fieldFirstName, "value"), firstName, "First name does not match");
        WebUI.assertEquals(WebUI.getAttributeElement(fieldLastName, "value"), lastName, "Last name does not match");
        WebUI.assertEquals(WebUI.getAttributeElement(fieldEmail, "value"), "vbin561995@gmail.com", "Email does not match");
        WebUI.assertEquals(WebUI.getAttributeElement(fieldPassword, "value"), "", "Password does not match");
        WebUI.clickElement(buttonX);
    }

}
