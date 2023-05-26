package com.bookin.bookin.dao;
import com.bookin.bookin.entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<Users, Long> {

    Users findByUsername(String username);

}