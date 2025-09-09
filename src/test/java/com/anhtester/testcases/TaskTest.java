package com.anhtester.testcases;

import com.anhtester.common.BaseTest;
import com.anhtester.drivers.DriverManager;
import com.anhtester.keywords.WebUI;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import java.awt.AWTException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
        String taskNameEdited = "Bin Task Edited";
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