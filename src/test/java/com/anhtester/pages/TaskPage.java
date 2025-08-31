package com.anhtester.pages;

import com.anhtester.drivers.DriverManager;
import com.anhtester.keywords.WebUI;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.By;
import org.testng.Assert;

public class TaskPage extends CommonPage {

    //verify projects page
    private By titleTaskPage = By.xpath("//span[normalize-space()='Tasks Summary']");
    private By switchToKanBan = By.xpath("//i[@class='fa-solid fa-grip-vertical']");
    private By switchToList = By.xpath("//i[@class='fa-solid fa-table-list']");

    //verify after switchToKanBan / Refresh page and verify different total with verify after switchToKanBan / Drag and Drop and verify Total task
    private By completeTaskTotal = By.xpath("//body/div[@id='wrapper']/div[@class='content']/div[@class='row']/div[@class='col-md-12']/div[@id='kan-ban-tab']/div[@class='row']/div[@class='container-fluid']/div[@id='kan-ban']/ul[5]/li[1]/div[1]/div[1]");
    private By notStartedTaskTotal = By.xpath("//body/div[@id='wrapper']/div[@class='content']/div[@class='row']/div[@class='col-md-12']/div[@id='kan-ban-tab']/div[@class='row']/div[@class='container-fluid']/div[@id='kan-ban']/ul[1]/li[1]/div[1]/div[1]");
    private By completeTaskAfterDragDrop = By.xpath("//div[@class='panel-heading' and normalize-space()='Complete - 0 Tasks']");

    //verify Add new task page
    private By newTaskButton = By.xpath("//a[normalize-space()='New Task']");
    private By titleAddNewTaskPopUp = By.xpath("//h4[@id='myModalLabel']");
    private By checkboxPublic = By.xpath("//input[@id='task_is_public']");
    private By textCheckboxPublic = By.xpath("//label[normalize-space()='Public']");
    private By checkboxBillable = By.xpath("//input[@id='task_is_billable']");
    private By textCheckboxBillable = By.xpath("//label[normalize-space()='Billable']");


    //Add new task page
    private By inputsubject = By.xpath("//input[@id='name']");
    private By saveTask = By.xpath("//button[normalize-space()='Save']");
    private By alertAddNewTaskSuccess = By.xpath("//span[@class='alert-title']");

    //Verify Add new task page, mark complete and complete status
    private By taskName = By.xpath("//h4[@class='modal-title tw-flex tw-items-center']");
    private By taskStatus = By.xpath("//span[@class='trigger pointer manual-popover text-has-action tw-text-neutral-800']");
    private By taskDescription = By.xpath("//span[@class='text-muted']");
    private By markComplete = By.xpath("//i[@class='fa fa-check']");
    private By closeAlert = By.xpath("(//button[@data-dismiss='alert']//span[@aria-hidden='true'][normalize-space()='×']");
    private By closePopUp = By.xpath("((//button[@aria-label='Close'])[3]");

    //Verify Search Task Created and other Status don't have
    private By searchOnKanBan = By.xpath("//input[@id='search']");
    private By nodataNotStarted = By.xpath("//ul[1]//li[1]//div[1]//div[2]//div[1]//ul[1]//li[1]//h4[1]");
    private By nodataInprogress = By.xpath("//ul[2]//li[1]//div[1]//div[2]//div[1]//ul[1]//li[1]//h4[1]");
    private By nodataTesting = By.xpath("//ul[3]//li[1]//div[1]//div[2]//div[1]//ul[1]//li[1]//h4[1]");
    private By nodataAwaitingFeedback = By.xpath("//ul[4]//li[1]//div[1]//div[2]//div[1]//ul[1]//li[1]//h4[1]");

    //Click and add new description
    private By taskCreated = By.xpath("//span[@class='inline-block full-width mtop10 tw-truncate']");
    private By description = By.xpath("//i[@class='fa-regular fa-pen-to-square']");
    private By inputDescription = By.xpath("//div[@id='task_view_description']");

    //Close popup and verify description
    private By verifyDescription = By.xpath("//p[normalize-space()='Bin add new description']");
    private By completeTask = By.xpath("//ul[5]//li[1]//div[1]//div[2]//div[1]//ul[1]//li[1]");
    private By notStartedTask = By.xpath("//ul[1]//li[1]//div[1]//div[2]//div[1]//ul[1]//li[1]");
    private By binTask = By.xpath("//span[normalize-space()='Bin Task']");

    //DragDrop
    private By from = By.xpath("//ul[5]//li[1]//div[1]//div[2]//div[1]//ul[1]//li[1]");
    private By to = By.xpath("//ul[1]//li[1]//div[1]//div[2]//div[1]//ul[1]//li[1]");

    //click switch to list, search and delete
    private By searchOnList = By.xpath("//input[@class='form-control input-sm']");
    private By binTaskOnList = By.xpath("//a[normalize-space()='Bin Task']");
    private By deleteTask = By.xpath("//a[normalize-space()='Delete']");

    //verify delete success, search and verify nodata after search
    private By nodataTask = By.xpath("//td[@class='dataTables_empty']");

    //logout
    private By iconUser = By.xpath("//img[@class='img img-responsive staff-profile-image-small tw-ring-1 tw-ring-offset-2 tw-ring-primary-500 tw-mx-1 tw-mt-2.5']");
    private By logOutoption = By.xpath("//ul[@class='dropdown-menu animated fadeIn']//li[@class='header-logout']//a[@href='#'][normalize-space()='Logout']");
    private By dismissAlert = By.xpath("//button[@data-dismiss='alert']");

    public void verifyNavigateToTasksPage()
    {
        Assert.assertTrue(WebUI.checkElementExist(titleTaskPage), "The TaskPage title not display.");
        WebUI.assertEquals(WebUI.getTextElement(titleTaskPage), "Tasks Summary", "The TaskPage title not match.");
        Assert.assertTrue(WebUI.checkElementExist(switchToKanBan), "The switchToKanBan not display.");
    }

    public void clickButtonSwitchToKanBan()
    {
        WebUI.clickElement(switchToKanBan);
    }

    public void verifyNavigateToKanbanPage()
    {
        Assert.assertTrue(WebUI.checkElementExist(completeTaskTotal), "The completeTaskTotal title not display.");
        WebUI.assertEquals(WebUI.getTextElement(completeTaskTotal), "Complete - 0 Tasks", "The completeTaskTotal title not match.");
    }

    public void clickButtonAddNewTask()
    {
        WebUI.clickElement(newTaskButton);
    }

    public void verifyAddNewTaskPopUp()
    {
        Assert.assertTrue(WebUI.checkElementExist(titleAddNewTaskPopUp), "The AddNewTask title not display.");
        WebUI.assertEquals(WebUI.getTextElement(titleAddNewTaskPopUp), "Add new task", "The AddNewTask title not match.");
        //Assert.assertTrue(WebUI.checkElementExist(checkboxPublic), "The checkboxPublic not display.");
        //Assert.assertFalse(WebUI.isElementSelected(checkboxPublic), "Checkbox Public should NOT be selected.");
        Assert.assertTrue(WebUI.checkElementExist(textCheckboxPublic), "The textCheckboxPublic not display.");
        WebUI.assertEquals(WebUI.getTextElement(textCheckboxPublic), "Public", "The textCheckboxPublic not match.");
        Assert.assertTrue(WebUI.checkElementExist(checkboxBillable), "The checkboxBillable not display.");
        Assert.assertTrue(WebUI.isElementSelected(checkboxBillable), "Checkbox Public should be selected.");
        Assert.assertTrue(WebUI.checkElementExist(textCheckboxBillable), "The textCheckboxBillable title not display.");
        WebUI.assertEquals(WebUI.getTextElement(textCheckboxBillable), "Billable", "The textCheckboxBillable not match.");
    }

    public void submitDataForNewTask(String taskName)
    {
        WebUI.setTextElement(inputsubject, taskName);
        WebUI.clickElement(saveTask);
    }

    public void verifyNewTaskAfterCreated()
    {
        Assert.assertTrue(WebUI.checkElementExist(alertAddNewTaskSuccess), "The alertAddNewTaskSuccess title not display.");
        WebUI.assertEquals(WebUI.getTextElement(alertAddNewTaskSuccess), "Task added successfully.", "The alertAddNewTaskSuccess title not match.");
        Assert.assertTrue(WebUI.checkElementExist(taskName), "The taskName not display.");
        WebUI.assertEquals(WebUI.getTextElement(taskName).replaceAll("\\s+", " ").trim(),
                "Bin Task In Progress",
                "The taskName not match.");
        Assert.assertTrue(WebUI.checkElementExist(taskStatus), "The taskStatus not display.");
        WebUI.assertEquals(WebUI.getTextElement(taskStatus), "In Progress", "The taskStatus not match.");
    }

    public void markCompletedAndRefreshPage()
    {
        WebUI.clickElement(markComplete);
        //WebUI.clickElement(closePopUp);
        WebUI.waitForPageRefresh(binTask);
    }

    public void verifyCompleteTasksAfterRefreshed()
    {
        Assert.assertTrue(WebUI.checkElementExist(completeTaskTotal), "The completeTaskTotal title not display.");
        WebUI.assertEquals(WebUI.getTextElement(completeTaskTotal), "Complete - 1 Tasks", "The completeTaskTotal title not match.");
    }

    public static void inputText() throws InterruptedException, AWTException
    {
        WebUI.sleep(1);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        WebUI.sleep(1);
        robot.keyPress(KeyEvent.VK_DELETE);
        robot.keyRelease(KeyEvent.VK_DELETE);
    }

    public void addNewDescriptionAndVerifyDescription() throws InterruptedException, AWTException
    {
        WebUI.clickElement(binTask);
        WebUI.clickElement(description);
        inputText();
        WebUI.setTextElement(inputDescription, "Bin add new description");
        WebUI.waitForPageRefresh(binTask);
        //WebUI.clickElement(closePopUp);
    }

    public void searchAndVerifyAfterSearch(String taskName)
    {
        WebUI.setTextElement(searchOnKanBan, taskName);
        Assert.assertTrue(WebUI.checkElementExist(nodataNotStarted), "The nodataNotStarted title not display.");
        WebUI.assertEquals(WebUI.getTextElement(nodataNotStarted), "No Tasks Found", "The nodataNotStarted title not match.");
        Assert.assertTrue(WebUI.checkElementExist(nodataInprogress), "The nodataInprogress title not display.");
        WebUI.assertEquals(WebUI.getTextElement(nodataInprogress), "No Tasks Found", "The nodataInprogress title not match.");
        Assert.assertTrue(WebUI.checkElementExist(nodataTesting), "The nodataTesting title not display.");
        WebUI.assertEquals(WebUI.getTextElement(nodataTesting), "No Tasks Found", "The nodataTesting title not match.");
        Assert.assertTrue(WebUI.checkElementExist(nodataAwaitingFeedback), "The nodataAwaitingFeedback title not display.");
        WebUI.assertEquals(WebUI.getTextElement(nodataAwaitingFeedback), "No Tasks Found", "The nodataAwaitingFeedback title not match.");
    }

    public void dragAndDrop()
    {
        WebUI.dragAndDrop(from, to);
    }

    public void verifyTotalTasksAfterDragDrop()
    {
        Assert.assertTrue(WebUI.checkElementExist(completeTaskAfterDragDrop), "The completeTaskTotal not display.");
        WebUI.assertEquals(WebUI.getTextElement(completeTaskAfterDragDrop), "Complete - 0 Tasks", "The completeTaskTotal not match.");
        Assert.assertTrue(WebUI.checkElementExist(notStartedTaskTotal), "The notStartedTaskTotal not display.");
        WebUI.assertEquals(WebUI.getTextElement(notStartedTaskTotal), "Not Started - 1 Tasks", "The notStartedTaskTotal not match.");
    }

    public void clickAndVerifyDescriptionAfterDragDrop()
    {
        WebUI.clickElement(taskCreated);
        Assert.assertTrue(WebUI.checkElementExist(verifyDescription), "The Description not display.");
        WebUI.assertEquals(WebUI.getTextElement(verifyDescription), "Bin add new description", "The Description not match.");
        WebUI.clickElement(closePopUp);
    }

    public void searchAndDeleteTask(String taskName)
    {
        WebUI.clickElement(switchToList);
        WebUI.setTextElement(searchOnList, taskName);
        WebUI.moveToElement(binTaskOnList);
        WebUI.clickElement(deleteTask);
        WebUI.acceptAlert();
    }

    public void searchAfterDeleted(String taskName)
    {
        WebUI.setTextElement(searchOnList, taskName);
    }

    public void verifyNoDataAfterDeleted()
    {
        Assert.assertTrue(WebUI.checkElementExist(nodataTask), "The nodataTask not display.");
        WebUI.assertEquals(WebUI.getTextElement(nodataTask), "No matching records found", "The nodataTask not match.");
    }

    public void logOutCRM()
    {
        WebUI.clickElement(dismissAlert);
        WebUI.clickElement(iconUser);
        WebUI.clickElement(logOutoption);
    }
}
