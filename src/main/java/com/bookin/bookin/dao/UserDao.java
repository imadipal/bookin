package com.bookin.bookin.dao;

import com.bookin.bookin.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDao {
    @Autowired
    UserRepository userRepository;

    public void addUser(Users user) { userRepository.save(user);}
    public Optional<Users> findByUsername(String username) {return Optional.ofNullable(userRepository.findByUsername(username));}
}
