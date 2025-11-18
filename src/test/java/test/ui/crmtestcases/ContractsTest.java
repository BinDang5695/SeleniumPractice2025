package test.ui.crmtestcases;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import test.ui.common.BaseTest;

public class ContractsTest extends BaseTest {

    @Epic("Regression Test")
    @Feature("Add New Contract")
    @Story("Contract")
    @Owner("Bin Tester")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Jira", url = "https://anhtester.atlassian.net/browse/CRM-8")
    @Issue("CRM-8")
    @Description("Add new Contract, verify and delete Contract")
    @Test(priority = 0)
    public void manageContract() {
        loginPage().loginCRM();
        dashboardPage().verifyDashboardPage("Invoices Awaiting Payment", "1 / 3");
        basePage().clickMenuContracts();
        contractsPage().clickButtonNewContract();
        contractsPage().addNewContract();
        contractsPage().verifyCreatedContract();
        contractsPage().updateContract();
        contractsPage().verifyUpdatedContract();
        contractsPage().deleteContract();
        basePage().clickMenuContracts();
        contractsPage().verifyDeletedContract();
        headerPage().logout();
    }
}
