package test.ui.crmtestcases;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import test.ui.common.BaseTest;

public class KnowledgeBaseTest extends BaseTest {

    @Epic("Regression Test")
    @Feature("Add New Knowledge Base")
    @Story("Knowledge Base")
    @Owner("Bin Tester")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Jira", url = "https://anhtester.atlassian.net/browse/CRM-11")
    @Issue("CRM-10")
    @Description("Add new Knowledge Base, verify and delete Knowledge Base")
    @Test(priority = 0)
    public void manageKnowledgeBase() {
        loginPage().loginCRM();
        dashboardPage().verifyDashboardPage("Invoices Awaiting Payment", "1 / 3");
        basePage().clickMenuKnowledgeBase();
        knowledgeBase().clickButtonNewArticle();
        knowledgeBase().addNewArticle();
        basePage().clickMenuKnowledgeBase();
        knowledgeBase().switchBetweenTabTest();
        knowledgeBase().deleteCreatedArticle();
        headerPage().logout();
    }
}
