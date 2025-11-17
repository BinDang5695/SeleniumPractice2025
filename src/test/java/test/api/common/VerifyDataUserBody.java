package test.api.common;

import test.api.model.RegisterUserPOJO_Lombok;
import settings.utils.LogUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

public class VerifyDataUserBody {

    public static void verifyDataBodyUser(Response response, RegisterUserPOJO_Lombok user) {

        JsonPath jsonPath = response.jsonPath();

        int actualId = jsonPath.getInt("response.id");
        user.setId(actualId);
        Assert.assertTrue(actualId > 0, "ID should be greater than 0.");

        Assert.assertEquals(jsonPath.getString("message"), "Success", "Message not match.");
        Assert.assertEquals(jsonPath.getString("response.username"), user.getUsername(), "Username not match.");
        Assert.assertEquals(jsonPath.getString("response.firstName"), user.getFirstName(), "Firstname not match.");
        Assert.assertEquals(jsonPath.getString("response.lastName"), user.getLastName(), "Lastname not match.");
        Assert.assertEquals(jsonPath.getString("response.email"), user.getEmail(), "Email not match.");
        Assert.assertEquals(jsonPath.getString("response.phone"), user.getPhone(), "Phone not match.");
        Assert.assertEquals(jsonPath.getInt("response.userStatus"), user.getUserStatus(), "UserStatus not match.");

        LogUtils.info("message: " + jsonPath.getString("message"));
        LogUtils.info("username: " + jsonPath.getString("response.username"));
        LogUtils.info("firstName: " + jsonPath.getString("response.firstName"));
        LogUtils.info("lastName: " + jsonPath.getString("response.lastName"));
        LogUtils.info("email: " + jsonPath.getString("response.email"));
        LogUtils.info("phone: " + jsonPath.getString("response.phone"));
        LogUtils.info("userStatus: " + jsonPath.getInt("response.userStatus"));
        LogUtils.info("id: " + jsonPath.getInt("response.id"));

    }

    public static void verifyAllHeaders(Response response) {

        Assert.assertEquals(response.getHeader("Connection"), "Keep-Alive");

        Assert.assertNotNull(response.getHeader("Keep-Alive"), "Keep-Alive header should not be null");

        Assert.assertEquals(response.getHeader("Cache-Control"), "no-cache, private");

        Assert.assertTrue(response.getHeader("Content-Type").contains("application/json"),
                "Content-Type should be application/json");

        Assert.assertEquals(response.getHeader("X-RateLimit-Limit"), "60");

        Assert.assertNotNull(response.getHeader("X-RateLimit-Remaining"), "X-RateLimit-Remaining should not be null");

        Assert.assertEquals(response.getHeader("Access-Control-Allow-Origin"), "*");

        Assert.assertEquals(response.getHeader("Vary"), "Accept-Encoding");

        int contentLength = Integer.parseInt(response.getHeader("Content-Length"));
        Assert.assertTrue(contentLength > 0, "Content-Length should be > 0");

        Assert.assertEquals(response.getHeader("Content-Encoding"), "gzip");

        Assert.assertNotNull(response.getHeader("Date"), "Date header should not be null");

        Assert.assertEquals(response.getHeader("Server"), "LiteSpeed");
    }

    public static void verifyResponseSuccess(Response response, RegisterUserPOJO_Lombok user, int statusCode) {
        ApiAssertion.verifyBaseResponse(response, statusCode, 2000);
        ApiAssertion.verifySchema(response, "src/test/resources/testdata/GetUserSchema.json");
        VerifyDataUserBody.verifyDataBodyUser(response, user);
        VerifyDataUserBody.verifyAllHeaders(response);
    }

    public static void verifyResponseFail(Response response, RegisterUserPOJO_Lombok user, int statusCode) {
        ApiAssertion.verifyBaseResponse(response, statusCode, 2000);
        ApiAssertion.verifySchema(response, "src/test/resources/testdata/GetUserSchemaAfterDelete.json");
        VerifyDataUserBody.verifyDataBodyUser(response, user);
        VerifyDataUserBody.verifyAllHeaders(response);
    }

}