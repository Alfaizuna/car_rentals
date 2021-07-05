package com.travel.carRentals.database.service;

import com.google.gson.Gson;
import com.travel.carRentals.database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    MyBatisServiceImpl myBatisService = new MyBatisServiceImpl();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
//        try {
//            user = myBatisService.findByUser(username);
            user.setUser("izun@yahoo.com");
            user.setPwd("udin321@");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return new org.springframework.security.core.userdetails.User(user.getUser(), user.getPwd(), new ArrayList<>());
    }
}
