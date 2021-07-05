package com.travel.carRentals.restapi.util;

public class Response {

    private String message;

    public Response(String message) {
        this.message = message;
    }

    public static String successResponse(String message){
        return message;
    }
}
