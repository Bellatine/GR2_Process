package com.van_nemui.gr2_process.service.impl;

import com.van_nemui.gr2_process.model.User;
import com.van_nemui.gr2_process.repository.UserRepository;
import com.van_nemui.gr2_process.service.UserService;
import com.van_nemui.gr2_process.util.Cache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User loginUser(String username, String password) {
        User user = Cache.Users.ALLUSERS.get(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            logger.error("no user map to username" + username );
            throw new RuntimeException("Invalid username or password");
        }
    }

    @Override
    public Map<String, User> loadAllUser() {
        List<User> listUsers = userRepository.findAll();
        Map<String, User> mapUsers = new HashMap<>();
        if(listUsers == null || listUsers.isEmpty()){
            logger.error("Load user fail! ");
            return null;
        }
        for(User user : listUsers){
            mapUsers.put(user.getUsername(), user);
        }
        return mapUsers;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("user".equals(username)) {
            return new org.springframework.security.core.userdetails.User("user", "$2a$10$J8yZ1nD5dzUviFb1cKwGuOiT1x3OtC8q3T8B7sZp06PjE5BsIdJKa", new ArrayList<>()); // password is "password" encoded
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}

