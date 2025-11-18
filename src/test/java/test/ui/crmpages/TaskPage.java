package test.ui.crmpages;

import settings.helpers.AssertHelper;
import settings.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;
import test.ui.common.BasePage;

public class TaskPage extends BasePage {

    //verify projects page
    private By titleTaskPage = By.xpath("//span[normalize-space()='Tasks Summary']");
    private By switchToKanBan = By.xpath("//i[@class='fa-solid fa-grip-vertical']");
    private By switchToList = By.xpath("//i[@class='fa-solid fa-table-list']");

    //verify after switchToKanBan
    private By completeTaskTotal = By.xpath("(//div[@class='panel-heading'])[5]");
    private By notStartedTaskTotal = By.xpath("(//div[@class='panel-heading'])[1]");
    private By completeTaskAfterDragDrop = By.xpath("//div[@class='panel-heading' and normalize-space()='Complete - 0 Tasks']");

    //verify Add new task page
    private By newTaskButton = By.xpath("//a[normalize-space()='New Task']");
    private By titleAddNewTaskPopUp = By.xpath("//h4[normalize-space()='Add new task']");
    private By inputStartDate = By.xpath("//input[@id='startdate']");
    private By titleSubject = By.xpath("//label[@for='name']");

    //Add new task page
    private By inputSubject = By.xpath("//input[@id='name']");
    private By saveTask = By.xpath("//button[normalize-space()='Save']");
    private By alertAddNewTaskSuccess = By.xpath("//span[@class='alert-title']");

    //Verify Add new task page, mark complete and complete status
    private By taskName = By.xpath("//h4[contains(@class,'modal-title ')]");
    private By taskStatus = By.xpath("//span[contains(@class,'trigger') and normalize-space()='In Progress']");
    private By markComplete = By.xpath("//i[@class='fa fa-check']");
    private By closePopUp = By.xpath("//div[@class='modal-header task-single-header']//button[@aria-label='Close']");

    //Verify Search Task Created and other Status don't have
    private By searchOnKanBan = By.xpath("//input[@id='search']");
    private By nodataNotStarted = By.xpath("(//h4[normalize-space()='No Tasks Found'])[1]");
    private By nodataInprogress = By.xpath("(//h4[normalize-space()='No Tasks Found'])[2]");
    private By nodataTesting = By.xpath("(//h4[normalize-space()='No Tasks Found'])[3]");
    private By nodataAwaitingFeedback = By.xpath("(//h4[normalize-space()='No Tasks Found'])[4]");

    //Click and edit Task
    private By menu = By.xpath("//a[@class='trigger manual-popover mright5']");
    private By editOption = By.xpath("//div[@class='popover-content']//ul//li//a[@href='#'][normalize-space()='Edit']");

    //Close popup and verify description
    private By binTask = By.xpath("//span[normalize-space()='Bin Task']");

    //DragDrop
    private By from = By.xpath("//ul[5]//li[1]//div[1]//div[2]//div[1]//ul[1]//li[1]");
    private By to = By.xpath("//ul[1]//li[1]//div[1]//div[2]//div[1]//ul[1]//li[1]");

    //click switch to list, search and delete
    private By searchOnList = By.xpath("//input[@aria-controls='tasks']");
    private By binEditedTaskOnList = By.xpath("//a[normalize-space()='NashTech']");
    private By deleteTask = By.xpath("//a[normalize-space()='Delete']");

    //verify delete success, search and verify nodata after search
    private By nodataTask = By.xpath("//td[@class='dataTables_empty']");

    //logout
    private By dismissAlert = By.xpath("//button[@data-dismiss='alert']");

    public void verifyNavigateToTasksPage()
    {
        Assert.assertTrue(WebUI.checkElementExist(titleTaskPage), "The TaskPage title not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(titleTaskPage), "Tasks Summary", "The TaskPage title not match.");
        Assert.assertTrue(WebUI.checkElementExist(switchToKanBan), "The switchToKanBan not display.");
    }

    public void clickButtonSwitchToKanBan()
    {
        WebUI.clickElement(switchToKanBan);
    }

    public void verifyNavigateToKanbanPage()
    {
        Assert.assertTrue(WebUI.checkElementExist(switchToList), "Not navigate to Kanban page yet.");
    }

    public void clickButtonAddNewTask()
    {
        WebUI.clickElement(newTaskButton);
    }

    public void verifyAddNewTaskPopUp()
    {
        Assert.assertTrue(WebUI.checkElementExist(titleAddNewTaskPopUp), "The AddNewTask title not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(titleAddNewTaskPopUp), "Add new task", "The AddNewTask title not match.");
        Assert.assertTrue(WebUI.checkElementExist(inputSubject), "The inputsubject field not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(titleSubject), "* Subject", "The Subject title not match.");
        Assert.assertTrue(WebUI.checkElementExist(inputStartDate), "The inputStartDate not display.");
        Assert.assertFalse(WebUI.checkFieldIsToday(inputStartDate, "dd-MM-yyyy"), "The inputStartDate format NOT match.");
    }

    public void submitDataForNewTask()
    {
        WebUI.setTextElement(inputSubject, "Bin Task");
        WebUI.clickElement(saveTask);
    }

    public void verifyNewTaskAfterCreated()
    {
        Assert.assertTrue(WebUI.checkElementExist(alertAddNewTaskSuccess), "The alertAddNewTaskSuccess title not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(alertAddNewTaskSuccess), "Task added successfully.", "The alertAddNewTaskSuccess title not match.");
        Assert.assertTrue(WebUI.checkElementExist(taskName), "The taskName not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(taskName).replaceAll("\\s+", " ").trim(),
                "Bin Task In Progress",
                "The taskName not match.");
        Assert.assertTrue(WebUI.checkElementExist(taskStatus), "The taskStatus not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(taskStatus), "In Progress", "The taskStatus not match.");
    }

    public void markCompletedAndRefreshPage()
    {
        WebUI.clickElement(markComplete);
        WebUI.clickElement(closePopUp);
        WebUI.waitForPageRefresh(binTask);
    }

    public void verifyCompleteTasksAfterRefreshed()
    {
        Assert.assertTrue(WebUI.checkElementExist(completeTaskTotal), "The completeTaskTotal title not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(completeTaskTotal), "Complete - 1 Tasks", "The completeTaskTotal title not match.");
    }

    public void editTask()
    {
        WebUI.clickElement(binTask);
        WebUI.clickElement(menu);
        WebUI.clickElement(editOption);
        WebUI.setTextElement(inputSubject, "NashTech");
        WebUI.clickElement(saveTask);
        WebUI.clickElement(closePopUp);
    }

    public void searchAndVerifyAfterSearch()
    {
        WebUI.setTextElement(searchOnKanBan, "NashTech");
        Assert.assertTrue(WebUI.checkElementExist(nodataNotStarted), "The nodataNotStarted title not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(nodataNotStarted), "No Tasks Found", "The nodataNotStarted title not match.");
        Assert.assertTrue(WebUI.checkElementExist(nodataInprogress), "The nodataInprogress title not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(nodataInprogress), "No Tasks Found", "The nodataInprogress title not match.");
        Assert.assertTrue(WebUI.checkElementExist(nodataTesting), "The nodataTesting title not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(nodataTesting), "No Tasks Found", "The nodataTesting title not match.");
        Assert.assertTrue(WebUI.checkElementExist(nodataAwaitingFeedback), "The nodataAwaitingFeedback title not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(nodataAwaitingFeedback), "No Tasks Found", "The nodataAwaitingFeedback title not match.");
    }

    public void dragAndDrop()
    {
        WebUI.dragAndDrop(from, to);
    }

    public void verifyTotalTasksAfterDragDrop()
    {
        Assert.assertTrue(WebUI.checkElementExist(completeTaskAfterDragDrop), "The completeTaskTotal not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(completeTaskAfterDragDrop), "Complete - 0 Tasks", "The completeTaskTotal not match.");
        Assert.assertTrue(WebUI.checkElementExist(notStartedTaskTotal), "The notStartedTaskTotal not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(notStartedTaskTotal), "Not Started - 1 Tasks", "The notStartedTaskTotal not match.");
    }

    public void searchAndDeleteTask()
    {
        WebUI.clickElement(switchToList);
        WebUI.setTextElement(searchOnList, "NashTech");
        WebUI.scrollToTop();
        WebUI.moveToElement(binEditedTaskOnList);
        WebUI.clickElement(deleteTask);
        WebUI.acceptAlert();
    }

    public void searchAfterDeleted()
    {
        WebUI.setTextElement(searchOnList, "NashTech");
    }

    public void verifyNoDataAfterDeleted()
    {
        Assert.assertTrue(WebUI.checkElementExist(nodataTask), "The nodataTask not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(nodataTask), "No matching records found", "The nodataTask not match.");
    }

    public void clickDismissAlert()
    {
        WebUI.clickElement(dismissAlert);
    }
}
