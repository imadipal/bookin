package com.bookin.bookin.service;

import com.bookin.bookin.dao.UserRepository;
import com.bookin.bookin.entity.Users;
import com.bookin.bookin.util.CustomUserDetails;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    public CustomUserDetails register(CustomUserDetails customUserDetails)
    {
        Users user =new Users();
        BeanUtils.copyProperties(customUserDetails,user);
        //user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user=userRepository.save(user);
        BeanUtils.copyProperties(user,customUserDetails);
        return customUserDetails;
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Users user=userRepository.findByUsername(userName);
//        if (userName.equals("John")) {
//            return new User("John", "secret", new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User does not Exist!");
//        }
            if(user==null){
                throw new UsernameNotFoundException("User does not exist!");
            }

        CustomUserDetails customUserDetails=new CustomUserDetails(user);
        BeanUtils.copyProperties(user,customUserDetails);
        return customUserDetails;


//        @Autowired
//        UserDao userDao;
//
//        @Override
//        public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//            Optional<Users> user= userDao.findByUsername(userName);
//            user.orElseThrow(()-> new UsernameNotFoundException("Not found : "+ userName));
//
//            return user.map(CustomUserDetails::new).get();
//        }
    }
}

