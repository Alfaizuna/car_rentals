package com.travel.carRentals.restapi.payload.request;

import com.travel.carRentals.database.enums.Driver;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ChooseOrderRequest {

    private Driver driver;
    private LocalDateTime startDateAndTime;
    private LocalDateTime endDateAndTime;
    private String city;
}
