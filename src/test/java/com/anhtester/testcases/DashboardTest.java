package com.anhtester.testcases;

import com.anhtester.common.BaseTest;
import org.testng.annotations.Test;

    public class DashboardTest extends BaseTest {

        @Test
        public void verifyDashboard()
        {
            loginPage().loginCRM("admin@example.com", "123456");
            dashboardPage().verifyDashboardPage("Invoices Awaiting Payment", "1 / 3");
        }




}
