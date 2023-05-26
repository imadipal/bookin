package com.bookin.bookin.util;

import com.bookin.bookin.dao.UserRepository;
import com.bookin.bookin.entity.Users;
import com.bookin.bookin.requestmodels.AuthorityModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
public class CustomUserDetails implements UserDetails {
    private String userName;
    private String password;

    private String name;

    private String role="user";
    private boolean active=true;
    private List<GrantedAuthority> authorities;



    public CustomUserDetails(Users user) {
        this.userName=user.getUsername();
        this.password=user.getPassword();
        this.active=user.isActive();
//        this.authorities=Arrays.stream(user.getRole().split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
    }
    


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}