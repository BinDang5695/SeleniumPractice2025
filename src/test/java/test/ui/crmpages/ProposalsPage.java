package test.ui.crmpages;

import org.openqa.selenium.By;
import settings.drivers.DriverManager;
import settings.helpers.AssertHelper;
import settings.helpers.FileHelper;
import settings.keywords.WebUI;
import settings.utils.LogUtils;
import test.ui.common.BasePage;

import java.io.File;

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
    private By inputSearchProposals = By.xpath("//input[@aria-controls='proposals']");
    private By contentProposals_info = By.xpath("//div[@id='proposals_info' and contains(., 'Showing 1 to 1 of 1 entries')]");
    private By dropdownMore = By.xpath("//button[normalize-space()='More']");
    private By optionDelete = By.xpath("//a[normalize-space()='Delete']");
    private By buttonX = By.xpath("//button[@data-dismiss='alert']//span[@aria-hidden='true'][normalize-space()='×']");
    private By buttonExport = By.xpath("//span[normalize-space()='Export']");
    private By optionPDF = By.xpath("//a[normalize-space()='PDF']");
    private By tableBinSubject = By.xpath("//tr[@class='has-row-options odd']//a[contains(text(),'Bin Subject')]");
    private By buttonEdit = By.xpath("//div[@class='row-options']//a[contains(text(),'Edit')]");

    //Compare file PDF with data on UI table
    private By tableProposal = By.xpath("//tr[1]//td[1]//a[contains(@href,'list_proposals')]");
    private By tableSubject = By.xpath("//tr[1]//td[2]//a[normalize-space()='Bin Subject']");
    private By tableTo = By.xpath("//a[contains(text(),'Bin Customer')]");
    private By tableTotal = By.xpath("//td[normalize-space()='$1,000.00']");
    private By tableDate = By.xpath("//input[@value='21-11-2026']");
    private By tableOpenTill = By.xpath("//input[@value='28-11-2026']");
    private By tableStatus = By.xpath("//tr[1]//td[5]//span[normalize-space()='Draft']");
    String uiProposalNumber;
    String uiSubject;
    String uiTo;
    String uiTotal;
    String uiDate;
    String uiOpenTill;
    String uiProject;
    String uiTags;
    String uiStatus;
    private String normalizeText(String text) {
        if (text == null) return "";
        return text
                .replaceAll("\\s+", " ")
                .replaceAll("[\\u00A0]", " ")
                .trim();
    }

    public void captureUITableData() {
        WebUI.waitForElementVisible(contentProposals_info);
        uiProposalNumber = WebUI.getTextElement(tableProposal);
        uiSubject = WebUI.getTextElement(tableSubject);
        uiTo = WebUI.getTextElement(tableTo);
        uiTotal = WebUI.getTextElement(tableTotal);
        uiStatus = WebUI.getTextElement(tableStatus);
        WebUI.moveToElement(tableSubject);
        WebUI.waitForElementVisible(buttonEdit);
        WebUI.clickElement(buttonEdit);
        uiDate = WebUI.getAttributeElement(tableDate, "value");
        uiOpenTill = WebUI.getAttributeElement(tableOpenTill, "value");
        uiProject = "";
        uiTags = "";
        WebUI.clickElement(buttonSaveAddNewProposal);
    }

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
        WebUI.waitForElementVisible(buttonX);
        WebUI.clickElement(buttonX);
    }

    public void verifyTooltip() {
        WebUI.moveToElement(iconToggleFullView);
        AssertHelper.assertEquals(WebUI.getTextElement(tooltipContent), "Toggle full view", "The content Toggle does not match.");
    }

    public void searchCreatedProposal() {
        WebUI.setTextElement(inputSearchProposals, "Bin Subject");
        WebUI.waitForElementVisible(contentProposals_info);
    }

    public void exportPDFFile() {
        WebUI.setTextElement(inputSearchProposals, "Bin Subject");
        WebUI.waitForElementVisible(contentProposals_info);
        WebUI.waitForElementVisible(buttonExport);
        WebUI.clickElement(buttonExport);
        WebUI.waitForElementVisible(optionPDF);
        WebUI.clickElement(optionPDF);
    }

    public void verifyDownloadPDFFile(String expectedFileName) {

        String fileName = (expectedFileName != null && !expectedFileName.isEmpty())
                ? expectedFileName : "Proposals.pdf";

        String fullPath = DriverManager.getDownloadPath() + File.separator + fileName;

        LogUtils.info("🔍 Waiting for downloaded PDF file: " + fileName);

        FileHelper.waitForFileExists(fullPath, 10);

        String pdfText = FileHelper.readPdfText(fullPath);

        LogUtils.info("📄 PDF Content (raw):\n" + pdfText);

        String pdfNorm = normalizeText(pdfText);
        String uiProposalNorm = normalizeText(uiProposalNumber);
        String uiSubjectNorm = normalizeText(uiSubject);
        String uiToNorm = normalizeText(uiTo);
        String uiTotalNorm = normalizeText(uiTotal);
        String uiDateNorm = normalizeText(uiDate);
        String uiOpenTillNorm = normalizeText(uiOpenTill);
        String uiProjectNorm = normalizeText(uiProject);
        String uiTagsNorm = normalizeText(uiTags);
        String uiStatusNorm = normalizeText(uiStatus);

        //LogUtils.info("📄 Normalized PDF Content: " + pdfNorm);
        LogUtils.info("🔢 UI Data to verify: Proposal#: " + uiProposalNorm
                + ", Subject: " + uiSubjectNorm
                + ", To: " + uiToNorm
                + ", Total: " + uiTotalNorm
                + ", Date: " + uiDateNorm
                + ", Open Till: " + uiOpenTillNorm
                + ", Project: " + uiProjectNorm
                + ", Tags: " + uiTagsNorm
                + ", Status: " + uiStatusNorm);

        AssertHelper.assertTrue(pdfNorm.contains(uiProposalNorm), "❌ Proposal # not found in PDF");
        AssertHelper.assertTrue(pdfNorm.contains(uiSubjectNorm), "❌ Subject not found in PDF");
        AssertHelper.assertTrue(pdfNorm.contains(uiToNorm), "❌ To not found in PDF");
        AssertHelper.assertTrue(pdfNorm.contains(uiTotalNorm), "❌ Total amount missing");
        AssertHelper.assertTrue(pdfNorm.contains(uiDateNorm), "❌ Date missing");
        AssertHelper.assertTrue(pdfNorm.contains(uiOpenTillNorm), "❌ Open Till missing");
        AssertHelper.assertTrue(pdfNorm.contains("Project"), "❌ Project not empty in PDF");
        AssertHelper.assertTrue(pdfNorm.contains("Tags"), "❌ Tags not empty in PDF");
        AssertHelper.assertTrue(pdfNorm.contains(uiStatusNorm), "❌ Status missing");

        LogUtils.info("✅ PDF content verified!");

        FileHelper.deleteFile(fullPath);
    }

    public void deleteCreatedProposal() {
        WebUI.openURL("https://crm.anhtester.com/admin/proposals#1");
        WebUI.clickElement(tableBinSubject);
        WebUI.waitForElementVisible(dropdownMore);
        WebUI.clickElement(dropdownMore);
        WebUI.clickElement(optionDelete);
        WebUI.acceptAlert();
        WebUI.clickElement(buttonX);
    }


}
