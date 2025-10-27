package test.ui.crmpages;

import settings.drivers.DriverManager;
import settings.helpers.AssertHelper;
import settings.helpers.SystemHelper;
import settings.helpers.TableHelper;
import settings.keywords.WebUI;
import org.openqa.selenium.By;

public class CustomerPage extends BasePage {

        private By menuCustomer = By.xpath("//span[normalize-space()='Customers']");
        private By searchAll = By.xpath("//input[@id='search_input']");
        private By headerCustomerPage = By.xpath("//span[normalize-space()='Customers Summary']");
        private By buttonAddNewCustomer = By.xpath("//a[normalize-space()='New Customer']");
        private By buttonImportCustomer = By.xpath("//a[normalize-space()='Import Customers']");
        private By inputSearchCustomer = By.xpath("//input[@class='form-control input-sm']");
        private By inputCompany = By.xpath("//input[@id='company']");
        private By inputVATNumber = By.xpath("//input[@id='vat']");
        private By inputPhoneNumber = By.xpath("//input[@id='phonenumber']");
        private By inputWebsite = By.xpath("//input[@id='website']");
        private By dropDownGroups = By.xpath("//button[@data-id='groups_in[]']");
        private By searchGroups = By.xpath("//input[@aria-controls='bs-select-1']");
        private By optionVIP = By.xpath("//a[normalize-space()='VIP']");
        private By currencyDropdown = By.xpath("//button[@aria-owns='bs-select-2']");
        private By optionEuro = By.xpath("//small[contains(text(),'€')]");
        private By defaultLanguageDropdown = By.xpath("//button[@aria-owns='bs-select-3']");
        private By optionVietnamese = By.xpath("//span[normalize-space()='Vietnamese']");
        private By inputAddress = By.xpath("//textarea[@id='address']");
        private By inputCity = By.xpath("//input[@id='city']");
        private By inputState = By.xpath("//input[@id='state']");
        private By inputZipCode = By.xpath("//input[@id='zip']");
        private By countryDropdown = By.xpath("//button[@aria-owns='bs-select-4']");
        private By searchCountry = By.xpath("//input[@aria-controls='bs-select-4']");
        private By optionVietnam = By.xpath("//span[normalize-space()='Vietnam']");
        private By buttonSave = By.xpath("//button[@class='btn btn-primary only-save customer-form-submiter']");
        private By dataInTable = By.xpath("//a[normalize-space()='Nashtech Company']");
        private By totalCustomer = By.xpath("//span[normalize-space()='Total Customers']/preceding-sibling::span");
        private By buttonDelete = By.xpath("//a[normalize-space()='Delete']");
        private By iconUser = By.xpath("//img[@class='img img-responsive staff-profile-image-small tw-ring-1 tw-ring-offset-2 tw-ring-primary-500 tw-mx-1 tw-mt-2.5']");
        private By buttonLogout = By.xpath("//a[@href='#' and normalize-space()='Logout']");
        private By buttonChooseFile = By.xpath("//input[@type='file']");
        private By buttonImport = By.xpath("//button[normalize-space()='Import']");
        String filePath = SystemHelper.getCurrentDir() + "src/test/resources/testdata/sample_import_file.csv";
        private By customerNameInTable = By.xpath("//tbody/tr[1]/td[3]/a[1]");

        public void clickbuttonAddNewCustomer() {
            WebUI.waitForElementVisible(buttonAddNewCustomer);
            WebUI.clickElement(buttonAddNewCustomer);
        }

        public void clickbuttonImportCustomer() {
            WebUI.waitForElementVisible(buttonImportCustomer);
            WebUI.clickElement(buttonImportCustomer);
        }

        public void setTextForUploadFile() {
            WebUI.setTextElement(buttonChooseFile, filePath);
        }

        public void clickbuttonImport() {
            WebUI.setTextElement(buttonChooseFile, filePath);
        }

        public void searchAndCheckDataInTable(int column, String customerName, String columnName)
        {
            WebUI.setTextElement(inputSearchCustomer,customerName);
            WebUI.waitForElementVisible(customerNameInTable);
            WebUI.waitForPageLoaded();
            TableHelper.checkDataInTableByColumn_Contains(3, customerName, "Company");
        }

        public void addNewCustomer(String customerName)
        {
            WebUI.setTextElement(inputCompany, "Nashtech Company");
            WebUI.setTextElement(inputVATNumber, "123456789");
            WebUI.setTextElement(inputPhoneNumber, "0123456789");
            WebUI.setTextElement(inputWebsite, "https://nashtech.com.vn");
            WebUI.clickElement(dropDownGroups);
            WebUI.setTextElement(searchGroups, "VIP");
            WebUI.clickElement(optionVIP);
            WebUI.clickElement(dropDownGroups);
            WebUI.clickElement(currencyDropdown);
            WebUI.clickElement(optionEuro);
            WebUI.clickElement(defaultLanguageDropdown);
            WebUI.clickElement(optionVietnamese);
            WebUI.setTextElement(inputAddress, "123 Street, Hanoi");
            WebUI.setTextElement(inputCity, "Hanoi");
            WebUI.setTextElement(inputState, "Hoan Kiem");
            WebUI.setTextElement(inputZipCode, "100000");
            WebUI.clickElement(countryDropdown);
            WebUI.setTextElement(searchCountry, "Vietnam");
            WebUI.clickElement(optionVietnam);
            WebUI.clickElement(buttonSave);
        }

        public void verifyCustomerAdded()
        {
            //WebUI1.waitForElementVisible(inputCompany);
            AssertHelper.assertTrue(WebUI.checkElementDisplayed(inputCompany), "Company field not displayed");
            AssertHelper.assertEquals(WebUI.getAttributeElement(inputCompany, "value"), "Nashtech Company", "Company name not matched");
            AssertHelper.assertTrue(WebUI.checkElementDisplayed(inputVATNumber), "VAT number field not displayed");
            AssertHelper.assertEquals(WebUI.getAttributeElement(inputVATNumber, "value"), "123456789", "VAT number not matched");
            AssertHelper.assertTrue(WebUI.checkElementDisplayed(inputPhoneNumber), "Phone Number field not displayed");
            AssertHelper.assertEquals(WebUI.getAttributeElement(inputPhoneNumber, "value"), "0123456789", "PhoneNumber not matched");
            AssertHelper.assertTrue(WebUI.checkElementDisplayed(inputWebsite), "Website field not displayed");
            AssertHelper.assertEquals(WebUI.getAttributeElement(inputWebsite, "value"), "https://nashtech.com.vn", "Website not matched");
            AssertHelper.assertTrue(WebUI.checkElementDisplayed(dropDownGroups), "Groups field not displayed");
            AssertHelper.assertEquals(WebUI.getTextElement(dropDownGroups), "VIP", "Groups not matched");
            AssertHelper.assertTrue(WebUI.checkElementDisplayed(currencyDropdown), "Currency field not displayed");
            AssertHelper.assertEquals(WebUI.getTextElement(currencyDropdown), "EUR", "Currency not matched");
            AssertHelper.assertTrue(WebUI.checkElementDisplayed(defaultLanguageDropdown), "Default Language field not displayed");
            AssertHelper.assertEquals(WebUI.getTextElement(defaultLanguageDropdown), "Vietnamese", "Default Language not matched");
            AssertHelper.assertTrue(WebUI.checkElementDisplayed(inputAddress), "Address field not displayed");
            AssertHelper.assertEquals(WebUI.getAttributeElement(inputAddress, "value"), "123 Street, Hanoi", "Address not matched");
            AssertHelper.assertTrue(WebUI.checkElementDisplayed(inputCity), "City field not displayed");
            AssertHelper.assertEquals(WebUI.getAttributeElement(inputCity, "value"), "Hanoi", "City name not matched");
            AssertHelper.assertTrue(WebUI.checkElementDisplayed(inputState), "State field not displayed");
            AssertHelper.assertEquals(WebUI.getAttributeElement(inputState, "value"), "Hoan Kiem", "State name not matched");
            AssertHelper.assertTrue(WebUI.checkElementDisplayed(inputZipCode), "Zip code field not displayed");
            AssertHelper.assertEquals(WebUI.getAttributeElement(inputZipCode, "value"), "100000", "Zip code not matched");
            AssertHelper.assertTrue(WebUI.checkElementDisplayed(countryDropdown), "Country field not displayed");
            AssertHelper.assertEquals(WebUI.getTextElement(countryDropdown), "Vietnam", "Country name not matched");
        }

        public int getTotalCustomers()
        {
            String totalString = DriverManager.getDriver().findElement(totalCustomer).getText();
            System.out.println("getCustomerTotal: " + totalString);
            return Integer.parseInt(totalString);
        }

        public void searchCustomer(String customerName)
        {
            WebUI.clickElement(menuCustomer);
            WebUI.setTextElement(inputSearchCustomer,customerName);
            WebUI.waitForElementVisible(dataInTable);
            AssertHelper.assertTrue(WebUI.checkElementDisplayed(dataInTable), "Customer not found: " + customerName);
        }

        public void searchDataCustomer(String data)
        {
            WebUI.waitForPageLoaded();
            WebUI.setTextElement(inputSearchCustomer,data);
        }

        public void deleteCustomer(String CustomerName)
        {
            WebUI.moveToElement(dataInTable);
            WebUI.clickElement(buttonDelete);
            WebUI.acceptAlert();
        }

        public void logOut()
        {
            WebUI.clickElement(iconUser);
            WebUI.clickElement(buttonLogout);
        }



}
