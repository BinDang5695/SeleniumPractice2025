package test.ui.crmpages;

import org.openqa.selenium.By;
import settings.drivers.DriverManager;
import settings.helpers.AssertHelper;
import settings.keywords.WebUI;
import test.ui.common.BasePage;

import java.util.Set;

public class KnowledgeBasePage extends BasePage {

    private By buttonNewArticle = By.xpath("//a[normalize-space()='New Article']");
    private By inputSubject = By.xpath("//input[@id='subject']");
    private By dropdownGroup = By.xpath("//button[@data-id='articlegroup']");
    private By searchGroup = By.xpath("//input[@aria-label='Search']");
    private By optionJava = By.xpath("//span[normalize-space()='java']");
    private By checkboxInternalArticle = By.xpath("//label[normalize-space()='Internal Article']");
    private By checkboxDisabled = By.xpath("//div[@class='panel-body']//label[@for='disabled'][normalize-space()='Disabled']");
    private By iframeDescription = By.id("description_ifr");
    private By editorBody = By.id("tinymce");
    private By buttonSave = By.xpath("//div[@class='panel-footer text-right']//button[@type='submit'][normalize-space()='Save']");
    private By createdArticle = By.xpath("//tr[@class='has-row-options odd']//a[contains(text(),'Bin Article')]");
    private By buttonView = By.xpath("//a[normalize-space()='View']");
    private By buttonDelete= By.xpath("//a[normalize-space()='Delete']");
    private By nameArticle = By.xpath("//h4[normalize-space()='Bin Article']");
    private By descriptionArticle = By.xpath("//p[normalize-space()='Bin article description']");
    private By buttonYes = By.xpath("//button[normalize-space()='Yes']");
    private By messageNotification = By.xpath("//div[@class='answer_response']");
    private By buttonX = By.xpath("//button[@data-dismiss='alert']//span[@aria-hidden='true'][normalize-space()='×']");

    public void clickButtonNewArticle() {
        WebUI.waitForElementVisible(buttonNewArticle);
        WebUI.clickElement(buttonNewArticle);
    }

    public void enterDescription(String content) {
        WebUI.waitForElementVisible(iframeDescription);
        WebUI.switchToIframe(iframeDescription);
        WebUI.waitForElementVisible(editorBody);
        WebUI.setTextElement(editorBody, content);
        WebUI.switchToDefaultContent();
    }

    public void addNewArticle() {
        WebUI.setTextElement(inputSubject, "Bin Article");
        WebUI.clickElement(dropdownGroup);
        WebUI.setTextElement(searchGroup, "java");
        WebUI.clickElement(optionJava);
        WebUI.clickElement(checkboxInternalArticle);
        WebUI.clickElement(checkboxDisabled);
        enterDescription("Bin article description");
        WebUI.clickElement(buttonSave);
    }

    public void switchBetweenTabTest() {
        String tab1 = DriverManager.getDriver().getWindowHandle();
        WebUI.moveToElement(createdArticle);
        WebUI.clickElement(buttonView);
        Set<String> allTabs = DriverManager.getDriver().getWindowHandles();
        String tab2 = null;

        for (String handle : allTabs) {
            if (!handle.equals(tab1)) {
                tab2 = handle;
                break;
            }
        }

        if (tab2 != null) {
            DriverManager.getDriver().switchTo().window(tab2);

            AssertHelper.assertEquals(WebUI.getTextElement(nameArticle), "Bin Article", "The project progress not match.");
            AssertHelper.assertEquals(WebUI.getTextElement(descriptionArticle), "Bin article description", "The description Article does not match.");
            WebUI.clickElement(buttonYes);
            AssertHelper.assertEquals(WebUI.getTextElement(messageNotification), "Thanks for your feedback", "The 1st message Notification does not match.");
            WebUI.clickElement(buttonYes);
            WebUI.waitForElementVisible(messageNotification);
            AssertHelper.assertEquals(WebUI.getTextElement(messageNotification), "You can vote once in 24 hours", "The 2nd message Notification does not match.");
        }

        DriverManager.getDriver().switchTo().window(tab1);

    }

    public void deleteCreatedArticle() {
        WebUI.moveToElement(createdArticle);
        WebUI.clickElement(buttonDelete);
        WebUI.acceptAlert();
        WebUI.clickElement(buttonX);
    }





}
