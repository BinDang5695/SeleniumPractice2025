package test.ui.crmtestcases;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import test.ui.common.BaseTest;

public class LeadsTest extends BaseTest {

    @Epic("Regression Test")
    @Feature("Add New Lead")
    @Story("Lead")
    @Owner("Bin Tester")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Jira", url = "https://anhtester.atlassian.net/browse/CRM-10")
    @Issue("CRM-10")
    @Description("Add new Lead, verify and delete Lead")
    @Test(priority = 0)
    public void manageLead() {
        loginPage().loginCRM();
        dashboardPage().verifyDashboardPage("Invoices Awaiting Payment", "1 / 3");
        leadsPage().createMultipleLeads(11);
        leadsPage().searchAndCheckDataInTable(3, "Bin Lead", "Name");
        leadsPage().deleteDataAfterSearched();
        leadsPage().verifyDeletedLeads();
        headerPage().logout();
    }
}
