package test.api.homework;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HW2_POSTBooking {

    @Test
    public void testCreateBooking() {

        String firstname = "Bin";
        String lastname = "Dang";
        int totalprice = 123;
        boolean depositpaid = true;
        String checkin = "2025-09-01";
        String checkout = "2025-09-02";
        String additionalneeds = "Bin dz Test";

        RequestSpecification request = given();
        request.baseUri("https://restful-booker.herokuapp.com")
                .accept("application/json")
                .contentType("application/json")
                .body("{\n" +
                        "  \"firstname\": \"" + firstname + "\",\n" +
                        "  \"lastname\": \"" + lastname + "\",\n" +
                        "  \"totalprice\": " + totalprice + ",\n" +
                        "  \"depositpaid\": " + depositpaid + ",\n" +
                        "  \"bookingdates\": {\n" +
                        "    \"checkin\": \"" + checkin + "\",\n" +
                        "    \"checkout\": \"" + checkout + "\"\n" +
                        "  },\n" +
                        "  \"additionalneeds\": \"" + additionalneeds + "\"\n" +
                        "}");

        Response response = request.when().post("/booking");
        response.prettyPrint();
        response.then().statusCode(200);

        //Verify Response
        response.then().contentType(ContentType.JSON);

        ResponseBody responseBody = response.getBody();
        JsonPath jsonPath = response.jsonPath();

        System.out.println("Firstname: " + firstname);
        Assert.assertEquals(jsonPath.getString("booking.firstname"), firstname, "The firstName not match.");

        System.out.println("Lastname: " + lastname);
        Assert.assertEquals(jsonPath.getString("booking.lastname"), lastname, "The lastName not match.");

        System.out.println("Total price: " + totalprice);
        Assert.assertEquals(jsonPath.getInt("booking.totalprice"), totalprice, "The totalprice not match.");

        System.out.println("Deposit paid: " + depositpaid);
        Assert.assertEquals(jsonPath.getBoolean("booking.depositpaid"), depositpaid, "The depositpaid not match.");

        System.out.println("Checkin: " + checkin);
        Assert.assertEquals(jsonPath.getString("booking.bookingdates.checkin"), checkin, "The checkin not match.");

        System.out.println("Checkout: " + checkout);
        Assert.assertEquals(jsonPath.getString("booking.bookingdates.checkout"), checkout, "The checkout not match.");

        System.out.println("Checkout: " + additionalneeds);
        Assert.assertEquals(jsonPath.getString("booking.additionalneeds"), additionalneeds, "The additionalneeds not match.");
    }
}