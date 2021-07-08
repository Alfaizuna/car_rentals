package com.travel.carRentals.database.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String token;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime tokenCreationDate;

}
