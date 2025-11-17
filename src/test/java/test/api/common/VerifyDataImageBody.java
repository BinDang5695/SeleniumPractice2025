package test.api.common;

import test.api.model.ImagePOJO_Response_Lombok;
import settings.utils.LogUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

public class VerifyDataImageBody {

    public static void verifyDataBodyImage(Response response, ImagePOJO_Response_Lombok imageresponse) {
        JsonPath jsonPath = response.jsonPath();

        int actualId = jsonPath.getInt("response.id");
        imageresponse.setId(actualId);
        imageresponse.setPath(jsonPath.getString("response.path"));

        Assert.assertTrue(actualId > 0, "ID should be greater than 0.");
        Assert.assertNotNull(imageresponse.getPath(), "Path should not be null.");
        Assert.assertEquals(jsonPath.getString("message"), "Success", "Message not match.");

        LogUtils.info("message: " + jsonPath.getString("message"));
        LogUtils.info("path: " + jsonPath.getString("response.path"));
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

    public static void verifyResponseSuccess(Response response, ImagePOJO_Response_Lombok imageresponse, int statusCode) {
        ApiAssertion.verifyBaseResponse(response, statusCode, 2000);
        ApiAssertion.verifySchema(response, "src/test/resources/testdata/GetImageSchema.json");
        VerifyDataImageBody.verifyDataBodyImage(response, imageresponse);
        VerifyDataImageBody.verifyAllHeaders(response);
    }
}