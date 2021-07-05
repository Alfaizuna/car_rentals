package com.travel.carRentals.restapi.controller;


import com.google.gson.Gson;
import com.travel.carRentals.database.model.AuthRequest;
import com.travel.carRentals.database.model.User;
import com.travel.carRentals.restapi.rabbitmq.RestApiSend;
import com.travel.carRentals.restapi.util.CustomErrorType;
import com.travel.carRentals.restapi.util.JwtUtil;
import com.travel.carRentals.restapi.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/car")
public class UserController {

    @Autowired
    RestApiSend restApiSend;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Traveloka Car Rentals!!";
    }

    @PostMapping("/login")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("invalid username / password");
        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            String username = "^([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})";
            String pass = "^[a-zA-Z0-9@\\\\#$%&*()_+\\]\\[';:?.,!^-]{8,}$";

            boolean usernameTrue = Pattern.matches(username, user.getUsername());
            boolean passwordTrue = Pattern.matches(pass, user.getPassword());

            if (usernameTrue && passwordTrue) {
                restApiSend.insertUser(new Gson().toJson(user));
            } else {
                return new ResponseEntity<>(new CustomErrorType(new Gson().toJson("Invalid username or password!")), HttpStatus.NOT_FOUND);
            }
//                restApiSend.insertUser(new Gson().toJson(user));
        } catch (Exception e) {
            System.out.println("error = " + e);
            return new ResponseEntity<>(new CustomErrorType(new Gson().toJson("there are any login session, please logout first")), HttpStatus.NOT_FOUND);
        }

        Response response = new Response("Registrasi " + user.getUsername() + " Berhasil!");
        return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
    }

    @PostMapping("/recovery")
    public ResponseEntity<?> passwordRecovery(@RequestParam String email) {

        return null;
    }
}
