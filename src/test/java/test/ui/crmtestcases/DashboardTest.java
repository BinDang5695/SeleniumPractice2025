package test.ui.crmtestcases;

import test.ui.common.BaseTest;
import org.testng.annotations.Test;

    public class DashboardTest extends BaseTest {

        @Test
        public void verifyDashboard()
        {
            loginPage().loginCRM();
            dashboardPage().verifyDashboardPage("Invoices Awaiting Payment", "1 / 3");
        }

<<<<<<< HEAD
=======



>>>>>>> 505f0111a689153ed2faf36e7bbe7c06b69d0fc0
}
