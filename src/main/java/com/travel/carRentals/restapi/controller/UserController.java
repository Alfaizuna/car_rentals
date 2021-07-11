package com.travel.carRentals.restapi.controller;


import com.google.gson.Gson;
import com.travel.carRentals.database.model.AuthRequest;
import com.travel.carRentals.database.model.User;
import com.travel.carRentals.database.service.UserService;
import com.travel.carRentals.restapi.payload.response.LoginResponse;
import com.travel.carRentals.restapi.rabbitmq.RestApiSend;
import com.travel.carRentals.restapi.util.CustomErrorType;
import com.travel.carRentals.restapi.util.JwtUtil;
import com.travel.carRentals.restapi.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @Autowired
    UserService userService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Aulia Car Rentals system!!!";
    }

    @PostMapping("/login")
    public Object generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword())
            );
        } catch (Exception ex) {
//            return "Invalid username / password";
            return new CustomErrorType("Invalid username / password");
        }
        System.out.println("token created!");
        String token = jwtUtil.generateToken(authRequest.getUsername());
//        return "Bearer " + token;
        LoginResponse loginResponse = new LoginResponse(authRequest.getUsername(), authRequest.getPassword(), "Bearer "+token);
        return loginResponse;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            String emailRgx = "^([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})";
            String passwordRgx = "^[a-zA-Z0-9@\\\\#$%&*()_+\\]\\[';:?.,!^-]{8,}$";

            boolean emailTrue = Pattern.matches(emailRgx, user.getEmail());
            boolean passwordTrue = Pattern.matches(passwordRgx, user.getPassword());

            if (emailTrue && passwordTrue) {
                restApiSend.insertUser(new Gson().toJson(user));
            } else {
                return new ResponseEntity<>(new CustomErrorType(new Gson().toJson("Invalid username or password!")), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
            return new ResponseEntity<>(new CustomErrorType(new Gson().toJson("there are any login session, please logout first")), HttpStatus.NOT_FOUND);
        }

        CommonResponse commonResponse = new CommonResponse("Registrasi " + user.getUsername() + " Berhasil!");
        return new ResponseEntity<>(new Gson().toJson(commonResponse), HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email) throws IOException {

        String response = userService.forgotPassword(email);

        if (!response.startsWith("Invalid")) {
            response = "http://localhost:8080/car/reset-password?token=" + response;
        }
        return response;
    }

    @PutMapping("/reset-password")
    public String resetPassword(@RequestParam String token, @RequestParam String password) throws IOException {
        return userService.resetPassword(token, password);
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
        return "berhasil-logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }
}
