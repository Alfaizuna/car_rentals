package com.travel.carRentals.restapi.payload.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChooseOrderByIdResponse {

    private Long idCar;
    private String brand;
    private String carName;
    private String carType;
    private String carStatus;
    private String driver;
    private Long amountOfPassenger;
    private Long amountOfLuggage;
    private String startDateAndTime;
    private String endDateAndTime;
    private Long pricePerDay;
    private String vendorName;
    private String city;
    private String paymentStatus;
    //    private Rating rating;
    private Double rating;
    private Integer baseRating;
    //    private FeatureCarRental featureCarRental;
    private boolean cleanTrip;
    private boolean refund;
    private boolean reschedule;

    private String review;
    private String imageLink;
}
