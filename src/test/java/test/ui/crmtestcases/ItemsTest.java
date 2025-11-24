package test.ui.crmtestcases;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import test.ui.common.BaseTest;

public class ItemsTest extends BaseTest {

    @Epic("Regression Test")
    @Feature("Add New Item")
    @Story("Item")
    @Owner("Bin Tester")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Jira", url = "https://anhtester.atlassian.net/browse/CRM-15")
    @Issue("CRM-15")
    @Description("Add new Item, verify and delete Item")
    @Test(priority = 0)
    public void manageKnowledgeBase() {
        loginPage().loginCRM();
        dashboardPage().verifyDashboardPage("Invoices Awaiting Payment", "1 / 3");
        basePage().clickMenuSalesPage();
        basePage().clickMenuItemsPage();
        itemsPage().clickButtonImportItems();
        itemsPage().importCSVFile();
        itemsPage().clickToImportCSVFile();
        basePage().clickMenuSalesPage();
        basePage().clickMenuItemsPage();
        itemsPage().searchAndVerifyItems();
        itemsPage().deleteImportedItem();
        headerPage().logout();
    }
}
