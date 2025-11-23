package test.ui.crmpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import settings.drivers.DriverManager;
import settings.helpers.AssertHelper;
import settings.helpers.TableHelper;
import settings.keywords.WebUI;
import settings.utils.LogUtils;
import test.ui.common.BasePage;

import java.time.Duration;
import java.util.List;

public class LeadsPage extends BasePage {

    private By buttonNewLead = By.xpath("//a[normalize-space()='New Lead']");
    private By dropdownStatus = By.xpath("//button[@data-id='status']");
    private By inputStatus = By.xpath("//input[@aria-controls='bs-select-5']");
    private By optionActive = By.xpath("//span[@class='text'][normalize-space()='Active']");
    private By dropdownSource = By.xpath("//button[@data-id='source']");
    private By inputSource = By.xpath("//input[@aria-controls='bs-select-6']");
    private By optionFacebook = By.xpath("//span[normalize-space()='Facebook']");
    private By dropdownTags = By.xpath("//div[@id='inputTagsWrapper']//input[@placeholder='Tag']");
    private By optionSelenium = By.xpath("//li[contains(@class,'menu')]//div[normalize-space()='Selenium']");
    private By inputName = By.xpath("//div[@class='col-md-6']//input[@id='name']");
    private By inputAddress = By.xpath("//textarea[@id='address']");
    private By inputPosition = By.xpath("//input[@id='title']");
    private By inputCity = By.xpath("//input[@id='city']");
    private By inputEmailAddress = By.xpath("//input[@id='email']");
    private By inputState = By.xpath("//input[@id='state']");
    private By inputWebsite = By.xpath("//input[@id='website']");
    private By dropdownCountry = By.xpath("//button[@data-id='country']");
    private By inputCountry = By.xpath("//input[@aria-controls='bs-select-8']");
    private By optionVietNam = By.xpath("//span[normalize-space()='Vietnam']");
    private By inputPhone = By.xpath("//input[@id='phonenumber']");
    private By inputZipCode = By.xpath("//input[@id='zip']");
    private By inputLeadValue = By.xpath("//input[@name='lead_value']");
    private By dropdownDefaultLanguage = By.xpath("//div[contains(text(),'System Default')]");
    private By inputDefaultLanguage = By.xpath("//input[@aria-controls='bs-select-9']");
    private By optionVietNamese = By.xpath("//span[normalize-space()='Vietnamese']");
    private By inputCompany = By.xpath("//input[@id='company']");
    private By inputDescription = By.xpath("//textarea[@id='description']");
    private By checkboxContactedToday = By.xpath("//label[normalize-space()='Contacted Today']");
    private By inputDateContacted = By.xpath("//input[@id='custom_contact_date']");
    private By buttonSave = By.xpath("//button[@id='lead-form-submit']");
    private By dropdownPagination = By.xpath("//select[@name='leads_length']");
    private By value10 = By.xpath("//option[@value='10']");
    private By value25 = By.xpath("//option[@value='25']");
    private By inputSearchLead = By.xpath("//input[@aria-controls='leads']");
    private By contentLeads_info1To10 = By.xpath("//div[@id='leads_info' and contains(., 'Showing 1 to 10 of 11 entries')]");
    private By contentLeads_info11To11 = By.xpath("//div[@id='leads_info' and contains(., 'Showing 11 to 11 of 11 entries')]");
    private By page1 = By.xpath("//a[normalize-space()='1']");
    private By page2 = By.xpath("//a[normalize-space()='2']");
    private By checkboxSelectAllLead = By.xpath("//div[@class='checkbox mass_select_all_wrap']");
    private By allBinLeadcheckbox = By.xpath("//div[@class='checkbox']");
    private By buttonBulkActions = By.xpath("//span[normalize-space()='Bulk Actions']");
    private By checkboxMassDelete = By.xpath("//label[normalize-space()='Mass Delete']");
    private By buttonConfirm = By.xpath("//a[normalize-space()='Confirm']");
    private By alertSuccess = By.xpath("//span[@class='alert-title']");
    private By noDataAfterDelete = By.xpath("//td[@class='dataTables_empty']");
    private By buttonX = By.xpath("//button[@data-dismiss='alert']//span[@aria-hidden='true'][normalize-space()='×']");

    public void clickButtonNewLead() {
        WebUI.waitForElementVisible(buttonNewLead);
        WebUI.clickElement(buttonNewLead);
    }

    public void addNewLead(String leadName, String emailAddress) {
        WebUI.clickElement(dropdownStatus);
        WebUI.setTextElement(inputStatus, "Active");
        WebUI.clickElement(optionActive);
        WebUI.clickElement(dropdownSource);
        WebUI.setTextElement(inputSource, "Facebook");
        WebUI.clickElement(optionFacebook);
        WebUI.clickElement(dropdownTags);
        WebUI.setTextElement(dropdownTags, "Selenium");
        WebUI.clickElement(optionSelenium);
        WebUI.setTextElement(inputName, leadName);
        WebUI.setTextElement(inputAddress, "36 Tran Quoc Toan");
        WebUI.setTextElement(inputPosition, "QC");
        WebUI.setTextElement(inputCity, "Da Nang");
        WebUI.setTextElement(inputEmailAddress, emailAddress);
        WebUI.setTextElement(inputState, "An Hai");
        WebUI.setTextElement(inputWebsite, "https://www.nashtechglobal.com/");
        WebUI.clickElement(dropdownCountry);
        WebUI.setTextElement(inputCountry, "Vietnam");
        WebUI.clickElement(optionVietNam);
        WebUI.setTextElement(inputPhone, "0123456789");
        WebUI.setTextElement(inputZipCode, "50000");
        WebUI.setTextElement(inputLeadValue, "1000");
        WebUI.clickElement(dropdownDefaultLanguage);
        WebUI.setTextElement(inputDefaultLanguage, "Vietnamese");
        WebUI.clickElement(optionVietNamese);
        WebUI.setTextElement(inputCompany, "Nashtech");
        WebUI.setTextElement(inputDescription, "Add description");
        WebUI.clickElement(checkboxContactedToday);
        WebUI.setTextElement(inputDateContacted, "19-11-2026 11:17");
        WebUI.clickElement(buttonSave);
        WebUI.waitForPageRefresh(buttonNewLead);
    }

    public void createMultipleLeads(int totalLead) {
        for (int i = 1; i <= totalLead; i++) {
            String leadName = "Bin Lead " + i;
            String emailAddress = "bin" + i + ".dangvan@nashtechglobal.com";
            LogUtils.info("Creating Lead: " + leadName);
            basePage().clickMenuLeads();
            clickButtonNewLead();
            addNewLead(leadName, emailAddress);
        }
    }

    public void searchAndCheckDataInTable(int column, String data, String columnName) {
        WebUI.clickElement(dropdownPagination);
        WebUI.clickElement(value10);
        WebUI.setTextElement(inputSearchLead, "Bin Lead");
        WebUI.waitForElementVisible(contentLeads_info1To10);
        TableHelper.checkDataInTableByColumn_Contains(column, data, columnName);
        WebUI.clickElement(page2);
        WebUI.waitForElementVisible(contentLeads_info11To11);
        TableHelper.checkDataInTableByColumn_Contains(column, data, columnName);
    }

    public boolean verifyAllCheckboxIsSelected() {
        List<WebElement> elements = DriverManager.getDriver().findElements(allBinLeadcheckbox);
        int total = elements.size();

        for (int i = 1; i <= total; i++) {
            By indexedCheckbox = By.xpath("(//div[@class='checkbox'])[" + i + "]/input");
            WebElement cb = DriverManager.getDriver().findElement(indexedCheckbox);

            if (!cb.isSelected()) {
                Assert.fail("❌ Checkbox at index " + i + " does not tick!");
            }
        }

        LogUtils.info("✅ All the checkbox ticked.");
        return true;
    }

    public void waitUntilCheckboxSelected(By indexedCheckbox) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(3));

        wait.until(driver -> {
            WebElement cb = driver.findElement(indexedCheckbox);
            return cb.isSelected();
        });
    }

    public void clickSelectAllAndEnsureChecked() {
        int retry = 0;
        int maxRetry = 3;

        while (retry < maxRetry) {

            WebUI.waitForElementVisible(checkboxSelectAllLead);
            WebUI.clickElement(checkboxSelectAllLead);

            List<WebElement> elements = DriverManager.getDriver().findElements(allBinLeadcheckbox);
            int total = elements.size();

            for (int i = 1; i <= total; i++) {
                By indexedCheckbox = By.xpath("(//div[@class='checkbox'])[" + i + "]/input");

                try {
                    waitUntilCheckboxSelected(indexedCheckbox);
                } catch (Exception e) {
                    LogUtils.warn("⏳ Checkbox " + i + " does not tick, retry...");
                }
            }

            if (verifyAllCheckboxIsSelected()) {
                LogUtils.info("✅ All checkbox ticked after retry number: " + retry);
                return;
            }

            LogUtils.warn("⚠️ Not ticked all yet, retry number: " + (retry + 1));
            retry++;
        }
        Assert.fail("❌ After clicked Select All many times but still has checkbox does not tick yet.");
    }


    public void deleteDataAfterSearched() {
        WebUI.clickElement(page1);
        WebUI.clickElement(value25);
        clickSelectAllAndEnsureChecked();
        WebUI.clickElement(buttonBulkActions);
        WebUI.clickElement(checkboxMassDelete);
        WebUI.clickElement(buttonConfirm);
        WebUI.acceptAlert();
    }

    public void verifyDeletedLeads() {
        AssertHelper.assertEquals(WebUI.getTextElement(alertSuccess), "Total leads deleted: 11", "The alert Success does not match.");
        WebUI.clickElement(buttonX);
        WebUI.setTextElement(inputSearchLead, "Bin Lead");
        AssertHelper.assertEquals(WebUI.getTextElement(noDataAfterDelete), "No matching records found", "The data still shown");
    }
}
