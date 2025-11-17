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
        projectPage().clickMenuProjects();
<<<<<<< HEAD
        projectPage().verifyNavigateToProjectPage();
        projectPage().clickButtonAddNewCustomer();
        projectPage().submitDataForNewCustomer();
        projectPage().verifyProjectCreated();
        customerPage().clickMenuCustomers();
        projectPage().searchAndCheckCustomerInTable();
=======
        String projectName = "Bin Project";
        String customerName = "Bin Customer";
        projectPage().verifyNavigateToProjectPage();
        projectPage().clickButtonAddNewCustomer();
        projectPage().submitDataForNewCustomer(projectName);
        projectPage().verifyProjectCreated();
        customerPage().clickMenuCustomers();
        projectPage().searchAndCheckCustomerInTable(customerName);
>>>>>>> 505f0111a689153ed2faf36e7bbe7c06b69d0fc0
        projectPage().clickFirstCustomer();
        projectPage().clickTabProjects();
        projectPage().verifyProjectCreatedOnProjectTab();
        projectPage().moveToProjectName();
        projectPage().clickAndDeleteProject();
        projectPage().clickMenuProjects();
<<<<<<< HEAD
        projectPage().searchAndCheckProjectInTable();
        projectPage().verifyNoDataAfterDeletedProject();
        headerPage().logout();
=======
        projectPage().searchAndCheckProjectInTable(projectName);
        projectPage().verifyNoDataAfterDeletedProject();
        projectPage().logOutCRM();
>>>>>>> 505f0111a689153ed2faf36e7bbe7c06b69d0fc0
    }
}