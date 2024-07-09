package com.van_nemui.gr2_process.controller;

import com.van_nemui.gr2_process.model.AuthResponse;
import com.van_nemui.gr2_process.model.User;
import com.van_nemui.gr2_process.service.UserService;
import com.van_nemui.gr2_process.service.impl.UserServiceImpl;
import com.van_nemui.gr2_process.util.Cache;
import com.van_nemui.gr2_process.util.JwtUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/gr2/auth")
public class AuthController {

//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtUtil jwtUtil;


    private static final Logger logger = LogManager.getLogger(AuthController.class);

    @Autowired
    private UserService userServiceImpl = new UserServiceImpl();


//    @PostMapping("/authenticate")
//    public AuthResponse createAuthenticationToken(@RequestBody User authenticationRequest) throws Exception {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//            );
//        } catch (Exception e) {
//            throw new Exception("Incorrect username or password", e);
//        }
//
//        final UserDetails userDetails = userServiceImpl.loadUserByUsername(authenticationRequest.getUsername());
//        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
//
//        return new AuthResponse(jwt);
//    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User user1 = userServiceImpl.registerUser(user);
            if(user1 == null){
                logger.error("User is exist: " + user.getUsername());
                return ResponseEntity.badRequest().body("User is exist");
            }
            logger.info("Start reload data...");
            try {

                Map<String, User> userMap = userServiceImpl.loadAllUser();

                Cache.Users.ALLUSERS = userMap;
                logger.info("Reoad data success! " + userMap.size());
            }catch (Exception e){
                logger.error("Load data fail.", e);
            }
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            User loggedInUser = userServiceImpl.loginUser(user.getUsername(), user.getPassword());
            return ResponseEntity.ok(loggedInUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{userAdmin}/{userName}")
    public ResponseEntity<?> removeUser(@PathVariable String userAdmin, @PathVariable String userName){
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Done");
    }
}
