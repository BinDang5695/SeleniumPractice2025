package com.anhtester.test.api.common;

import com.anhtester.test.api.model.BookingPOJO_Request_Lombok;
import com.anhtester.test.api.model.BookingPOJO_Response_Lombok;
import com.anhtester.utils.LogUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

public class VerifyDataBookingBody {

    public static void verifyDataBodyBooking(Response response, BookingPOJO_Request_Lombok bookingrequest, BookingPOJO_Response_Lombok bookingresponse) {

        JsonPath jsonPath = response.jsonPath();

        int actualBookingId = jsonPath.getInt("bookingid");
        bookingresponse.setBookingid(actualBookingId);
        Assert.assertTrue(actualBookingId > 0, "BookingId should be greater than 0.");

        Assert.assertEquals(jsonPath.getString("booking.firstname"), bookingrequest.getFirstname(), "FirstName not match.");
        Assert.assertEquals(jsonPath.getString("booking.lastname"), bookingrequest.getLastname(), "LastName not match.");
        Assert.assertEquals(jsonPath.getInt("booking.totalprice"), bookingrequest.getTotalprice(), "TotalPrice not match.");
        Assert.assertEquals(jsonPath.getBoolean("booking.depositpaid"), bookingrequest.isDepositpaid(), "DepositPaid not match.");
        Assert.assertEquals(jsonPath.getString("booking.bookingdates.checkin"), bookingrequest.getBookingdates().getCheckin(), "Checkin not match.");
        Assert.assertEquals(jsonPath.getString("booking.bookingdates.checkout"), bookingrequest.getBookingdates().getCheckout(), "Checkout not match.");
        Assert.assertEquals(jsonPath.getString("booking.additionalneeds"), bookingrequest.getAdditionalneeds(), "Additionalneeds not match.");

        LogUtils.info("BookingId: " + jsonPath.getInt("bookingid"));
        LogUtils.info("firstname: " + jsonPath.getString("booking.firstname"));
        LogUtils.info("lastname: " + jsonPath.getString("booking.lastname"));
        LogUtils.info("totalprice: " + jsonPath.getInt("booking.totalprice"));
        LogUtils.info("depositpaid: " + jsonPath.getBoolean("booking.depositpaid"));
        LogUtils.info("checkin: " + jsonPath.getString("booking.bookingdates.checkin"));
        LogUtils.info("checkout: " + jsonPath.getString("booking.bookingdates.checkin"));
        LogUtils.info("additionalneeds " + jsonPath.getString("booking.additionalneeds"));
    }

        public static void verifyAllHeaders (Response response){

            Assert.assertEquals(response.getHeader("Server"), "Heroku", "Server not match");

            Assert.assertTrue(response.getHeader("Content-Type").contains("application/json"),
                    "Content-Type should be application/json");

            Assert.assertEquals(response.getHeader("Via"), "1.1 heroku-router", "Via header not match");

            Assert.assertNotNull(response.getHeader("Etag"), "Etag header should not be null");

            Assert.assertNotNull(response.getHeader("Reporting-Endpoints"), "Reporting-Endpoints should not be null");

            Assert.assertNotNull(response.getHeader("Report-To"), "Report-To should not be null");

            Assert.assertNotNull(response.getHeader("Nel"), "Nel should not be null");

            int contentLength = Integer.parseInt(response.getHeader("Content-Length"));
            Assert.assertTrue(contentLength > 0, "Content-Length should be > 0");

            Assert.assertEquals(response.getHeader("X-Powered-By"), "Express", "X-Powered-By header should not be null");

            Assert.assertNotNull(response.getHeader("Date"), "Date header should not be null");

        }

        public static void verifyResponseSuccess (Response response, BookingPOJO_Request_Lombok bookingRequest, BookingPOJO_Response_Lombok bookingResponse, int statusCode)
        {
            ApiAssertion.verifyBaseResponse(response, statusCode, 2000);
            ApiAssertion.verifySchema(response, "src/test/resources/testdata/GetBookingSchema.json");
            VerifyDataBookingBody.verifyDataBodyBooking(response, bookingRequest, bookingResponse);
            VerifyDataBookingBody.verifyAllHeaders(response);
        }

//        public static void verifyDataBodyBookAfterDelete(Response response, BookPOJO_Lombok book) {
//        JsonPath jsonPath = response.jsonPath();
//
//        int actualId = jsonPath.getInt("response.id");
//        book.setId(actualId);
//        Assert.assertTrue(actualId > 0, "ID should be greater than 0.");
//
//        Assert.assertEquals(jsonPath.getString("message"), "Success", "Message not match.");
//        Assert.assertEquals(jsonPath.getString("response.name"), book.getName(), "Book name not match.");
//        Assert.assertEquals(jsonPath.getInt("response.category_id"), book.getCategory_id(), "CategoryID not match.");
//        Assert.assertEquals(jsonPath.getInt("response.price"), book.getPrice(), "Price not match.");
//        Assert.assertEquals(jsonPath.getString("response.release_date"), book.getRelease_date(), "Release date not match.");
//
//        Object statusObj = jsonPath.get("response.status");
//        boolean actualStatus;
//        if (statusObj instanceof Boolean) {
//            actualStatus = (Boolean) statusObj;
//        } else if (statusObj instanceof Integer) {
//            actualStatus = ((Integer) statusObj == 1);
//        } else {
//            throw new AssertionError("Unexpected status type: " + statusObj.getClass());
//        }
//
//        Assert.assertEquals(actualStatus, book.isStatus(), "Status not match.");
//
//            LogUtils.info("message: " + jsonPath.getString("message"));
//            LogUtils.info("name: " + jsonPath.getString("response.name"));
//            LogUtils.info("category_id: " + jsonPath.getInt("response.category_id"));
//            LogUtils.info("price: " + jsonPath.getInt("response.price"));
//            LogUtils.info("release_date: " + jsonPath.getString("response.release_date"));
//            LogUtils.info("status: " + jsonPath.getBoolean("response.status"));
//            LogUtils.info("id: " + jsonPath.getInt("response.id"));
//    }
//
//    public static void verifyResponseSuccessAfterDelete (Response response, BookingPOJO_Response_Lombok booking,int statusCode)
//    {
//        ApiAssertion.verifyBaseResponse(response, statusCode, 2000);
//        ApiAssertion.verifySchema(response, "src/test/resources/testdata/GetBookSchema.json");
//        VerifyDataBookingBody.verifyDataBodyBookAfterDelete(response, booking);
//        VerifyDataBookingBody.verifyAllHeaders(response);
//    }

//        public static void verifyResponseFail (Response response, BookingPOJO_Response_Lombok book,int statusCode){
//
//            ApiAssertion.verifyBaseResponse(response, statusCode, 2000);
//            ApiAssertion.verifySchema(response, "src/test/resources/testdata/GetUserSchemaAfterDelete.json");
//            VerifyDataBookingBody.verifyDataBodyBook(response, book);
//            VerifyDataBookingBody.verifyAllHeaders(response);
//        }

}