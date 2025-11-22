package test.ui.crmtestcases;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import test.ui.common.BaseTest;

public class Invoices extends BaseTest {

    @Epic("Regression Test")
    @Feature("Add New Expense")
    @Story("Expense")
    @Owner("Bin Tester")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Jira", url = "https://anhtester.atlassian.net/browse/CRM-9")
    @Issue("CRM-9")
    @Description("Add new Expense, verify and delete Expense")
    @Test(priority = 0)
    public void manageExpense() {
        loginPage().loginCRM();
        dashboardPage().verifyDashboardPage("Invoices Awaiting Payment", "1 / 3");
        basePage().clickMenuExpenses();
        expensesPage().clickButtonRecordExpense();
        expensesPage().addNewExpense();
        expensesPage().verifyCreatedExpense();
        expensesPage().updateExpense();
        expensesPage().verifyUpdatedContract();
        expensesPage().deleteExpense();
        expensesPage().verifyDeletedContract();
        headerPage().logout();
    }
}
