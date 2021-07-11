package com.travel.carRentals.restapi.payload.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChooseOrderResponse {

    private Long idCar;
    private String brand;
    private String carName;
    private String carType;
    private String driver;
    private Long amountOfPassenger;
    private Long amountOfLuggage;
    private LocalDateTime startDateAndTime;
    private LocalDateTime endDateAndTime;
    private Long pricePerDay;
    private String imageLink;

}
