package com.bookin.bookin.requestmodels;

import com.bookin.bookin.entity.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private String token;
    private String role;
    private String name;

    public JwtResponse(String jwtToken) {
        this.token=jwtToken;

    }


}
