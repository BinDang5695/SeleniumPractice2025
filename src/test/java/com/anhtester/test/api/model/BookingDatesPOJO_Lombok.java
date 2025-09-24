package com.anhtester.test.api.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDatesPOJO_Lombok {

     private String checkin;
     private String checkout;


}
