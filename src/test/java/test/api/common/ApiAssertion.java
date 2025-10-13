package test.api.common;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.File;

public class ApiAssertion {

    // ✅ Verify status code
    public static void verifyStatusCode(Response response, int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    // ✅ Verify message
    public static void verifyMessage(Response response, String expectedMessage) {
        String actualMessage = response.jsonPath().getString("message");
        Assert.assertEquals(actualMessage, expectedMessage, "Message not match.");
    }

    // ✅ Verify response time (ms)
    public static void verifyResponseTime(Response response, long maxTimeMs) {
        Assert.assertTrue(response.time() <= maxTimeMs,
                "Response time too long. Actual: " + response.time() + " ms");
    }

    // ✅ Verify header
    public static void verifyHeader(Response response, String headerName, String expectedValue) {
        String actual = response.getHeader(headerName);
        Assert.assertEquals(actual, expectedValue,
                "Header " + headerName + " not match. Actual: " + actual);
    }

    // ✅ Verify JSON schema
    public static void verifySchema(Response response, String schemaFilePath) {
        File schema = new File(schemaFilePath);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schema));
    }

    // ✅ Verify all
    public static void verifyBaseResponse(Response response,
                                          int statusCode,
                                          long maxTimeMs) {
        verifyStatusCode(response, statusCode);
        verifyResponseTime(response, maxTimeMs);
    }
}
