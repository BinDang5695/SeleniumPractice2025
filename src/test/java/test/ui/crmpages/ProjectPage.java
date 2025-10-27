package test.ui.crmpages;

import settings.drivers.DriverManager;
import settings.helpers.AssertHelper;
import settings.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ProjectPage extends BasePage {

    //verify projects page
    private By titleProjectPage = By.xpath("//span[normalize-space()='Projects Summary']");
    private By inprogressProject = By.xpath("//span[@class='project-status-#2563eb']");
    private By inputSearchProject = By.xpath("//input[@class='form-control input-sm']");
    private By itemCustomerFirst = By.xpath("//table[@id='clients']/tbody/tr[1]/td[3]/a[normalize-space()='Bin Customer']");
    private By inputSearchCustomer = By.xpath("//div[@id='clients_filter']//input[@type='search']");
    private By customerID = By.xpath("//th[@id='th-number']");

    //verify addnewproject page
    private By buttonNewProject = By.xpath("//a[normalize-space()='New Project']");

    //addnewproject page
    private By inputprojectName = By.xpath("//input[@id='name']");
    private By inputcustomer = By.xpath("//button[@class='btn dropdown-toggle bs-placeholder btn-default']");
    private By searchCustomer = By.xpath("//div[@class='dropdown bootstrap-select ajax-search bs3 open']//input[@placeholder='Type to search...']");
    private By selectCustomer = By.xpath("//span[normalize-space()='Bin Customer']");
    private By checkBoxCalculate = By.xpath("//label[normalize-space()='Calculate progress through tasks']");
    private By saveProject = By.xpath("//button[normalize-space()='Save']");

    //verify project created
    private By projectNameCustomer = By.xpath("//button[@title='Bin Project - Bin Customer']//div[@class='filter-option-inner-inner']");
    private By projectProgress = By.xpath("//p[@class='project-info tw-mb-0 tw-font-medium tw-text-base tw-tracking-tight']");
    private By customer = By.xpath("//dt[normalize-space()='Customer']");
    private By projectNameCreated = By.xpath("//a[normalize-space()='Bin Customer']");
    private By status = By.xpath("//dt[normalize-space()='Status']");
    private By statusProject = By.xpath("//dd[normalize-space()='In Progress']");
    private By alertsuccess = By.xpath("//span[@class='alert-title']");

    //verify project created on project tab
    private By projectNameOnProjectTab = By.xpath("//a[normalize-space()='Bin Project']");
    private By statusOnProjectTab = By.xpath("//span[@class='label project-status-2']");

    //delete project created
    private By deleteProject = By.xpath("//a[@class='text-danger _delete']");

    //verify Project deleted success
    //Project deleted success message
    //search and check nodata
    private By noData = By.xpath("//td[@class='dataTables_empty']");

    //logout
    private By iconUser = By.xpath("//img[@class='img img-responsive staff-profile-image-small tw-ring-1 tw-ring-offset-2 tw-ring-primary-500 tw-mx-1 tw-mt-2.5']");
    private By logOutoption = By.xpath("//ul[@class='dropdown-menu animated fadeIn']//li[@class='header-logout']//a[@href='#'][normalize-space()='Logout']");


    public void verifyNavigateToProjectPage()
    {
        AssertHelper.assertTrue(WebUI.checkElementExist(titleProjectPage), "The ProjectPage title not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(titleProjectPage), "Projects Summary", "The ProjectPage title not match.");
    }

    public void clickButtonAddNewCustomer()
    {
        WebUI.clickElement(buttonNewProject);
    }

    public void moveSliderToMiddle()
    {
        WebDriver driver = DriverManager.getDriver();
        WebElement slider = driver.findElement(By.xpath("//span[@class='ui-slider-handle ui-corner-all ui-state-default']"));
        WebElement sliderBar = slider.findElement(By.xpath("./.."));
        int width = sliderBar.getSize().width;
        int xOffset = (int) (width * 0.5);
        Actions actions = new Actions(driver);
        actions.clickAndHold(slider)
                .moveByOffset(xOffset, 0)
                .release()
                .perform();
    }

    public void submitDataForNewCustomer(String projectName)
    {
        WebUI.setTextElement(inputprojectName, projectName);
        WebUI.clickElement(inputcustomer);
        WebUI.setTextElement(searchCustomer, "Bin Customer");
        WebUI.clickElement(selectCustomer);
        WebUI.clickElement(checkBoxCalculate);
        moveSliderToMiddle();
        WebUI.clickElement(saveProject);
    }

    public void verifyProjectCreated()
    {
        AssertHelper.assertTrue(WebUI.checkElementExist(alertsuccess), "The alertsuccess title not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(alertsuccess), "Project added successfully.", "The alertsuccess title not match.");
        AssertHelper.assertTrue(WebUI.checkElementExist(projectNameCustomer), "The project customer header page not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(projectNameCustomer), "Bin Project - Bin Customer", "The project customer header page not match.");
        AssertHelper.assertTrue(WebUI.checkElementExist(projectProgress), "The project progress not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(projectProgress), "Project Progress 50%", "The project progress not match.");
        AssertHelper.assertTrue(WebUI.checkElementExist(customer), "The customer title not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(customer), "Customer", "The customer title not match.");
        AssertHelper.assertTrue(WebUI.checkElementExist(projectNameCreated), "The project name not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(projectNameCreated), "Bin Customer", "The project progress not match.");
        AssertHelper.assertTrue(WebUI.checkElementExist(status), "The status title not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(status), "Status", "The status title not match.");
        AssertHelper.assertTrue(WebUI.checkElementExist(statusProject), "The statusProject title not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(statusProject), "In Progress", "The statusProject title not match.");
    }

    public void searchAndCheckCustomerInTable(String customerName)
    {
        WebUI.setTextElement(inputSearchCustomer, customerName);
        AssertHelper.assertEquals(WebUI.getTextElement(itemCustomerFirst), customerName, "The customer name in table not match");
    }

    public void clickFirstCustomer()
    {
        //WebUI.clickElement(customerID);
        //WebUI.clickElement(customerID);
        WebUI.clickElement(itemCustomerFirst);
    }

    public void verifyProjectCreatedOnProjectTab()
    {
        AssertHelper.assertTrue(WebUI.checkElementExist(projectNameOnProjectTab), "The Project Name not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(projectNameOnProjectTab), "Bin Project", "The Project Name not match.");
        AssertHelper.assertTrue(WebUI.checkElementExist(statusOnProjectTab), "The status not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(statusOnProjectTab), "In Progress", "The status not match.");
    }

    public void moveToProjectName()
    {
        WebUI.moveToElement(projectNameOnProjectTab);
    }

    public void clickAndDeleteProject()
    {
        WebUI.clickElement(deleteProject);
        WebUI.acceptAlert();
    }

    public void searchAndCheckProjectInTable(String projectName)
    {
        //clickmenuProjects();
        WebUI.setTextElement(inputSearchProject, projectName);
    }

    public void verifyNoDataAfterDeletedProject()
    {
        AssertHelper.assertTrue(WebUI.checkElementExist(noData), "The nodata text not display.");
        AssertHelper.assertEquals(WebUI.getTextElement(noData), "No matching records found", "The nodata text not match.");
    }

    public int getProjectInprogress()
    {
        String totalString = WebUI.getTextElement(inprogressProject);
        System.out.println("getProjectInprogress: " + totalString);
        return Integer.parseInt(totalString);
    }

    public void logOutCRM()
    {
        WebUI.clickElement(iconUser);
        WebUI.clickElement(logOutoption);
    }

}
