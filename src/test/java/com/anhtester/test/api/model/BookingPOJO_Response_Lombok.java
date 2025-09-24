package com.anhtester.test.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookingPOJO_Response_Lombok {

    private int bookingid;

    private BookingPOJO_Request_Lombok booking;

}