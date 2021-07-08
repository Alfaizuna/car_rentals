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
        Reader reader = Resources.getResourceAsReader(CommonMessage.SQL);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        session = sqlSessionFactory.openSession();
    }

    public void register(String userString) throws IOException, TimeoutException {
        System.out.println("Memulai register user..");
        User user = new Gson().fromJson(userString, User.class);

        connectMyBatis();
        session.insert("User.register", user);
        session.commit();
        session.close();
    }

    public User saveToken(User user) throws IOException, TimeoutException {
        connectMyBatis();
        String token = new Gson().toJson(user, User.class);
        session.insert("User.saveToken", token);
        session.commit();
        session.close();
        return user;
    }

    public User savePassword(User user) throws IOException, TimeoutException {
        connectMyBatis();
        String token = new Gson().toJson(user, User.class);
        session.insert("User.saveToken", token);
        session.commit();
        session.close();
        return user;
    }

    public User findByUser(String user) throws IOException {
        System.out.println("find by user");
//        User user1 = new Gson().fromJson(user, User.class);
        connectMyBatis();
        User user1 = new Gson().fromJson(user, User.class);
        user1.setTokenCreationDate(null);
        User user2 = session.selectOne("User.findByUser", user1);
        System.out.println("found");
        session.commit();
        session.close();
        return user2;
    }

    public User findByEmail(String email) throws IOException {
        System.out.println("find by email");
        connectMyBatis();
        User user2 = session.selectOne("User.findByEmail", email);
        session.commit();
        session.close();
        return user2;
    }

    public User findByToken(String token) throws IOException {
        System.out.println("find by email");
        connectMyBatis();
        User user2 = session.selectOne("User.findByEmail", token);
        session.commit();
        session.close();
        return user2;
    }

//    public void deleteSiswa(String idString) throws IOException, TimeoutException {
//        System.out.println("Memulai delete siswa...");
//        connectMyBatis();                                           //Membuat koneksi db MyBatis
//        long id = Long.parseLong(idString);                         //Konversi String ke Long
//
//        session.delete("Siswa.deleteNilaiById",id);              //Proses menghapus record nilai
//        int hasil = session.delete("Siswa.deleteSiswaById",id);  //Proses menghapus record siswa
//        session.commit();                                           //Ekseskusi query
//
//        if(hasil==1){
//            System.out.println("Delete siswa berhasil");
//            send.sendToRestApi("Delete siswa berhasil");
//        } else {
//            System.out.println("Delete siswa gagal...");
//            send.sendToRestApi("Delete siswa gagal...");
//        }
//    }

//    public void updateSiswa(String siswaString) throws IOException, TimeoutException {
//
//        connectMyBatis();
//
//        Siswa siswa = new Gson().fromJson(siswaString, Siswa.class);
//
//        System.out.println(siswa.getId_siswa());
//        System.out.println(siswa.getNama());
//        System.out.println(siswa.getAddress());
//        System.out.println(siswa.getStatus());
//
//        int hasil = session.update("Siswa.updateSiswaById", siswa);
//        session.commit();
//
//        if(hasil==1){
//            System.out.println("Edit nama siswa berhasil");
//            send.sendToRestApi("Edit nama siswa berhasil");
//        } else {
//            System.out.println("Edit nama siswa gagal....");
//            send.sendToRestApi("Edit nama siswa gagal....");
//        }
//    }
}
