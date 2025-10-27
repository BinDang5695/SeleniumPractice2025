package test.ui.crmtestcases;

import test.ui.common.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

public class TaskTest extends BaseTest {

    @Epic("Regression")
    @Feature("DMS")
    @Story("Task")
    @Owner("Bin Tester")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://crm.anhtester.com/admin/tasks")
    @Issue("https://nashtech-global.atlassian.net/")
    @Test(description = "Verify can create, update & delete Task")
    public void verifyTask() {
        loginPage().loginCRM();
        dashboardPage().verifyDashboardPage("Invoices Awaiting Payment", "1 / 3");
        taskPage().clickMenuTasks();
        String taskName = "Bin Task";
        String taskNameEdited = "NashTech";
        taskPage().verifyNavigateToTasksPage();
        taskPage().clickButtonSwitchToKanBan();
        taskPage().verifyNavigateToKanbanPage();
        taskPage().clickButtonAddNewTask();
        taskPage().verifyAddNewTaskPopUp();
        taskPage().submitDataForNewTask(taskName);
        taskPage().verifyNewTaskAfterCreated();
        taskPage().markCompletedAndRefreshPage();
        taskPage().verifyCompleteTasksAfterRefreshed();
        taskPage().editTask(taskNameEdited);
        taskPage().searchAndVerifyAfterSearch(taskNameEdited);
        taskPage().dragAndDrop();
        taskPage().verifyTotalTasksAfterDragDrop();
        taskPage().searchAndDeleteTask(taskNameEdited);
        taskPage().searchAfterDeleted(taskNameEdited);
        taskPage().verifyNoDataAfterDeleted();
        taskPage().logOutCRM();
    }
}