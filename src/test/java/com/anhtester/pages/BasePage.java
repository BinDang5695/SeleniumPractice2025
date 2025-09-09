package com.anhtester.pages;

import com.anhtester.helpers.ExcelHelper;
import com.anhtester.keywords.WebUI;
import com.anhtester.pixabaypages.*;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private LoginPixabayPage loginPixabayPage;
    private HomePage homePage;
    private ProfilePage profilePage;
    private ImageDetailsPage imageDetailsPage;
    private SettingsPage settingsPage;
    private MyMediaPage myMediaPage;
    private LibraryPage libraryPage;

    private By pageMyProfile = By.xpath("//div[@class='userMenu--BWkSG']//a[1]");
    private By pageMyMedia = By.xpath("//div[@class='userMenu--BWkSG']//a[2]");
    private By pageUpload = By.xpath("//div[@class='userMenu--BWkSG']//a[3]");
    private By pageStatistics = By.xpath("//div[@class='userMenu--BWkSG']//a[4]");
    private By pageLibrary = By.xpath("//div[@class='userMenu--BWkSG']//a[5]");
    private By pageFollowing = By.xpath("//div[@class='userMenu--BWkSG']//a[6]");
    private By pageMessages = By.xpath("//div[@class='userMenu--BWkSG']//a[7]");
    private By pageSettings = By.xpath("//div[@class='userMenu--BWkSG']//a[8]");


    public LoginPixabayPage loginPixabayPage() {
        if (loginPixabayPage == null) {
            loginPixabayPage = new LoginPixabayPage();
        }
        return loginPixabayPage;
    }

    public ProfilePage profilePage() {
        if (profilePage == null) {
            profilePage = new ProfilePage();
        }
        return profilePage;
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

    public MyMediaPage myMediaPage() {
        if (myMediaPage == null) {
            myMediaPage = new MyMediaPage();
        }
        return myMediaPage;
    }

    public LibraryPage libraryPage() {
        if (libraryPage == null) {
            libraryPage = new LibraryPage();
        }
        return libraryPage;
    }



}
