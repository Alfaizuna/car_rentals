package com.travel.carRentals.restapi.payload.response;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CarResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String brand;
    private String carName;
    private String carType;
    private Long amountOfPassenger;
    private Long amountOfLuggage;
    private Long pricePerDay;
    private String driver;
    //    private Date startDateAndTime;
    //    private Date endDateAndTime;
    private String city;
    private String vendorName;
    private String carStatus;

    public CarResponse() {
    }

    public CarResponse(String brand, String carName, String carType, Long amountOfPassenger, Long amountOfLuggage, Long pricePerDay, String driver, String city, String vendorName, String carStatus) {
        this.brand = brand;
        this.carName = carName;
        this.carType = carType;
        this.amountOfPassenger = amountOfPassenger;
        this.amountOfLuggage = amountOfLuggage;
        this.pricePerDay = pricePerDay;
        this.driver = driver;
        this.city = city;
        this.vendorName = vendorName;
        this.carStatus = carStatus;
    }

    @Override
    public String toString() {
        return "CarResponse{" +
                "brand='" + brand + '\'' +
                ", carName='" + carName + '\'' +
                ", carType='" + carType + '\'' +
                ", amountOfPassenger=" + amountOfPassenger +
                ", amountOfLuggage=" + amountOfLuggage +
                ", pricePerDay=" + pricePerDay +
                ", driver='" + driver + '\'' +
                ", city='" + city + '\'' +
                ", vendorName='" + vendorName + '\'' +
                ", carStatus='" + carStatus + '\'' +
                '}';
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public Long getAmountOfPassenger() {
        return amountOfPassenger;
    }

    public void setAmountOfPassenger(Long amountOfPassenger) {
        this.amountOfPassenger = amountOfPassenger;
    }

    public Long getAmountOfLuggage() {
        return amountOfLuggage;
    }

    public void setAmountOfLuggage(Long amountOfLuggage) {
        this.amountOfLuggage = amountOfLuggage;
    }

    public Long getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Long pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }
}
