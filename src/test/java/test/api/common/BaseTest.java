package test.api.common;

import settings.globals.EndPointGlobal;
import settings.globals.TokenGlobal;
import settings.keywords.ApiKeyword;
import test.api.model.LoginPOJO;
import test.api.model.data.LoginPOJO_Builder;
import settings.reports.AllureManager;
import settings.utils.LogUtils;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    @BeforeTest
    public void loginUser() {

        LoginPOJO loginPOJO = LoginPOJO_Builder.getDataLogin();
        Gson gson = new Gson();

        Response response = ApiKeyword.postNotAuth(EndPointGlobal.EP_LOGIN, gson.toJson(loginPOJO));

        //response.prettyPrint();

        response.then().statusCode(200);

        TokenGlobal.TOKEN = response.getBody().path("token");
        LogUtils.info("Token Global: " + TokenGlobal.TOKEN);
        AllureManager.saveTextLog("Token Global: " + TokenGlobal.TOKEN);
    }
}
