package com.travel.carRentals.database.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.travel.carRentals.database.service.MyBatisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Receive {

    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;

//    @Autowired
    MyBatisServiceImpl myBatisServiceImpl = new MyBatisServiceImpl();

    public void connectRabbitMQ() throws IOException, TimeoutException {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
    }

    public void registerUser() throws IOException, TimeoutException{
        try{
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("insertUser", true, false, false, null);
            System.out.println(" [*] Waiting for messages...");
            
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String userString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + userString + "'");
                try {
                    doWork(userString);
                    myBatisServiceImpl.register(userString);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            };
            channel.basicConsume("insertUser", true, deliverCallback, consumerTag -> { });
        }catch (Exception e) {
            System.out.println("Error insertUser = " + e);
        }
    }

    private static void doWork(String task) {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

//    public void deleteSiswaById() {
//        try{
//            connectRabbitMQ();
//            channel = connection.createChannel();
//            channel.queueDeclare("deleteSiswaById", false, false, false, null);
//            //System.out.println(" [*] Waiting for messages..");
//            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//                String idString = new String(delivery.getBody(), StandardCharsets.UTF_8);
//                System.out.println(" [x] Received '" + idString + "'");
//                try {
//                    myBatisServiceImpl.deleteSiswa(idString);
//                } catch (TimeoutException e) {
//                    e.printStackTrace();
//                }
//            };
//            channel.basicConsume("deleteSiswaById", true, deliverCallback, consumerTag -> { });
//        }catch (Exception e) {
//            System.out.println("Error insertSiswa = " + e);
//        }
//    }

//    public void updateSiswaById() {
//        try{
//            connectRabbitMQ();
//            channel = connection.createChannel();
//            channel.queueDeclare("updateSiswaById", false, false, false, null);
//            //System.out.println(" [*] Waiting for messages..");
//            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//                String siswaString = new String(delivery.getBody(), StandardCharsets.UTF_8);
//                System.out.println(" [x] Received '" + siswaString + "'");
//                try {
//                    myBatisService.updateSiswa(siswaString);
//                } catch (TimeoutException e) {
//                    e.printStackTrace();
//                }
//            };
//            channel.basicConsume("updateSiswaById", true, deliverCallback, consumerTag -> { });
//        }catch (Exception e) {
//            System.out.println("Error insertSiswa = " + e);
//        }
//    }
}
