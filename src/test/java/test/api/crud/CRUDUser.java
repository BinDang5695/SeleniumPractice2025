package test.api.crud;

import settings.globals.EndPointGlobal;
import settings.helpers.JsonHelper;
import settings.keywords.ApiKeyword;
import test.api.common.BaseTest;
import test.api.common.VerifyDataUserBody;
import test.api.listeners.TestListener;
import test.api.model.RegisterUserPOJO_Lombok;
import test.api.model.data.UserPOJO_Lombok_Builder;
import settings.utils.LogUtils;
import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.File;

@Listeners(TestListener.class)
public class CRUDUser extends BaseTest {

    private RegisterUserPOJO_Lombok user;
    String dataFile = "src/test/resources/testdata/CreateUser.json";

    @Severity(SeverityLevel.CRITICAL)
    @Epic("Regression Test")
    @Feature("User Test")
    @Story("Test Add New User")
    @Description("Add new User")
    @Link("https://jira.com/anhtester/apitest/user/10")
    @Test(priority = 1)
    public void testAddNewUser() {

        user = UserPOJO_Lombok_Builder.getDataToCreateUser();

        JsonHelper.updateValueJsonFile(dataFile, user);

        Response response = ApiKeyword.post(EndPointGlobal.EP_USER, new File(dataFile));

        VerifyDataUserBody.verifyResponseSuccess(response, user, 200);

    }

    @Test(priority = 2)
    public void getUser(){
        LogUtils.info("USER_NAME: " + user.getUsername());

        Response response = ApiKeyword.get(EndPointGlobal.EP_USER, "username", new File(dataFile));

        VerifyDataUserBody.verifyResponseSuccess(response, user, 200);

    }

    @Test(priority = 3)
    public void putUser() throws Exception {

        user = UserPOJO_Lombok_Builder.createUpdatedUser(user);

        JsonHelper.updateValueJsonFile(dataFile, user);

        String jsonBody = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(dataFile)));

        Response response = ApiKeyword.put(EndPointGlobal.EP_USER + "/" + user.getId(), jsonBody);

        VerifyDataUserBody.verifyResponseSuccess(response, user, 200);

    }

    @Test(priority = 4)
    public void getUserAfterPut(){
        LogUtils.info("USER_NAME: " + user.getUsername());

        Response response = ApiKeyword.get(EndPointGlobal.EP_USER, "username", new File(dataFile));

        VerifyDataUserBody.verifyResponseSuccess(response, user, 200);

    }

    @Test(priority = 5)
    public void deleteUser(){

        Response response = ApiKeyword.delete(EndPointGlobal.EP_USER, "username", new File(dataFile));

        VerifyDataUserBody.verifyResponseSuccess(response, user, 200);

    }

    @Test(priority = 6)
    public void getAfterDeleteUser() {

        Response response = ApiKeyword.get(EndPointGlobal.EP_USER, "username", new File(dataFile));

        JsonPath jsonPath = response.jsonPath();

        ApiKeyword.verifyStatusCode(response, 400);

        Assert.assertEquals(jsonPath.getString("message"), "Not found", "Message not match.");
        Assert.assertEquals(jsonPath.getString( "errors"), "No user found with the submitted id", "Error detail not match.");
        LogUtils.info("message: " + jsonPath.getString("message"));
        LogUtils.info("errors: " + jsonPath.getString("errors"));

    }


}