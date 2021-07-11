package com.travel.carRentals.database.service;

import com.travel.carRentals.database.model.Order;
import com.travel.carRentals.restapi.payload.request.FinalOrderRequest;
import com.travel.carRentals.restapi.payload.request.PaymentRequest;
import com.travel.carRentals.restapi.payload.response.ChooseOrderByIdResponse;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;

@Service
public class OrderServiceImpl {

    private SqlSession session;

    public void connectMyBatis() throws IOException {
        Reader reader = Resources.getResourceAsReader("SqlMapConfig2.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        session = sqlSessionFactory.openSession();
    }

    public void saveOrder(ChooseOrderByIdResponse car) throws IOException {
        connectMyBatis();
        Order order = new Order();
        order.setIdCar(car.getIdCar());
        order.setBrand(car.getBrand());
        order.setCarName(car.getCarName());
        order.setCarType(car.getCarType());
        order.setAmountOfPassenger(car.getAmountOfPassenger());
        order.setAmountOfLuggage(car.getAmountOfLuggage());
        order.setPricePerDay(car.getPricePerDay());
        order.setDriver(car.getDriver());
        order.setStartDateAndTime(car.getStartDateAndTime());
        order.setEndDateAndTime(car.getEndDateAndTime());
        order.setCity(car.getCity());
        order.setVendorName(car.getVendorName());
        order.setCarStatus(car.getCarStatus());
        order.setPaymentStatus("NOT_PAID_YET");
        order.setPaymentMethod(null);
        order.setReview("1");
        order.setRating(car.getRating());
        order.setBaseRating(car.getBaseRating());
        order.setRefund(car.isRefund());
        order.setCleanTrip(car.isCleanTrip());
        order.setReschedule(car.isReschedule());
        order.setImageLink(car.getImageLink());
        order.setOrderStatus("ORDERING");
        order.setTakenLocation(null);
        order.setGivenLocation(null);
        order.setIsDeleted(0);


        session.insert("Order.saveOrder", order);
        session.commit();
        session.close();
    }

    public Order getOrderById(FinalOrderRequest finalOrderRequest) throws IOException {
        connectMyBatis();

        Order order = session.selectOne("Order.getOrderById", finalOrderRequest.getIdOrder());

        order.setTakenLocation(finalOrderRequest.getTakenLocation());
        order.setGivenLocation(finalOrderRequest.getGivenLocation());

        session.commit();
        session.close();
        return order;
    }

    public void updateOrder(Order order) throws IOException {
        connectMyBatis();
        session.update("Order.updateOrder", order);
        System.out.println("Order Updated");
        session.commit();
        session.close();
    }

    public void payment(PaymentRequest paymentRequest) throws IOException {
        connectMyBatis();
        Order order = session.selectOne("getOrderById", paymentRequest.getIdOrder());

        order.setOrderStatus("SUCCESS");
        order.setPaymentStatus("PAID");
        order.setPaymentMethod(paymentRequest.getPaymentMethod());
        session.update("Order.payment", order);
        System.out.println("Payment Berhasil!");
        session.commit();
        session.close();
    }
}
