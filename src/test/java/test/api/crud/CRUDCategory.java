package test.api.crud;

import settings.globals.EndPointGlobal;
import settings.helpers.JsonHelper;
import settings.keywords.ApiKeyword;
import test.api.common.BaseTest;
import test.api.common.VerifyDataCategoryBody;
import test.api.listeners.TestListener;
import test.api.model.CategoryPOJO_Request_Lombok;
import test.api.model.data.CategoryPOJO_Lombok_Builder;
import settings.utils.LogUtils;
import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;

@Listeners(TestListener.class)
public class CRUDCategory extends BaseTest {

    private CategoryPOJO_Request_Lombok category;
    String dataFile = "src/test/resources/testdata/CreateCategory.json";

    @Severity(SeverityLevel.CRITICAL)
    @Epic("Regression Test")
    @Feature("Category Test")
    @Story("Test Add New Category")
    @Description("Add new Category")
    @Link("https://jira.com/anhtester/apitest/user/10")
    @Test(priority = 1)
    public void testAddNewCategory() {

        category = CategoryPOJO_Lombok_Builder.getDataToCreateCategory();

        JsonHelper.updateValueJsonFile(dataFile, category);

        Response response = ApiKeyword.post(EndPointGlobal.EP_CATEGORY, new File(dataFile));

        VerifyDataCategoryBody.verifyResponseSuccess(response, category, 200);

    }

    @Test(priority = 2)
    public void getCategory(){
        LogUtils.info("USER_NAME: " + category.getName());

        Response response = ApiKeyword.get(EndPointGlobal.EP_CATEGORY + "/" + category.getId());

        VerifyDataCategoryBody.verifyResponseSuccess(response, category, 200);

    }

    @Test(priority = 3)
    public void putCategory() throws Exception {

        category = CategoryPOJO_Lombok_Builder.createUpdatedCategory(category);

        JsonHelper.updateValueJsonFile(dataFile, category);

        String jsonBody = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(dataFile)));

        Response response = ApiKeyword.put(EndPointGlobal.EP_CATEGORY + "/" + category.getId(), jsonBody);

        VerifyDataCategoryBody.verifyResponseSuccess(response, category, 200);

    }

    @Test(priority = 4)
    public void getCategoryAfterPut(){
        LogUtils.info("USER_NAME: " + category.getName());

        Response response = ApiKeyword.get(EndPointGlobal.EP_CATEGORY + "/" + category.getId());

        VerifyDataCategoryBody.verifyResponseSuccess(response, category, 200);

    }

    @Test(priority = 5)
    public void deleteCategory(){

        Response response = ApiKeyword.delete(EndPointGlobal.EP_CATEGORY + "/" + category.getId());

        VerifyDataCategoryBody.verifyResponseSuccess(response, category, 200);

    }

    @Test(priority = 6)
    public void getAfterDeleteCategory() {

        Response response = ApiKeyword.get(EndPointGlobal.EP_CATEGORY + "/" + category.getId());

        JsonPath jsonPath = response.jsonPath();

        ApiKeyword.verifyStatusCode(response, 400);

        Assert.assertEquals(jsonPath.getString("message"), "Not found", "Message not match.");
        Assert.assertEquals(jsonPath.getString( "errors"), "No categogy found with the submitted id", "Error detail not match.");
        LogUtils.info("message: " + jsonPath.getString("message"));
        LogUtils.info("errors: " + jsonPath.getString("errors"));

    }


}