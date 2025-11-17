package test.ui.crmtestcases;

import test.ui.common.BaseTest;
import test.ui.dataproviders.DataProviderFactory;
import io.qameta.allure.*;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Epic("Regression Test")
    @Feature("DMS")
    @Story("Login")
    @Owner("Bin Tester")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Jira", url = "https://anhtester.atlassian.net/browse/CRM-1")
    @Issue("CRM-1")
    @Description("Login with valid credentials")
    @Test(dataProvider = "data_provider_login_success", dataProviderClass = DataProviderFactory.class)
    public void loginSuccess(String email, String password) {
        loginPage().loginCRM(email, password);
        loginPage().verifyLoginSuccess();
    }

    @Epic("Regression Test")
    @Feature("DMS")
    @Story("Login")
    @Owner("Bin Tester")
    @Severity(SeverityLevel.NORMAL)
    @Link(name = "Jira", url = "https://anhtester.atlassian.net/browse/CRM-2")
    @Issue("CRM-2")
    @Description("Login with invalid credentials")
    @Test(dataProvider = "data_provider_login_fail_invalid_email", dataProviderClass = DataProviderFactory.class)
    public void loginFailWithInvalidEmail(String email, String password) {
        loginPage().loginCRM(email, password);
        loginPage().verifyLoginFail("Invalid email or password");
    }

    @Epic("Regression Test")
    @Feature("DMS")
    @Story("Login")
    @Owner("Bin Tester")
    @Severity(SeverityLevel.NORMAL)
    @Link(name = "Jira", url = "https://anhtester.atlassian.net/browse/CRM-3")
    @Issue("CRM-3")
    @Description("Login with invalid credentials")
    @Test(dataProvider = "data_provider_login_fail_invalid_password", dataProviderClass = DataProviderFactory.class)
    public void loginFailWithInvalidPassword(String email, String password) {
        loginPage().loginCRM(email, password);
        loginPage().verifyLoginFail("Invalid email or password");
    }

}
