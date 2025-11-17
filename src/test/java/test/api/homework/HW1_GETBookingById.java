package test.api.homework;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HW1_GETBookingById {

    @Test
    public void getBookingById() {
        RequestSpecification request = given();
        request.baseUri("https://restful-booker.herokuapp.com")
                .accept("application/json");

        int id = 278;

        Response response = request.when().get("/booking/" + id);
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200, "Status Code is wrong.");
        Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8", "Content Type is wrong.");

        JsonPath jsonPath = response.jsonPath();
        String firstname = jsonPath.get("firstname");
        String lastname = jsonPath.get("lastname");
        int totalprice = jsonPath.getInt("totalprice");
        boolean depositpaid = jsonPath.get("depositpaid");
        String checkin = jsonPath.get("bookingdates.checkin");
        String checkout = jsonPath.get("bookingdates.checkout");
        String additionalneeds = jsonPath.get("additionalneeds");

        System.out.println("Firstname: " + firstname);
        Assert.assertEquals(firstname.contains("Bin"), true, "Message Bin not exist.");
        Assert.assertEquals(firstname, "Bin", "Message Bin not exist.");

        System.out.println("Lastname: " + lastname);
        Assert.assertEquals(lastname.contains("Dang"), true, "Message Dang not exist.");
        Assert.assertEquals(lastname, "Dang", "Message Dang not exist.");

        System.out.println("Total price: " + totalprice);
        Assert.assertEquals(jsonPath.get("totalprice").toString(), "123", "Total price is wrong.");
        Assert.assertEquals(Integer.parseInt(jsonPath.get("totalprice").toString()), 123, "Total price is wrong.");

        System.out.println("Deposit paid: " + depositpaid);
        Assert.assertEquals(depositpaid, true, "Message true not exist.");

        System.out.println("Checkin: " + checkin);
        Assert.assertEquals(checkin.contains("2025-09-01"), true, "Checkin not exist.");
        Assert.assertEquals(checkin, "2025-09-01", "Checkin not exist.");

        System.out.println("Checkout: " + checkout);
        Assert.assertEquals(checkout.contains("2025-09-02"), true, "Checkout not exist.");
        Assert.assertEquals(checkout, "2025-09-02", "Checkout not exist.");

        System.out.println("Additional needs: " + additionalneeds);
        Assert.assertEquals(additionalneeds.contains("Breakfast"), true, "Additional needs not exist.");
        Assert.assertEquals(additionalneeds, "Breakfast", "Additional needs not exist.");

    }

}
