package com.bookin.bookin.requestmodels;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.security.PrivateKey;

@Getter
@Setter
@AllArgsConstructor
public class JwtRegister {

    private String username;
    private String name;
    private String password;
}
