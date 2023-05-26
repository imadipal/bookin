package com.bookin.bookin.requestmodels;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {
    private String userName;
    private String password;
    public JwtRequest(JwtRegister jwtRequest) {
        this.userName= jwtRequest.getUsername();
        this.password=jwtRequest.getPassword();
    }
}
