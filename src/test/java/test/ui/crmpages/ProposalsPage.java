package test.ui.crmpages;

import org.openqa.selenium.By;
import settings.drivers.DriverManager;
import settings.helpers.AssertHelper;
import settings.helpers.ExcelHelper;
import settings.helpers.FileHelper;
import settings.keywords.WebUI;
import settings.utils.LogUtils;
import test.ui.common.BasePage;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


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
    private By buttonToogleTableRight = By.xpath("//i[@class='fa fa-angle-double-right']");
    private By inputSearchProposals = By.xpath("//input[@aria-controls='proposals']");
    private By contentProposals_info = By.xpath("//div[@id='proposals_info' and contains(., 'Showing 1 to 1 of 1 entries')]");
    private By dropdownMore = By.xpath("//button[normalize-space()='More']");
    private By optionDelete = By.xpath("//a[normalize-space()='Delete']");
    private By buttonX = By.xpath("//button[@data-dismiss='alert']//span[@aria-hidden='true'][normalize-space()='×']");
    private By buttonExport = By.xpath("//span[normalize-space()='Export']");
    private By optionPDF = By.xpath("//a[normalize-space()='PDF']");
    private By optionExcel = By.xpath("//a[normalize-space()='Excel']");
    private By optionCSV = By.xpath("//a[normalize-space()='CSV']");
    private By tableBinSubject = By.xpath("//tr[@class='has-row-options odd']//a[contains(text(),'Bin Subject')]");

    //Compare file PDF with data on UI table
    private By tableProposal = By.xpath("//tr[1]//td[1]//a[contains(@href,'list_proposals')]");
    private By tableSubject = By.xpath("//tr[1]//td[2]//a[normalize-space()='Bin Subject']");
    private By tableTo = By.xpath("//a[contains(text(),'Bin Customer')]");
    private By tableTotal = By.xpath("//td[normalize-space()='$1,000.00']");
    private By tableDate = By.xpath("//td[normalize-space()='21-11-2026']");
    private By tableOpenTill = By.xpath("//td[normalize-space()='28-11-2026']");
    private By tableCreated = By.xpath("//td[@class='sorting_1']");
    private By tableStatus = By.xpath("//td//span[contains(@class,'proposal-status')]");
    String uiProposalNumber;
    String uiSubject;
    String uiTo;
    String uiTotal;
    String uiDate;
    String uiOpenTill;
    String uiCreated;
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
        uiDate = WebUI.getTextElement(tableDate);
        uiOpenTill = WebUI.getTextElement(tableOpenTill);
        uiProject = "";
        uiTags = "";
        uiCreated = WebUI.getTextElement(tableCreated);
        uiStatus = WebUI.getTextElement(tableStatus);
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
        WebUI.waitForElementVisible(tooltipContent);
        AssertHelper.assertEquals(WebUI.getTextElement(tooltipContent), "Toggle full view", "The content Toggle does not match.");
    }

    public void searchCreatedProposal() {
        WebUI.clickElement(buttonToogleTableRight);
        WebUI.setTextElement(inputSearchProposals, "Bin Subject");
        WebUI.waitForElementVisible(contentProposals_info);
    }

    public void exportPDFFile() {
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
        String uiCreatedNorm = normalizeText(uiCreated);
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
                + ", Date Created: " + uiCreatedNorm
                + ", Status: " + uiStatusNorm);

        AssertHelper.assertTrue(pdfNorm.contains(uiProposalNorm), "❌ Proposal # not found in PDF");
        AssertHelper.assertTrue(pdfNorm.contains(uiSubjectNorm), "❌ Subject not found in PDF");
        AssertHelper.assertTrue(pdfNorm.contains(uiToNorm), "❌ To not found in PDF");
        AssertHelper.assertTrue(pdfNorm.contains(uiTotalNorm), "❌ Total amount missing");
        AssertHelper.assertTrue(pdfNorm.contains(uiDateNorm), "❌ Date missing");
        AssertHelper.assertTrue(pdfNorm.contains(uiOpenTillNorm), "❌ Open Till missing");
        AssertHelper.assertTrue(pdfNorm.contains("Project"), "❌ Project not empty in PDF");
        AssertHelper.assertTrue(pdfNorm.contains("Tags"), "❌ Tags not empty in PDF");
        AssertHelper.assertTrue(pdfNorm.contains(uiCreatedNorm), "❌ Date Created missing");
        AssertHelper.assertTrue(pdfNorm.contains(uiStatusNorm), "❌ Status missing");

        LogUtils.info("✅ PDF content verified!");

        FileHelper.deleteFile(fullPath);
    }

    public void deleteCreatedProposal() {
        WebUI.waitForPageRefresh(tableBinSubject);
        WebUI.clickElement(tableBinSubject);
        WebUI.waitForElementVisible(dropdownMore);
        WebUI.clickElement(dropdownMore);
        WebUI.clickElement(optionDelete);
        WebUI.acceptAlert();
        WebUI.clickElement(buttonX);
    }

    public void exportExcelFile() {
        WebUI.waitForElementVisible(buttonExport);
        WebUI.clickElement(buttonExport);
        WebUI.waitForElementVisible(optionExcel);
        WebUI.clickElement(optionExcel);
    }

    public void exportCSVFile() {
        WebUI.waitForElementVisible(buttonExport);
        WebUI.clickElement(buttonExport);
        WebUI.waitForElementVisible(optionCSV);
        WebUI.clickElement(optionCSV);
    }

    public void verifyDownloadExcelFile(String expectedFileName) {

        String fileName = (expectedFileName != null && !expectedFileName.isEmpty())
                ? expectedFileName : "Proposals.xlsx";

        String fullPath = DriverManager.getDownloadPath() + File.separator + fileName;

        LogUtils.info("🔍 Waiting for downloaded Excel file: " + fileName);

        FileHelper.waitForFileExists(fullPath, 10);

        String excelText = ExcelHelper.readExcelAsText(fullPath);
        LogUtils.info("📘 Excel Content (raw):\n" + excelText);

        String excelNorm = normalizeText(excelText);

        String uiProposalNorm = normalizeText(uiProposalNumber);
        String uiSubjectNorm = normalizeText(uiSubject);
        String uiToNorm = normalizeText(uiTo);
        //String uiTotalNorm = normalizeText(uiTotal);
        String uiDateNorm = normalizeText(uiDate);
        String uiOpenTillNorm = normalizeText(uiOpenTill);
        String uiProjectNorm = normalizeText(uiProject);
        String uiTagsNorm = normalizeText(uiTags);
        String uiCreatedNorm = normalizeText(uiCreated);
        String uiStatusNorm = normalizeText(uiStatus);

        LogUtils.info("🔢 UI Data to verify inside Excel: " +
                "\nProposal#: " + uiProposalNorm +
                "\nSubject: " + uiSubjectNorm +
                "\nTo: " + uiToNorm +
                //"\nTotal: " + uiTotalNorm +
                "\nDate: " + uiDateNorm +
                "\nOpen Till: " + uiOpenTillNorm +
                "\nProject: " + uiProjectNorm +
                "\nTags: " + uiTagsNorm +
                "\nDate Created: " + uiCreatedNorm +
                "\nStatus: " + uiStatusNorm
        );

        AssertHelper.assertTrue(excelNorm.contains(uiProposalNorm), "❌ Proposal # not found");
        AssertHelper.assertTrue(excelNorm.contains(uiSubjectNorm), "❌ Subject not found");
        AssertHelper.assertTrue(excelNorm.contains(uiToNorm), "❌ To not found");
        //AssertHelper.assertTrue(excelNorm.contains(uiTotalNorm), "❌ Total missing");
        AssertHelper.assertTrue(excelNorm.contains(uiDateNorm), "❌ Date missing");
        AssertHelper.assertTrue(excelNorm.contains(uiOpenTillNorm), "❌ Open Till missing");
        AssertHelper.assertTrue(excelNorm.contains("Project"), "❌ Project column not found");
        AssertHelper.assertTrue(excelNorm.contains("Tags"), "❌ Tags column not found");

        AssertHelper.assertTrue(excelNorm.contains(uiCreatedNorm), "❌ Date Created missing");
        AssertHelper.assertTrue(excelNorm.contains(uiStatusNorm), "❌ Status missing");

        LogUtils.info("✅ Excel content verified successfully!");

        FileHelper.deleteFile(fullPath);
    }

    public void verifyDownloadCSVFile(String expectedFileName) {

        String fileName = (expectedFileName != null && !expectedFileName.isEmpty())
                ? expectedFileName : "Proposals.csv";

        String fullPath = DriverManager.getDownloadPath() + File.separator + fileName;

        LogUtils.info("🔍 Waiting for downloaded CSV file: " + fileName);

        FileHelper.waitForFileExists(fullPath, 10);

        StringBuilder csvTextBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fullPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                csvTextBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to read CSV file: " + fullPath, e);
        }

        String csvText = csvTextBuilder.toString();
        LogUtils.info("📄 CSV Content (raw):\n" + csvText);

        String csvNorm = normalizeText(csvText);

        String uiProposalNorm = normalizeText(uiProposalNumber);
        String uiSubjectNorm = normalizeText(uiSubject);
        String uiToNorm = normalizeText(uiTo);
        String uiTotalNorm = normalizeText(uiTotal);
        String uiDateNorm = normalizeText(uiDate);
        String uiOpenTillNorm = normalizeText(uiOpenTill);
        String uiProjectNorm = normalizeText(uiProject);
        String uiTagsNorm = normalizeText(uiTags);
        String uiCreatedNorm = normalizeText(uiCreated);
        String uiStatusNorm = normalizeText(uiStatus);

        LogUtils.info("🔢 UI Data to verify inside CSV: " +
                "\nProposal#: " + uiProposalNorm +
                "\nSubject: " + uiSubjectNorm +
                "\nTo: " + uiToNorm +
                "\nTotal: " + uiTotalNorm +
                "\nDate: " + uiDateNorm +
                "\nOpen Till: " + uiOpenTillNorm +
                "\nProject: " + uiProjectNorm +
                "\nTags: " + uiTagsNorm +
                "\nDate Created: " + uiCreatedNorm +
                "\nStatus: " + uiStatusNorm
        );

        AssertHelper.assertTrue(csvNorm.contains(uiProposalNorm), "❌ Proposal # not found");
        AssertHelper.assertTrue(csvNorm.contains(uiSubjectNorm), "❌ Subject not found");
        AssertHelper.assertTrue(csvNorm.contains(uiToNorm), "❌ To not found");
        AssertHelper.assertTrue(csvNorm.contains(uiTotalNorm), "❌ Total missing");
        AssertHelper.assertTrue(csvNorm.contains(uiDateNorm), "❌ Date missing");
        AssertHelper.assertTrue(csvNorm.contains(uiOpenTillNorm), "❌ Open Till missing");
        AssertHelper.assertTrue(csvNorm.contains("Project"), "❌ Project column not found");
        AssertHelper.assertTrue(csvNorm.contains("Tags"), "❌ Tags column not found");
        AssertHelper.assertTrue(csvNorm.contains(uiCreatedNorm), "❌ Date Created missing");
        AssertHelper.assertTrue(csvNorm.contains(uiStatusNorm), "❌ Status missing");

        LogUtils.info("✅ CSV content verified successfully!");

        FileHelper.deleteFile(fullPath);
    }



<<<<<<< HEAD
}
=======
}
>>>>>>> 8750b133f09992f03a85474f9b6de1d17f053f29
