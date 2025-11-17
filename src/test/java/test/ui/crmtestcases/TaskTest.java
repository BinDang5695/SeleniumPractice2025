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
<<<<<<< HEAD
=======
        String taskName = "Bin Task";
        String taskNameEdited = "NashTech";
>>>>>>> 505f0111a689153ed2faf36e7bbe7c06b69d0fc0
        taskPage().verifyNavigateToTasksPage();
        taskPage().clickButtonSwitchToKanBan();
        taskPage().verifyNavigateToKanbanPage();
        taskPage().clickButtonAddNewTask();
        taskPage().verifyAddNewTaskPopUp();
<<<<<<< HEAD
        taskPage().submitDataForNewTask();
        taskPage().verifyNewTaskAfterCreated();
        taskPage().markCompletedAndRefreshPage();
        taskPage().verifyCompleteTasksAfterRefreshed();
        taskPage().editTask();
        taskPage().searchAndVerifyAfterSearch();
        taskPage().dragAndDrop();
        taskPage().verifyTotalTasksAfterDragDrop();
        taskPage().searchAndDeleteTask();
        taskPage().searchAfterDeleted();
        taskPage().verifyNoDataAfterDeleted();
        taskPage().clickDismissAlert();
        headerPage().logout();
=======
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
>>>>>>> 505f0111a689153ed2faf36e7bbe7c06b69d0fc0
    }
}