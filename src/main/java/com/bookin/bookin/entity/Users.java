package com.bookin.bookin.entity;


import com.bookin.bookin.requestmodels.JwtRegister;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {

//    @Id
//    private Long id;


    @Id
    @Indexed(unique = true)
    private String username;


    private String password;


    private String name;


    private String role;


    private boolean active;

    public Users(JwtRegister jwtRegister) {
    }
}
