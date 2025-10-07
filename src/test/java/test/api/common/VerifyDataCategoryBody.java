package test.api.common;

import test.api.model.CategoryPOJO_Request_Lombok;
import settings.utils.LogUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

public class VerifyDataCategoryBody {

    public static void verifyDataBodyCategory(Response response, CategoryPOJO_Request_Lombok category) {

        JsonPath jsonPath = response.jsonPath();

        int actualId = jsonPath.getInt("response.id");
        category.setId(actualId);
        Assert.assertTrue(actualId > 0, "ID should be greater than 0.");

        Assert.assertEquals(jsonPath.getString("message"), "Success", "Message not match.");
        Assert.assertEquals(jsonPath.getString("response.name"), category.getName(), "Name not match.");

        LogUtils.info("message: " + jsonPath.getString("message"));
        LogUtils.info("name: " + jsonPath.getString("response.name"));
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

    public static void verifyResponseSuccess(Response response, CategoryPOJO_Request_Lombok category, int statusCode) {
        ApiAssertion.verifyBaseResponse(response, statusCode, 2000);
        ApiAssertion.verifySchema(response, "src/test/resources/testdata/GetCategorySchema.json");
        VerifyDataCategoryBody.verifyDataBodyCategory(response, category);
        VerifyDataCategoryBody.verifyAllHeaders(response);
    }

    public static void verifyResponseFail(Response response, CategoryPOJO_Request_Lombok category, int statusCode) {
        ApiAssertion.verifyBaseResponse(response, statusCode, 2000);
        ApiAssertion.verifySchema(response, "src/test/resources/testdata/GetCategorySchema.json");
        VerifyDataCategoryBody.verifyDataBodyCategory(response, category);
        VerifyDataCategoryBody.verifyAllHeaders(response);
    }

}