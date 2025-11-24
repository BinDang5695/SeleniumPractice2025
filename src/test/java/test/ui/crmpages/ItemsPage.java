package test.ui.crmpages;

import org.openqa.selenium.By;
import settings.helpers.AssertHelper;
import settings.helpers.SystemHelper;
import settings.keywords.WebUI;
import test.ui.common.BasePage;

public class ItemsPage extends BasePage {

    private By buttonImportItems = By.xpath("//a[normalize-space()='Import Items']");
    private By buttonChooseFile = By.xpath("//input[@id='file_csv']");
    private By buttonImport = By.xpath("//button[normalize-space()='Import']");
    String filePath = SystemHelper.getCurrentDir() + "src\\test\\resources\\testdata\\BinItems.csv";
    private By inputSearchItems = By.xpath("//input[@aria-controls='DataTables_Table_1']");
    private By tableDescription = By.xpath("//a[normalize-space()='Bin Description']");
    private By tableLongDescription = By.xpath("//td[normalize-space()='Bin Long description']");
    private By tableRate = By.xpath("//td[normalize-space()='$25.00']");
    private By tableTax1 = By.xpath("//td[5]//span[normalize-space()='0.00%']");
    private By tableTax2 = By.xpath("//td[6]//span[normalize-space()='0.00%']");
    private By tableUnit = By.xpath("//td[normalize-space()='Bin Unit']");
    private By buttonDelete = By.xpath("//a[@class='text-danger _delete']");
    private By buttonX = By.xpath("//button[@data-dismiss='alert']//span[@aria-hidden='true'][normalize-space()='×']");

    public void clickButtonImportItems() {
        WebUI.waitForElementVisible(buttonImportItems);
        WebUI.clickElement(buttonImportItems);
    }

    public void importCSVFile() {
        WebUI.sendKeysUploadFile(buttonChooseFile, filePath);
    }

    public void clickToImportCSVFile() {
        WebUI.waitForElementVisible(buttonImport);
        WebUI.clickElement(buttonImport);
    }

    public void searchAndVerifyItems() {
        WebUI.setTextElement(inputSearchItems, "Bin Long Description");
        AssertHelper.assertEquals(WebUI.getTextElement(tableDescription), "Bin Description", "The Description does not match.");
        AssertHelper.assertEquals(WebUI.getTextElement(tableLongDescription), "Bin Long description", "The Long description does not match.");
        AssertHelper.assertEquals(WebUI.getTextElement(tableRate), "$25.00", "The Rate does not match.");
        AssertHelper.assertEquals(WebUI.getTextElement(tableTax1), "0.00%", "The Tax 1 does not match.");
        AssertHelper.assertEquals(WebUI.getTextElement(tableTax2), "0.00%", "The Tax 2 does not match.");
        AssertHelper.assertEquals(WebUI.getTextElement(tableUnit), "Bin Unit", "The Unit does not match.");
    }

    public void deleteImportedItem() {
        WebUI.moveToElement(tableDescription);
        WebUI.clickElement(buttonDelete);
        WebUI.acceptAlert();
        WebUI.clickElement(buttonX);
    }
}
