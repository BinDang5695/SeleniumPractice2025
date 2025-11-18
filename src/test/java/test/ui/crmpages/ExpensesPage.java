package test.ui.crmpages;

import org.openqa.selenium.By;
import settings.helpers.AssertHelper;
import settings.helpers.SystemHelper;
import settings.keywords.WebUI;
import test.ui.common.BasePage;

public class ExpensesPage extends BasePage {

    private By buttonRecordExpense = By.xpath("//a[normalize-space()='Record Expense']");
    private By buttonAttachReceipt = By.xpath("//span[normalize-space()='Attach Receipt']");
    String filePath = SystemHelper.getCurrentDir() + "src\\test\\resources\\testdata\\UK.jpg";
    private By inputName = By.xpath("//input[@id='expense_name']");
    private By inputNote = By.xpath("//textarea[@id='note']");
    private By dropdownExpenseCategory = By.xpath("//div[contains(text(),'Nothing selected')]");
    private By optionBinCategory = By.xpath("//span[normalize-space()='Bin Category']");
    private By inputExpenseDate = By.xpath("//input[@id='date']");
    private By inputAmount = By.xpath("//input[@id='amount']");
    private By dropdownPaymentMode = By.xpath("//button[@data-id='paymentmode']");
    private By optionBank = By.xpath("//a[normalize-space()='Bank']");
    private By inputReference = By.xpath("//input[@id='reference_no']");
    private By dropdownRepeatEvery = By.xpath("//button[@data-id='repeat_every']");
    private By optionWeek = By.xpath("//span[normalize-space()='Week']");
    private By checkboxInfinity = By.xpath("//label[normalize-space()='Infinity']");
    private By inputTotalCycles = By.xpath("//input[@id='cycles']");
    private By buttonSave = By.xpath("//div[@class='btn-bottom-toolbar text-right']//button[@type='submit'][normalize-space()='Save']");
    //Verify
    private By alertSuccess = By.xpath("//span[@class='alert-title']");
    private By expenseName = By.xpath("//h4[@id='expenseName']");
    private By expenseNote = By.xpath("//div[normalize-space()='Bin Note']");
    private By expenseCategory = By.xpath("//h3[@id='expenseCategory']");
    private By expenseDate = By.xpath("//span[normalize-space()='18-11-2026']");
    private By expenseAmount = By.xpath("//span[contains(normalize-space(),'$1,000.00')]");
    private By expensePaymentMode = By.xpath("//span[contains(normalize-space(),'Paid Via Bank')]");
    private By expenseRef = By.xpath("//span[normalize-space()='#1000']");
    private By expenseRepeat = By.xpath("//b[normalize-space()='25-11-2026']");
    private By expenseCyclesRemaining= By.xpath("//b[normalize-space()='10']");
    private By attachedReceipt = By.xpath("//a[normalize-space()='UK.jpg']");
    //Edit
    private By buttonEditExpense = By.xpath("//i[contains(@class,'pen')]");
    private By updatedExpenseAmount = By.xpath("//span[contains(normalize-space(),'$2,000.00')]");
    //Delete
    private By buttonDeleteExpense = By.xpath("//a[contains(@class,'delete')]//i[contains(@class,'remove')]");
    //Verify after deleted
    private By inputSearchExpenses = By.xpath("//input[@aria-controls='expenses']");
    private By noDataAfterDelete = By.xpath("//td[@class='dataTables_empty']");
    private By buttonX = By.xpath("//button[@data-dismiss='alert']//span[@aria-hidden='true'][normalize-space()='×']");

    public void clickButtonRecordExpense() {
        WebUI.waitForElementVisible(buttonRecordExpense);
        WebUI.clickElement(buttonRecordExpense);
    }

    public void addNewExpense() {
        WebUI.uploadFileWithRobotClass(buttonAttachReceipt, filePath);
        WebUI.setTextElement(inputName, "Bin Name");
        WebUI.setTextElement(inputNote, "Bin Note");
        WebUI.clickElement(dropdownExpenseCategory);
        WebUI.clickElement(optionBinCategory);
        WebUI.setTextElement(inputExpenseDate, "18-11-2026");
        WebUI.setTextElement(inputAmount, "1000");
        WebUI.clickElement(dropdownPaymentMode);
        WebUI.clickElement(optionBank);
        WebUI.setTextElement(inputReference, "#1000");
        WebUI.clickElement(dropdownRepeatEvery);
        WebUI.clickElement(optionWeek);
        WebUI.clickElement(checkboxInfinity);
        WebUI.setTextElement(inputTotalCycles, "10");
        WebUI.clickElement(buttonSave);
    }

    public void verifyCreatedExpense() {
        AssertHelper.assertEquals(WebUI.getTextElement(expenseName), "Bin Name", "Expense Name does not match");
        AssertHelper.assertEquals(WebUI.getTextElement(expenseNote), "Bin Note", "Expense Note does not match");
        AssertHelper.assertEquals(WebUI.getTextElement(expenseCategory), "Bin Category", "Expense Category does not match");
        AssertHelper.assertEquals(WebUI.getTextElement(expenseDate), "18-11-2026", "Expense Date does not match");
        AssertHelper.assertEquals(WebUI.getTextElement(expenseAmount), "$1,000.00", "Expense Amount does not match");
        AssertHelper.assertEquals(WebUI.getTextElement(expensePaymentMode), "Paid Via Bank", "Expense Payment Mode does not match");
        AssertHelper.assertEquals(WebUI.getTextElement(expenseRef), "#1000", "Expense Ref does not match");
        AssertHelper.assertEquals(WebUI.getTextElement(expenseRepeat), "25-11-2026", "Expense Repeat does not match");
        AssertHelper.assertEquals(WebUI.getTextElement(expenseCyclesRemaining), "10", "Expense Cycles Remaining does not match");
        AssertHelper.assertEquals(WebUI.getTextElement(attachedReceipt), "UK.jpg", "Expense attached Receipt does not match");
    }

    public void updateExpense() {
        WebUI.clickElement(buttonEditExpense);
        WebUI.setTextElement(inputName, "Bin Name Updated");
        WebUI.setTextElement(inputNote, "Bin Note Updated");
        WebUI.setTextElement(inputAmount, "2000");
        WebUI.clickElement(buttonSave);
    }

    public void verifyUpdatedContract() {
        AssertHelper.assertEquals(WebUI.getTextElement(alertSuccess), "Expense updated successfully.", "Alert Success after updated does not match");
        AssertHelper.assertEquals(WebUI.getTextElement(expenseName), "Bin Name Updated", "Expense Name after updated does not match");
        AssertHelper.assertEquals(WebUI.getTextElement(updatedExpenseAmount), "$2,000.00", "Expense Amount after updated does not match");
    }

    public void deleteExpense() {
        WebUI.clickElement(buttonDeleteExpense);
        WebUI.acceptAlert();
    }

    public void verifyDeletedContract() {
        AssertHelper.assertEquals(WebUI.getTextElement(alertSuccess), "Expense deleted", "Alert Success after deleted does not match");
        WebUI.clickElement(buttonX);
        WebUI.setTextElement(inputSearchExpenses, "Bin Category");
        AssertHelper.assertEquals(WebUI.getTextElement(noDataAfterDelete), "No matching records found", "Deleted Contract still shown");
    }
}
