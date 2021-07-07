package com.travel.carRentals.restapi.util;

public class CommonResponse {

    private String message;

    public CommonResponse(String message) {
        this.message = message;
    }

    public static String successResponse(String message){
        return message;
    }
}
