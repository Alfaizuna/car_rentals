package com.travel.carRentals.restapi.payload.request;

import com.travel.carRentals.database.enums.Driver;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChooseOrderByIdRequest {

    private Long idCar;
    private Driver driver;
    private LocalDateTime startDateAndTime;
    private LocalDateTime endDateAndTime;
}
