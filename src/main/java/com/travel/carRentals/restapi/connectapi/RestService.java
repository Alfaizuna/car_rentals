package com.travel.carRentals.restapi.connectapi;

import com.travel.carRentals.restapi.payload.response.CarResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

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
        String url = "http://localhost:8081/rent/getAvailableCars";
        return this.restTemplate.getForObject(url, CarResponse[].class);
    }

//    public String getPostsPlainJSON() {
//        String url = "https://jsonplaceholder.typicode.com/posts";
//        return this.restTemplate.getForObject(url, String.class);
//    }

}
