package com.travel.carRentals.restapi.controller;

import com.travel.carRentals.restapi.connectapi.RestService;
import com.travel.carRentals.restapi.payload.response.CarResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    RestService restService;

    @GetMapping("/cars")
    public List<CarResponse> getCars(){
        List<CarResponse> cars = Arrays.asList(restService.getCars());
        return cars;
    }
}
