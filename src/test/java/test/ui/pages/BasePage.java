package test.ui.pages;

import settings.helpers.ExcelHelper;
import settings.keywords.WebUI;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.ui.pixabaypages.*;

public class BasePage {

    private static final Logger log = LoggerFactory.getLogger(BasePage.class);
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private CustomerPage customerPage;
    private ContactsPage contactsPage;
    private ProjectPage projectPage;
    private TaskPage taskPage;
    private ExcelHelper excelHelper;

    private By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    private By menuCustomers = By.xpath("//span[normalize-space()='Customers']");
    private By menuProjects = By.xpath("//span[normalize-space()='Projects']");
    private By menuTasks = By.xpath("//span[normalize-space()='Tasks']");

    // Tab
    private By tabProjects = By.xpath("//a[@data-group='projects']");
    private By tabContacts = By.xpath("//a[normalize-space()='Contacts']");

    public BasePage() {
    }

    public ExcelHelper excelHelper() {
        if (excelHelper == null) {
            excelHelper = new ExcelHelper();
        }
        return excelHelper;
    }

    public LoginPage loginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public DashboardPage dashboardPage() {
        if (dashboardPage == null) {
            dashboardPage = new DashboardPage();
        }
        return dashboardPage;
    }

    public CustomerPage customerPage() {
        if (customerPage == null) {
            customerPage = new CustomerPage();
        }
        return customerPage;
    }

    public ContactsPage contactsPage() {
        if (contactsPage == null) {
            contactsPage = new ContactsPage();
        }
        return contactsPage;
    }

    public ProjectPage projectPage() {
        if (projectPage == null) {
            projectPage = new ProjectPage();
        }
        return projectPage;
    }

    public TaskPage taskPage () {
        if (taskPage == null) {
            taskPage = new TaskPage();
        }
        return taskPage;
    }

    public DashboardPage clickMenuDashboard() {
        WebUI.clickElement(menuDashboard);
        return new DashboardPage();
    }

    public CustomerPage clickMenuCustomers() {
        WebUI.clickElement(menuCustomers);
        return new CustomerPage();
    }

    public ProjectPage clickMenuProjects() {
        WebUI.clickElement(menuProjects);
        return new ProjectPage();
    }

    public ProjectPage clickTabProjects() {
        WebUI.clickElement(tabProjects);
        return new ProjectPage();
    }

    public ContactsPage clickTabContacts() {
        WebUI.clickElement(tabContacts);
        return new ContactsPage();
    }

    public TaskPage clickMenuTasks() {
        WebUI.clickElement(menuTasks);
        return new TaskPage();
    }

    // PexelsTest

    private LoginPixabay loginPixabay;
    private HomePage homePage;
    private ImageDetailsPage imageDetailsPage;
    private SettingsPage settingsPage;
    private LibraryPage libraryPage;

    public LoginPixabay loginPixabay() {
        if (loginPixabay == null) {
            loginPixabay = new LoginPixabay();
        }
        return loginPixabay;
    }

    public HomePage homePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public ImageDetailsPage imageDetailsPage() {
        if (imageDetailsPage == null) {
            imageDetailsPage = new ImageDetailsPage();
        }
        return imageDetailsPage;
    }

    public SettingsPage settingsPage() {
        if (settingsPage == null) {
            settingsPage = new SettingsPage();
        }
        return settingsPage;
    }

    public LibraryPage libraryPage() {
        if (libraryPage == null) {
            libraryPage = new LibraryPage();
        }
        return libraryPage;
    }






}
