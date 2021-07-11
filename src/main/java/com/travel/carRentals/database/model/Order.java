package com.travel.carRentals.database.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idOrder;
    private Long idCar;
    private String brand;
    private String carName;
    private String carType;
    private Long amountOfPassenger;
    private Long amountOfLuggage;
    private Long pricePerDay;
    private String driver;
    private String startDateAndTime;
    private String endDateAndTime;
    private String city;
    private String vendorName;
    private String carStatus;
    private String paymentStatus;
    private String paymentMethod;
    private String review;
    private double rating;
    private int baseRating;
    private boolean refund;
    private boolean cleanTrip;
    private boolean reschedule;
    private String imageLink;
    private String OrderStatus;
    private String takenLocation;
    private String givenLocation;
    private int isDeleted;


}
