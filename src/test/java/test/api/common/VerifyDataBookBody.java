package test.api.common;

import test.api.model.BookPOJO_Lombok;
import test.api.model.ImagePOJO_Response_Lombok;
import settings.utils.LogUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;

public class VerifyDataBookBody {

    public static void verifyDataBodyBook(Response response, BookPOJO_Lombok book) {
        JsonPath jsonPath = response.jsonPath();

        int actualId = jsonPath.getInt("response.id");
        book.setId(actualId);
        Assert.assertTrue(actualId > 0, "ID should be greater than 0.");

        Assert.assertEquals(jsonPath.getString("message"), "Success", "Message not match.");
        Assert.assertEquals(jsonPath.getString("response.name"), book.getName(), "Book name not match.");
        Assert.assertEquals(jsonPath.getInt("response.category_id"), book.getCategory_id(), "CategoryID not match.");
        Assert.assertEquals(jsonPath.getInt("response.price"), book.getPrice(), "Price not match.");
        Assert.assertEquals(jsonPath.getString("response.release_date"), book.getRelease_date(), "Release date not match.");

        Object statusObj = jsonPath.get("response.status");
        boolean actualStatus;
        if (statusObj instanceof Boolean) {
            actualStatus = (Boolean) statusObj;
        } else if (statusObj instanceof Integer) {
            actualStatus = ((Integer) statusObj == 1);
        } else {
            throw new AssertionError("Unexpected status type: " + statusObj.getClass());
        }

        Assert.assertEquals(actualStatus, book.isStatus(), "Status not match.");
        List<ImagePOJO_Response_Lombok> responseImages = jsonPath.getList("response.image", ImagePOJO_Response_Lombok.class);
        List<Integer> requestImageIds = book.getImage_ids();

        Assert.assertNotNull(responseImages, "Image list should not be null");

        Assert.assertEquals(responseImages.size(), requestImageIds.size(), "Number of images in response does not match request image_ids");

        for (int i = 0; i < requestImageIds.size(); i++) {

            ImagePOJO_Response_Lombok img = responseImages.get(i);
            int requestId = requestImageIds.get(i);
            Assert.assertEquals(img.getId(), requestId, "Image ID at index " + i + " does not match request image_ids");
            Assert.assertNotNull(img.getPath(), "Image path should not be null");
            Assert.assertTrue(img.getPath().contains("public/images/"), "Image path should contain 'public/images/': " + img.getPath());

            LogUtils.info("message: " + jsonPath.getString("message"));
            LogUtils.info("name: " + jsonPath.getString("response.name"));
            LogUtils.info("category_id: " + jsonPath.getInt("response.category_id"));
            LogUtils.info("price: " + jsonPath.getInt("response.price"));
            LogUtils.info("release_date: " + jsonPath.getString("response.release_date"));
            LogUtils.info("status: " + jsonPath.getBoolean("response.status"));
            LogUtils.info("id: " + jsonPath.getInt("response.id"));
            LogUtils.info("image.id: " + jsonPath.getString("response.image[0].id"));
            LogUtils.info("image.path: " + jsonPath.getString("response.image[0].path"));
        }
    }

        public static void verifyAllHeaders (Response response){

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

        public static void verifyResponseSuccess (Response response, BookPOJO_Lombok book,int statusCode)
        {
            ApiAssertion.verifyBaseResponse(response, statusCode, 2000);
            ApiAssertion.verifySchema(response, "src/test/resources/testdata/GetBookSchema.json");
            VerifyDataBookBody.verifyDataBodyBook(response, book);
            VerifyDataBookBody.verifyAllHeaders(response);
        }

        public static void verifyDataBodyBookAfterDelete(Response response, BookPOJO_Lombok book) {
        JsonPath jsonPath = response.jsonPath();

        int actualId = jsonPath.getInt("response.id");
        book.setId(actualId);
        Assert.assertTrue(actualId > 0, "ID should be greater than 0.");

        Assert.assertEquals(jsonPath.getString("message"), "Success", "Message not match.");
        Assert.assertEquals(jsonPath.getString("response.name"), book.getName(), "Book name not match.");
        Assert.assertEquals(jsonPath.getInt("response.category_id"), book.getCategory_id(), "CategoryID not match.");
        Assert.assertEquals(jsonPath.getInt("response.price"), book.getPrice(), "Price not match.");
        Assert.assertEquals(jsonPath.getString("response.release_date"), book.getRelease_date(), "Release date not match.");

        Object statusObj = jsonPath.get("response.status");
        boolean actualStatus;
        if (statusObj instanceof Boolean) {
            actualStatus = (Boolean) statusObj;
        } else if (statusObj instanceof Integer) {
            actualStatus = ((Integer) statusObj == 1);
        } else {
            throw new AssertionError("Unexpected status type: " + statusObj.getClass());
        }

        Assert.assertEquals(actualStatus, book.isStatus(), "Status not match.");

            LogUtils.info("message: " + jsonPath.getString("message"));
            LogUtils.info("name: " + jsonPath.getString("response.name"));
            LogUtils.info("category_id: " + jsonPath.getInt("response.category_id"));
            LogUtils.info("price: " + jsonPath.getInt("response.price"));
            LogUtils.info("release_date: " + jsonPath.getString("response.release_date"));
            LogUtils.info("status: " + jsonPath.getBoolean("response.status"));
            LogUtils.info("id: " + jsonPath.getInt("response.id"));
    }

    public static void verifyResponseSuccessAfterDelete (Response response, BookPOJO_Lombok book,int statusCode)
    {
        ApiAssertion.verifyBaseResponse(response, statusCode, 2000);
        ApiAssertion.verifySchema(response, "src/test/resources/testdata/GetBookSchema.json");
        VerifyDataBookBody.verifyDataBodyBookAfterDelete(response, book);
        VerifyDataBookBody.verifyAllHeaders(response);
    }

        public static void verifyResponseFail (Response response, BookPOJO_Lombok book,int statusCode){

            ApiAssertion.verifyBaseResponse(response, statusCode, 2000);
            ApiAssertion.verifySchema(response, "src/test/resources/testdata/GetUserSchemaAfterDelete.json");
            VerifyDataBookBody.verifyDataBodyBook(response, book);
            VerifyDataBookBody.verifyAllHeaders(response);
        }

}