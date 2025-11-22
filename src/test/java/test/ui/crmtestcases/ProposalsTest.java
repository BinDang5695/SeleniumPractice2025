package test.ui.crmtestcases;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import test.ui.common.BaseTest;

public class ProposalsTest extends BaseTest {

    @Epic("Regression Test")
    @Feature("Add New Proposal")
    @Story("Proposal")
    @Owner("Bin Tester")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Jira", url = "https://anhtester.atlassian.net/browse/CRM-12")
    @Issue("CRM-12")
    @Description("Add new Proposal, verify and delete Proposal")
    @Test(priority = 0)
    public void manageProposals() {
        loginPage().loginCRM();
        dashboardPage().verifyDashboardPage("Invoices Awaiting Payment", "1 / 3");
        basePage().clickMenuSalesPage();
        basePage().clickMenuProposalsPage();
        proposalsPage().clickButtonNewProposal();
        proposalsPage().addNewProposal();
        proposalsPage().verifyTooltip();
        proposalsPage().searchCreatedProposal();
        proposalsPage().captureUITableData();
        proposalsPage().exportPDFFile();
        proposalsPage().verifyDownloadPDFFile("Proposals.pdf");
        proposalsPage().deleteCreatedProposal();
        headerPage().logout();
    }
}
