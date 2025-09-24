package com.anhtester.test.api.bai18_allurereport;

import com.anhtester.globals.EndPointGlobal;
import com.anhtester.helpers.JsonHelper;
import com.anhtester.helpers.SystemHelper;
import com.anhtester.keywords.ApiKeyword;
import com.anhtester.test.api.common.BaseTest;
import com.anhtester.test.api.common.VerifyDataCategoryBody;
import com.anhtester.test.api.common.VerifyDataImageBody;
import com.anhtester.test.api.common.VerifyDataUserBody;
import com.anhtester.test.api.listeners.TestListener;
import com.anhtester.test.api.model.ImagePOJO_Request_Lombok;
import com.anhtester.test.api.model.ImagePOJO_Response_Lombok;
import com.anhtester.test.api.model.data.CategoryPOJO_Lombok_Builder;
import com.anhtester.test.api.model.data.ImagePOJO_Lombok_Builder;
import com.anhtester.test.api.model.data.UserPOJO_Lombok_Builder;
import com.anhtester.utils.LogUtils;
import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;

@Listeners(TestListener.class)
public class CRUDImage extends BaseTest {

    private ImagePOJO_Response_Lombok imageresponse = new ImagePOJO_Response_Lombok();

    @Severity(SeverityLevel.CRITICAL)
    @Epic("Regression Test")
    @Feature("Image Test")
    @Story("Test Add New Image")
    @Description("Add new Image")
    @Link("https://jira.com/anhtester/apitest/user/10")
    @Test(priority = 1)
    public void testAddNewImage() {

        File file = ImagePOJO_Lombok_Builder.getImageFile("UK.jpg");

        Response response = ApiKeyword.post(EndPointGlobal.EP_IMAGE, "image", file);

        VerifyDataImageBody.verifyResponseSuccess(response, imageresponse, 200);

        LogUtils.info("Upload file: " + file.getName() + " | Size: " + file.length() + " bytes");

    }

    @Test(priority = 2)
    public void getImage(){
        LogUtils.info("Id: " + imageresponse.getId());

        Response response = ApiKeyword.get(EndPointGlobal.EP_IMAGE + "/" + imageresponse.getId());

        VerifyDataImageBody.verifyResponseSuccess(response, imageresponse, 200);

    }

    @Test(priority = 3)
    public void putImage() {

        File file = ImagePOJO_Lombok_Builder.getImageFile("UpdateImage.jpg");

        Response response = ApiKeyword.post(EndPointGlobal.EP_IMAGE + "/" + imageresponse.getId(), "image", file);

        VerifyDataImageBody.verifyResponseSuccess(response, imageresponse, 200);

        LogUtils.info("Upload file: " + file.getName() + " | Size: " + file.length() + " bytes");

    }

    @Test(priority = 4)
    public void getImageAfterPut(){

        LogUtils.info("Id: " + imageresponse.getId());

        Response response = ApiKeyword.get(EndPointGlobal.EP_IMAGE + "/" + imageresponse.getId());

        VerifyDataImageBody.verifyResponseSuccess(response, imageresponse, 200);

    }

    @Test(priority = 5)
    public void deleteImage(){

        Response response = ApiKeyword.delete(EndPointGlobal.EP_IMAGE + "/" + imageresponse.getId());

        VerifyDataImageBody.verifyResponseSuccess(response, imageresponse, 200);

    }

    @Test(priority = 6)
    public void getAfterDeleteImage() {

        Response response = ApiKeyword.get(EndPointGlobal.EP_IMAGE + "/" + imageresponse.getId());

        JsonPath jsonPath = response.jsonPath();

        ApiKeyword.verifyStatusCode(response, 400);

        Assert.assertEquals(jsonPath.getString("message"), "Not found", "Message not match.");
        Assert.assertEquals(jsonPath.getString( "errors"), "No image found with the submitted id", "Error detail not match.");
        LogUtils.info("message: " + jsonPath.getString("message"));
        LogUtils.info("errors: " + jsonPath.getString("errors"));

    }


}