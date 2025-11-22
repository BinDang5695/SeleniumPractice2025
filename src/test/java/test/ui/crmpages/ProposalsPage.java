package test.ui.crmpages;

import org.openqa.selenium.By;
import settings.helpers.AssertHelper;
import settings.keywords.WebUI;
import test.ui.common.BasePage;

public class ProposalsPage extends BasePage {

    private By buttonNewProposals = By.xpath("//a[normalize-space()='New Proposal']");
    private By inputSubject = By.xpath("//input[@id='subject']");
    private By dropdownRelated = By.xpath("//button[@data-id='rel_type']");
    private By optionCustomer = By.xpath("//a[@role='option']//span[normalize-space()='Customer']");
    private By dropdownCustomer = By.xpath("//div[contains(text(),'Select and begin typing')]");
    private By inputCustomer = By.xpath("//input[@placeholder='Type to search...']");
    private By option1stBinCustomer = By.xpath("(//span[normalize-space()='Bin Customer'])[1]");
    private By calendarDate = By.xpath("//div[@app-field-wrapper='date']//i[contains(@class,'calendar')]");
    private By year2025 = By.xpath("//span[normalize-space()='2025']");
    private By year2026 = By.xpath("//div[normalize-space()='2026']");
    private By date21 = By.xpath("//div[normalize-space()='21']");
    private By toogleAllowComments = By.xpath("//label[@for='allow_comments']");
    private By inputTo = By.xpath("//input[@id='proposal_to']");
    private By inputEmail = By.xpath("//input[@id='email']");
    private By buttonAdd = By.xpath("//div[@class='input-group-btn']");
    private By inputDescription = By.xpath("//input[@id='description']");
    private By inputRate = By.xpath("//input[@id='rate']");
    private By buttonSaveAddNewItem = By.xpath("//button[@type='submit']");
    private By radioHours = By.xpath("//label[normalize-space()='Hours']");
    private By buttonSelect = By.xpath("//button[@class='btn pull-right btn-primary']");
    private By buttonSaveAddNewProposal = By.xpath("//button[@type='button'][normalize-space()='Save']");
    private By iconToggleFullView = By.xpath("//li[@data-title='Toggle full view']");
    private By tooltipContent = By.cssSelector(".tooltip-inner");
    private By dropdownMore = By.xpath("//button[normalize-space()='More']");
    private By optionDelete = By.xpath("//a[normalize-space()='Delete']");
    private By buttonX = By.xpath("//button[@data-dismiss='alert']//span[@aria-hidden='true'][normalize-space()='×']");

    public void clickButtonNewProposal() {
        WebUI.waitForElementVisible(buttonNewProposals);
        WebUI.clickElement(buttonNewProposals);
    }

    public void addNewProposal() {
        WebUI.setTextElement(inputSubject, "Bin Subject");
        WebUI.clickElement(dropdownRelated);
        WebUI.clickElement(optionCustomer);
        WebUI.clickElement(dropdownCustomer);
        WebUI.setTextElement(inputCustomer, "Bin Customer");
        WebUI.clickElement(option1stBinCustomer);
        WebUI.clickElement(calendarDate);
        WebUI.clickElement(year2025);
        WebUI.waitForElementVisible(year2026);
        WebUI.clickElement(year2026);
        WebUI.clickElement(date21);
        WebUI.clickElement(toogleAllowComments);
        WebUI.setTextElement(inputEmail, "vbin@gmail.com");
        WebUI.clickElement(buttonAdd);
        WebUI.setTextElement(inputDescription, "Bin description");
        WebUI.setTextElement(inputRate, "1000");
        WebUI.clickElement(buttonSaveAddNewItem);
        WebUI.clickElement(radioHours);
        WebUI.scrollToBottom();
        WebUI.waitForElementVisible(buttonSelect);
        WebUI.clickElement(buttonSelect);
        WebUI.waitForElementVisible(buttonSaveAddNewProposal);
        WebUI.clickElement(buttonSaveAddNewProposal);
    }

    public void verifyTooltip() {
        WebUI.moveToElement(iconToggleFullView);
        AssertHelper.assertEquals(WebUI.getTextElement(tooltipContent), "Toggle full view", "The content Toggle does not match.");
    }

    public void deleteCreatedProposal() {
        WebUI.clickElement(dropdownMore);
        WebUI.clickElement(optionDelete);
        WebUI.acceptAlert();
        WebUI.clickElement(buttonX);
    }

}
