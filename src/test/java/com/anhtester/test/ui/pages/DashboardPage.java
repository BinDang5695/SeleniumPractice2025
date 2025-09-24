package com.anhtester.test.ui.pages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;

public class DashboardPage extends BasePage {

        private By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
        private By iconUserProfile = By.xpath("//li[@class='icon header-user-profile']");
        private By invoicesAwaitingPayment = By.xpath("//span[normalize-space()='Invoices Awaiting Payment']");
        private By convertedLeads = By.xpath("//span[normalize-space()='Converted Leads']");
        private By projectsInProgress = By.xpath("//span[normalize-space()='Projects In Progress']");
        private By totalInvoicesAwaitingPayment = By.xpath("(//span[normalize-space()='Invoices Awaiting Payment']/parent::div)/following-sibling::span");
        private By totalConvertedLeads= By.xpath("(//span[normalize-space()='Converted Leads']/parent::div)/following-sibling::span");
        private By totalProjectsInProgress = By.xpath("(//span[normalize-space()='Projects In Progress']/parent::div)/following-sibling::span");
        private By totalTasksNotFinished = By.xpath("(//span[normalize-space()='Tasks Not Finished']/parent::div)/following-sibling::span");
        private By dashboardOptions = By.xpath("//div[@class='screen-options-btn']");
        private By allCheckboxDashboardOptions = By.xpath("//div[@id='dashboard-options']/div[contains(@class,'checkbox')]");

        public void verifyDashboardPage(String text1, String text2)
        {
            WebUI.waitForPageLoaded();
            WebUI.assertTrue(WebUI.checkElementDisplayed(invoicesAwaitingPayment), "invoicesAwaitingPayment not display");
            WebUI.assertEquals(WebUI.getTextElement(invoicesAwaitingPayment), text1, "invoicesAwaitingPayment text not match");
            WebUI.assertTrue(WebUI.checkElementDisplayed(totalInvoicesAwaitingPayment), "totalInvoicesAwaitingPayment not display");
            WebUI.assertEquals(WebUI.getTextElement(totalInvoicesAwaitingPayment), text2, "totalInvoicesAwaitingPayment text not match");
        }




}
