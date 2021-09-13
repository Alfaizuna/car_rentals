package com.travel.carRentals.restapi.connectapi;

import com.travel.carRentals.restapi.payload.request.ChooseOrderByIdRequest;
import com.travel.carRentals.restapi.payload.request.ChooseOrderRequest;
import com.travel.carRentals.restapi.payload.response.CarResponse;
import com.travel.carRentals.restapi.payload.response.ChooseOrderByIdResponse;
import com.travel.carRentals.restapi.payload.response.ChooseOrderResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class RestService {
    private final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        // set connection and read timeouts
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(500))
                .setReadTimeout(Duration.ofSeconds(500))
                .build();
    }

    public CarResponse[] getCars() {
        String url = "http://localhost:8082/rent/getAvailableCars";
        return this.restTemplate.getForObject(url, CarResponse[].class);
    }

//    public String getPostsPlainJSON() {
//        String url = "https://jsonplaceholder.typicode.com/posts";
//        return this.restTemplate.getForObject(url, String.class);
//    }

    public ChooseOrderResponse[] getCarsByCity(@RequestBody ChooseOrderRequest chooseOrderRequest) {
        String url = "http://localhost:8082/rent/chooseFirstOrder";

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // create a post object
        ChooseOrderRequest post = new ChooseOrderRequest();
        post.setCity(chooseOrderRequest.getCity());
        post.setDriver(chooseOrderRequest.getDriver());
        post.setStartDateAndTime(LocalDateTime.now());
        post.setEndDateAndTime(LocalDateTime.now());

        // build the request
        HttpEntity<ChooseOrderRequest> entity = new HttpEntity<>(post, headers);

        // send POST request
        return restTemplate.postForObject(url, entity, ChooseOrderResponse[].class);
    }

    public ChooseOrderByIdResponse getCarById(@RequestBody ChooseOrderByIdRequest chooseOrderByIdRequest) {
        String url = "http://localhost:8082/rent/chooseFirstOrderById";

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // create a post object
        ChooseOrderByIdRequest post = new ChooseOrderByIdRequest();
        post.setIdCar(chooseOrderByIdRequest.getIdCar());
        post.setDriver(chooseOrderByIdRequest.getDriver());
        post.setStartDateAndTime(chooseOrderByIdRequest.getStartDateAndTime());
        post.setEndDateAndTime(chooseOrderByIdRequest.getEndDateAndTime());

        // build the request
        HttpEntity<ChooseOrderByIdRequest> entity = new HttpEntity<>(post, headers);

        // send POST request
        return restTemplate.postForObject(url, entity, ChooseOrderByIdResponse.class);
    }

}
