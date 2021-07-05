package com.travel.carRentals.database;

import com.travel.carRentals.database.rabbitmq.Receive;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DatabaseMain {
    public static Receive receive = new Receive();

    public static void main(String[] args) throws IOException, TimeoutException {
        receive.registerUser();
    }
}
