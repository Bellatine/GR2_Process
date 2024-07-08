package com.van_nemui.gr2_process.service;

import com.van_nemui.gr2_process.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;

public interface UserService extends UserDetailsService {
    User registerUser(User user);
    User loginUser(String username, String password);
    Map<String, User> loadAllUser();
}
