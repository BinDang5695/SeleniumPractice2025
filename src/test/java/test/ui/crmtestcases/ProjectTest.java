package test.ui.crmtestcases;

import test.ui.common.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

public class ProjectTest extends BaseTest {

    @Epic("Regression")
    @Feature("DMS")
    @Story("Project")
    @Owner("Bin Tester")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://crm.anhtester.com/admin/projects")
    @Issue("https://nashtech-global.atlassian.net/")
    @Test(description = "Verify Project of Customer before and after delete")
    public void verifyProjectOfCustomer()
    {
        loginPage().loginCRM();
        dashboardPage().verifyDashboardPage("Invoices Awaiting Payment", "1 / 3");
        basePage().clickMenuProjects();
        projectPage().verifyNavigateToProjectPage();
        projectPage().clickButtonAddNewCustomer();
        projectPage().submitDataForNewCustomer();
        projectPage().verifyProjectCreated();
        basePage().clickMenuCustomers();
        projectPage().searchAndCheckCustomerInTable();
        projectPage().clickFirstCustomer();
        basePage().clickTabProjects();
        projectPage().verifyProjectCreatedOnProjectTab();
        projectPage().moveToProjectName();
        projectPage().clickAndDeleteProject();
        basePage().clickMenuProjects();
        projectPage().searchAndCheckProjectInTable();
        projectPage().verifyNoDataAfterDeletedProject();
        headerPage().logout();
    }
}