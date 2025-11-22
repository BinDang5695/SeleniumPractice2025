package test.ui.common;

import settings.helpers.ExcelHelper;
import settings.keywords.WebUI;
import org.openqa.selenium.By;
import test.ui.crmpages.*;
import test.ui.pexelspages.*;
import test.ui.pixabaypages.*;

public class BasePage {

    private BasePage basePage;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private CustomerPage customerPage;
    private ContactsPage contactsPage;
    private ProjectPage projectPage;
    private TaskPage taskPage;
    private HeaderPage headerPage;
    private ContractsPage contractsPage;
    private ExpensesPage expensesPage;
    private LeadsPage leadsPage;
    private KnowledgeBasePage knowledgeBase;
    private SalesPage salesPage;
    private ProposalsPage proposalsPage;
    private ExcelHelper excelHelper;

    private By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    private By menuCustomers = By.xpath("//span[normalize-space()='Customers']");
    private By menuProjects = By.xpath("//span[normalize-space()='Projects']");
    private By menuTasks = By.xpath("//span[normalize-space()='Tasks']");
    private By menuContracts = By.xpath("//span[normalize-space()='Contracts']");
    private By menuExpenses = By.xpath("//span[@class='menu-text'][normalize-space()='Expenses']");
    private By menuLeads = By.xpath("//span[@class='menu-text'][normalize-space()='Leads']");
    private By menuKnowledgeBase = By.xpath("//span[normalize-space()='Knowledge Base']");
    private By menuSales = By.xpath("//span[@class='menu-text'][normalize-space()='Sales']");
    private By menuProposals = By.xpath("//span[normalize-space()='Proposals']");
    private By menuInvoices = By.xpath("//span[normalize-space()='Invoices']");
    private By tabProjects = By.xpath("//a[@data-group='projects']");
    private By tabContacts = By.xpath("//a[normalize-space()='Contacts']");

    public BasePage basePage() {
        if (basePage == null) {
            basePage = new BasePage();
        }
        return basePage;
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

    public HeaderPage headerPage () {
        if (headerPage == null) {
            headerPage = new HeaderPage();
        }
        return headerPage;
    }

    public ContractsPage contractsPage () {
        if (contractsPage == null) {
            contractsPage = new ContractsPage();
        }
        return contractsPage;
    }

    public ExpensesPage expensesPage () {
        if (expensesPage == null) {
            expensesPage = new ExpensesPage();
        }
        return expensesPage;
    }

    public LeadsPage leadsPage () {
        if (leadsPage == null) {
            leadsPage = new LeadsPage();
        }
        return leadsPage;
    }

    public KnowledgeBasePage knowledgeBase () {
        if (knowledgeBase == null) {
            knowledgeBase = new KnowledgeBasePage();
        }
        return knowledgeBase;
    }

    public ProposalsPage proposalsPage () {
        if (proposalsPage == null) {
            proposalsPage = new ProposalsPage();
        }
        return proposalsPage;
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

    public ContractsPage clickMenuContracts() {
        WebUI.clickElement(menuContracts);
        return new ContractsPage();
    }

    public TaskPage clickMenuTasks() {
        WebUI.clickElement(menuTasks);
        return new TaskPage();
    }

    public ExpensesPage clickMenuExpenses() {
        WebUI.clickElement(menuExpenses);
        return new ExpensesPage();
    }

    public LeadsPage clickMenuLeads() {
        WebUI.clickElement(menuLeads);
        return new LeadsPage();
    }

    public KnowledgeBasePage clickMenuKnowledgeBase() {
        WebUI.clickElement(menuKnowledgeBase);
        return new KnowledgeBasePage();
    }

    public SalesPage clickMenuSalesPage() {
        WebUI.clickElement(menuSales);
        return new SalesPage();
    }

    public ProposalsPage clickMenuProposalsPage() {
        WebUI.clickElement(menuProposals);
        return new ProposalsPage();
    }

    // Pixabay

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

    // CMS

    private LoginPexelsPage loginPexelsPage;
    private ImagePage imagePage;
    private ProfilePage profilePage;
    private CollectionsPage collectionsPage;

    public LoginPexelsPage loginPexelsPage() {
        if (loginPexelsPage == null) {
            loginPexelsPage = new LoginPexelsPage();
        }
        return loginPexelsPage;
    }

    public ImagePage imagePage() {
        if (imagePage == null) {
            imagePage = new ImagePage();
        }
        return imagePage;
    }

    public ProfilePage profilePage() {
        if (profilePage == null) {
            profilePage = new ProfilePage();
        }
        return profilePage;
    }

    public CollectionsPage collectionsPage() {
        if (collectionsPage == null) {
            collectionsPage = new CollectionsPage();
        }
        return collectionsPage;
    }






}
