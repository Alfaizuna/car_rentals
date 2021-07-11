package com.travel.carRentals.restapi.controller;

import com.google.gson.Gson;
import com.travel.carRentals.database.model.Order;
import com.travel.carRentals.database.service.OrderServiceImpl;
import com.travel.carRentals.restapi.connectapi.RestService;
import com.travel.carRentals.restapi.payload.request.ChooseOrderByIdRequest;
import com.travel.carRentals.restapi.payload.request.ChooseOrderRequest;
import com.travel.carRentals.restapi.payload.request.FinalOrderRequest;
import com.travel.carRentals.restapi.payload.request.PaymentRequest;
import com.travel.carRentals.restapi.payload.response.CarResponse;
import com.travel.carRentals.restapi.payload.response.ChooseOrderByIdResponse;
import com.travel.carRentals.restapi.payload.response.ChooseOrderResponse;
import com.travel.carRentals.restapi.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    RestService restService;

    OrderServiceImpl orderService = new OrderServiceImpl();

    @GetMapping("/cars")
    public List<CarResponse> getCars(){
        List<CarResponse> cars = Arrays.asList(restService.getCars());
        return cars;
    }

    @PostMapping("/chooseOrder")
    public List<ChooseOrderResponse> chooseOrder(@RequestBody ChooseOrderRequest chooseOrderRequest){

        List<ChooseOrderResponse> cars = Arrays.asList(restService.getCarsByCity(chooseOrderRequest));

        return cars;
    }

    @PostMapping("/chooseOrderById")
    public ChooseOrderByIdResponse chooseOrderById(@RequestBody ChooseOrderByIdRequest chooseOrderByIdRequest) throws IOException, TimeoutException {

//        List<ChooseOrderResponse> cars = Arrays.asList(restService.getCarsByCity(chooseOrderRequest));
        ChooseOrderByIdResponse car = restService.getCarById(chooseOrderByIdRequest);
        orderService.saveOrder(car);

        return car;
    }

    @PostMapping("/finalOrder")
    public Order finalOrder(@RequestBody FinalOrderRequest finalOrderRequest) throws IOException {

        Order order = orderService.getOrderById(finalOrderRequest);
        orderService.updateOrder(order);
        return order;
    }

    @PostMapping("/payment")
    public ResponseEntity<?> paymentSuccess(@RequestBody PaymentRequest paymentRequest) throws IOException {

        orderService.payment(paymentRequest);

        CommonResponse commonResponse = new CommonResponse("Payment Berhasil");
        return new ResponseEntity<>(new Gson().toJson(commonResponse), HttpStatus.OK);
    }
}
