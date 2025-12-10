package test.ui.crmpages;

import org.openqa.selenium.By;
import settings.helpers.AssertHelper;
import settings.helpers.SystemHelper;
import settings.keywords.WebUI;
import test.ui.common.BasePage;

public class ContractsPage extends BasePage {

    private By buttonNewContract = By.xpath("//a[normalize-space()='New Contract']");
    private By inputCustomer = By.xpath("//button[@data-id='clientid']");
    private By searchCustomer = By.xpath("//input[@placeholder='Type to search...']");
    private By selectCustomer = By.xpath("//span[normalize-space()='Bin Customer']");
    private By inputSubject = By.xpath("//input[@id='subject']");
    private By inputContractValue = By.xpath("//input[@name='contract_value']");
    private By inputContractType = By.xpath("//button[@data-id='contract_type']");
    private By inputValueForContractType = By.xpath("//input[@aria-controls='bs-select-1']");
    private By selectContractType = By.xpath("//span[normalize-space()='1']");
    private By inputStartDate = By.xpath("//input[@id='datestart']");
    private By inputEndDate = By.xpath("//input[@id='dateend']");
    private By inputDescription = By.xpath("//textarea[@id='description']");
    private By buttonSave = By.xpath("//div[contains(@class,'btn')]//button[@type='submit']");
    private By selectedCustomer = By.xpath("//div[contains(text(),'Bin Customer')]");
    private By selectedContractType = By.xpath("//div[contains(text(),'1')]");
    private By inputedDescription = By.xpath("//form[@id='contract-form']//textarea[@id='description']");
    private By alertSuccess = By.xpath("//span[@class='alert-title']");
    private By dropdownMore = By.xpath("//button[normalize-space()='More']");
    private By buttonDelete = By.xpath("//a[normalize-space()='Delete']");
    private By searchContract = By.xpath("//input[@aria-controls='contracts']");
    private By noDataAfterDelete = By.xpath("//td[@class='dataTables_empty']");
    private By buttonX = By.xpath("//button[@data-dismiss='alert']//span[@aria-hidden='true'][normalize-space()='×']");

    public void clickButtonNewContract() {
        WebUI.waitForElementVisible(buttonNewContract);
        WebUI.clickElement(buttonNewContract);
    }

    public void addNewContract() {
        WebUI.clickElement(inputCustomer);
        WebUI.setTextElement(searchCustomer, "Bin Customer");
        WebUI.clickElement(selectCustomer);
        WebUI.setTextElement(inputSubject, "Bin Subject");
        WebUI.setTextElement(inputContractValue, "1000");
        WebUI.clickElement(inputContractType);
        WebUI.setTextElement(inputValueForContractType, "1");
        WebUI.clickElement(selectContractType);
        WebUI.setTextElement(inputStartDate, "18-11-2026");
        WebUI.setTextElement(inputEndDate, "18-11-2026");
        WebUI.setTextElement(inputDescription, "Bin Description");
        WebUI.clickElement(buttonSave);
    }

    public void verifyCreatedContract() {
        AssertHelper.assertEquals(WebUI.getTextElement(alertSuccess), "Contract added successfully.", "Alert Success after created does not match");
        AssertHelper.assertEquals(WebUI.getTextElement(selectedCustomer), "Bin Customer", "Customer Name does not match");
        AssertHelper.assertEquals(WebUI.getAttributeElement(inputSubject, "value"), "Bin Subject", "Subject does not match");
        AssertHelper.assertEquals(WebUI.getAttributeElement(inputContractValue, "value"), "1000.00", "Contract Value does not match");
        AssertHelper.assertEquals(WebUI.getTextElement(selectedContractType), "1", "Selected Contract Type does not match");
        AssertHelper.assertEquals(WebUI.getAttributeElement(inputStartDate, "value"), "18-11-2026", "Start Date does not match");
        AssertHelper.assertEquals(WebUI.getAttributeElement(inputEndDate, "value"), "18-11-2026", "End Date does not match");
        AssertHelper.assertEquals(WebUI.getAttributeElement(inputedDescription, "value"), "Bin Description", "Inputed Description after created does not match");

    }

    public void updateContract() {
        WebUI.setTextElement(inputSubject, "Bin Subject Updated");
        WebUI.setTextElement(inputContractValue, "2000");
        WebUI.setTextElement(inputStartDate, "18-11-2027");
        WebUI.setTextElement(inputEndDate, "18-11-2027");
        WebUI.setTextElement(inputDescription, "Bin Description Updated");
        WebUI.clickElement(buttonSave);
    }

    public void verifyUpdatedContract() {
        AssertHelper.assertEquals(WebUI.getTextElement(alertSuccess), "Contract updated successfully.", "Alert Success after updated does not match");
        AssertHelper.assertEquals(WebUI.getTextElement(selectedCustomer), "Bin Customer", "Customer Name does not match");
        AssertHelper.assertEquals(WebUI.getAttributeElement(inputSubject, "value"), "Bin Subject Updated", "Subject does not match");
        AssertHelper.assertEquals(WebUI.getAttributeElement(inputContractValue, "value"), "2000.00", "Contract Value does not match");
        AssertHelper.assertEquals(WebUI.getTextElement(selectedContractType), "1", "Selected Contract Type does not match");
        AssertHelper.assertEquals(WebUI.getAttributeElement(inputStartDate, "value"), "18-11-2027", "Start Date does not match");
        AssertHelper.assertEquals(WebUI.getAttributeElement(inputEndDate, "value"), "18-11-2027", "End Date does not match");
        AssertHelper.assertEquals(WebUI.getAttributeElement(inputedDescription, "value"), "Bin Description Updated", "Inputed Description after updated does not match");
    }

    public void deleteContract() {
        WebUI.clickElement(dropdownMore);
        WebUI.clickElement(buttonDelete);
        WebUI.acceptAlert();
        WebUI.clickElement(buttonX);
    }

    public void verifyDeletedContract() {
        WebUI.setTextElement(searchContract, "Bin Subject");
        WebUI.scrollToCenterElement(noDataAfterDelete);
        AssertHelper.assertEquals(WebUI.getTextElement(noDataAfterDelete), "No entries found", "Deleted Contract still shown");
    }

}
