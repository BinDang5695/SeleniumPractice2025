package test.ui.crmtestcases;

import test.ui.common.BaseTest;
import org.testng.annotations.Test;

public class LoginTestByExcelFile extends BaseTest {
    
    @Test
    public void loginSuccess()
    {
        excelHelper().setExcelFile("src/test/resources/testdata/CRM_LoginTest2.xlsx", "LoginDataProvider");
        loginPage().loginCRM(excelHelper().getCellData("Email", 1),
                excelHelper().getCellData("Password", 1));
        loginPage().verifyLoginSuccess();
    }

    @Test
    public void loginFailWithInvalidEmail()
    {
        excelHelper().setExcelFile("src/test/resources/testdata/CRM_LoginTest2.xlsx", "LoginDataProvider");
        loginPage().loginCRM(excelHelper().getCellData("Email", 2),
                excelHelper().getCellData("Password", 2));
        loginPage().verifyLoginFail("Invalid email or password");
    }

    @Test
    public void loginFailWithInvalidPassword()
    {
        excelHelper().setExcelFile("src/test/resources/testdata/CRM_LoginTest2.xlsx", "LoginDataProvider");
        loginPage().loginCRM(excelHelper().getCellData("Email", 3),
                excelHelper().getCellData("Password", 3));
        loginPage().verifyLoginFail("Invalid email or password");
    }

    @Test
    public void loginFailWithEmptyEmail()
    {
        excelHelper().setExcelFile("src/test/resources/testdata/CRM_LoginTest2.xlsx", "LoginDataProvider");
        loginPage().loginCRM(excelHelper().getCellData("Email", 4),
                excelHelper().getCellData("Password", 4));
        loginPage().verifyLoginFail("The Email Address field is required.");
    }

    @Test
    public void loginFailWithEmptyPassword() {
        excelHelper().setExcelFile("src/test/resources/testdata/CRM_LoginTest2.xlsx", "LoginDataProvider");
        loginPage().loginCRM(excelHelper().getCellData("Email", 5),
                excelHelper().getCellData("Password", 5));
        loginPage().verifyLoginFail("The Password field is required.");
    }
}
