package com.travel.carRentals.database.service;

import com.google.gson.Gson;
import com.travel.carRentals.database.model.User;
import com.travel.carRentals.restapi.util.CommonMessage;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.concurrent.TimeoutException;

@Service
public class MyBatisServiceImpl {

    private SqlSession session;

    public void connectMyBatis() throws IOException {
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        session = sqlSessionFactory.openSession();
    }

    public void register(String userString) throws IOException, TimeoutException {
        System.out.println("Memulai register user..");
        User user = new Gson().fromJson(userString, User.class);

        connectMyBatis();
        user.setToken(null);
        user.setTokenCreationDate(null);
        session.insert("User.register", user);
        session.commit();
        session.close();
    }

    public User findByUser(String user) throws IOException {
        System.out.println("find by user");
        connectMyBatis();
        User user2 = session.selectOne("User.findByUser", user);
        session.commit();
        session.close();
        return user2;
    }

    public User findByEmail(String email) throws IOException {
        System.out.println("find by email");
        connectMyBatis();
        User user2 = session.selectOne("User.findByEmail", email);
        session.commit();
//        session.close();
        return user2;
    }

    public User findByToken(String token) throws IOException {
        System.out.println("find by token");
        connectMyBatis();
        User user = session.selectOne("User.findByToken", token);
        session.commit();
        session.close();
        return user;
    }

    public void updateUser(User user) throws IOException {
        System.out.println("updating user...");
        connectMyBatis();

        User u = new User();
        u.setId(user.getId());
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setToken(user.getToken());
        u.setTokenCreationDate(user.getTokenCreationDate());

        session.update("User.updateUser", u);
        session.commit();
        session.close();
    }

    public void updatePassword(User user) throws IOException {
        System.out.println("updating password...");
        connectMyBatis();

        User u = new User();
        u.setId(user.getId());
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setToken(user.getToken());
        u.setTokenCreationDate(user.getTokenCreationDate());

        session.update("User.updatePassword", u);
        session.commit();
        session.close();
    }
}
