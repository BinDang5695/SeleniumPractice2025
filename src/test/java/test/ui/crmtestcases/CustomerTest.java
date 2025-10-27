package test.ui.crmtestcases;

import test.ui.common.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomerTest extends BaseTest {

    @Epic("Regression Test")
    @Feature("Add New Customer")
    @Story("Customer")
    @Owner("Bin Tester")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Jira", url = "https://anhtester.atlassian.net/browse/CRM-4")
    @Issue("CRM-4")
    @Description("Add new Customer, verify and delete Customer")
    @Test(priority = 0)
    public void manageCustomer() {
        String customerName = "Nashtech Company";
        loginPage().loginCRM();
        dashboardPage().verifyDashboardPage("Invoices Awaiting Payment", "1 / 3");
        customerPage().clickMenuCustomers();
        int beforeTotal = customerPage().getTotalCustomers();
        customerPage().clickbuttonAddNewCustomer();
        customerPage().addNewCustomer(customerName);
        customerPage().verifyCustomerAdded();
        contactsPage().clickTabContacts();
        contactsPage().clickButtonNewContact();
        contactsPage().addNewContact("Bin", "Dang");
        contactsPage().verifyCreatedContact("Bin", "Dang");
        customerPage().searchCustomer(customerName);
        int afterTotal = customerPage().getTotalCustomers();
        Assert.assertEquals(afterTotal, beforeTotal + 1, "Total customers should be increased by 1 after adding a new customer.");
        customerPage().deleteCustomer(customerName);
    }





}
