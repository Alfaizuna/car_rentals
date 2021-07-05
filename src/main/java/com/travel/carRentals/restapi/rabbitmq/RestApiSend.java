package com.travel.carRentals.restapi.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Component
public class RestApiSend {

    public void insertUser(String userString) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("insertUser", true, false, false, null);
            channel.basicPublish("", "insertUser", null, userString.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + userString + "'");
        }
    }

    public void deleteSiswaById(String idString) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("deleteSiswaById", false, false, false, null);
            //String message = "Assalamualaikum";
            channel.basicPublish("", "deleteSiswaById", null, idString.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + idString + "'");
        }
    }

    public void updateSiswaById(String siswaString) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("updateSiswaById", false, false, false, null);
            //String message = "Assalamualaikum";
            channel.basicPublish("", "updateSiswaById", null, siswaString.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + siswaString + "'");
        }
    }
}
