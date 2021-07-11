package com.travel.carRentals.restapi.payload.request;

import lombok.Data;

@Data
public class PaymentRequest {

    private Long idOrder;
    private String paymentMethod;
    private String paymentStatus;
    private String OrderStatus;

}
