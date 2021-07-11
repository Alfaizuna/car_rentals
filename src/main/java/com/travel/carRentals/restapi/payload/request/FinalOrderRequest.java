package com.travel.carRentals.restapi.payload.request;

import lombok.Data;

@Data
public class FinalOrderRequest {

    private Long idOrder;
    private String takenLocation;
    private String givenLocation;

}
