package com.travel.carRentals;

import com.travel.carRentals.restapi.rabbitmq.RestApiReceive;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarRentalsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalsApplication.class, args);
        RestApiReceive restApiReceive = new RestApiReceive();
        try {
            restApiReceive.receiveFromDatabase();
        }catch (Exception e){
            System.out.println(e);
        }
	}
}
