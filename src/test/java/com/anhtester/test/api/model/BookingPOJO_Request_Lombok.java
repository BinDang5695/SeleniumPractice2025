package com.anhtester.test.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookingPOJO_Request_Lombok {

    private String firstname;

    private String lastname;

    private int totalprice;

    private boolean depositpaid;

    private BookingDatesPOJO_Lombok bookingdates;

    private String additionalneeds;

}