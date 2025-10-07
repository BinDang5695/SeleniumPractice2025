package test.ui.dataproviders;

import org.testng.annotations.DataProvider;

public class DataProviderFactory {

    @DataProvider(name = "data_provider_login_success")
    public Object[][] dataProviderLoginSuccess() {
        return new Object[][]{
                {"admin@example.com", "123456"},
                //{"admin@example.com", "123456"},
                //{"admin@example.com", "123456"},
        };
    }

    @DataProvider(name = "data_provider_login_fail_invalid_email")
    public Object[][] dataProviderLoginFailWithInvalidEmail() {
        return new Object[][]{
                {"admin123@example.com", "123456"},
        };
    }

    @DataProvider(name = "data_provider_login_fail_invalid_password")
    public Object[][] dataProviderLoginFailWithInvalidPassword() {
        return new Object[][]{
                {"admin@example.com", "123456789"},
        };
    }



}
