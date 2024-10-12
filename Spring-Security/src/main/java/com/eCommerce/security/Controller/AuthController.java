package com.eCommerce.security.Controller;


import com.eCommerce.security.Request.LoginRequest;
import com.eCommerce.security.Response.LoginResponse;
import com.eCommerce.security.Service.AuthService;
import com.eCommerce.security.jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/security")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthService userService;

    @GetMapping("/hello")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userController(){
        return "Hello";
    }

    @GetMapping("/ur")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String user(){
        return "Hello! user";
    }

    @GetMapping("/ar")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String admin(){
        return "Hello! admin";
    }
//
//    @PostMapping("/create")
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        User createdUser = userService.createUser(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
//    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){

        logger.info("Checking the received login request data: {}", loginRequest.toString());


        Authentication authentication;
        try{
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken
                            (loginRequest.getUsername(), loginRequest.getPassword()));
        }
        catch (AuthenticationException e){
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad Credentials");
            map.put("status", false);
            return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
        }
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

//        String jwtToken = jwtUtils.generateTokenFromUsername(loginRequest.getUsername());

        String username = authentication.getName();
//        String role =authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority).collect(Collectors.toSet()).iterator().next();
        String jwtToken;

        try {
            jwtToken = jwtUtils.generateTokenFromUsername(username);
            logger.debug("JWT Token generated successfully for user: {}", username);
        } catch (Exception e) {
            logger.error("Failed to generate JWT Token for user: {}", username, e);
            return new ResponseEntity<>("Token generation failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LoginResponse response = new LoginResponse(loginRequest.getUsername(), jwtToken);

        return ResponseEntity.ok(response);

    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        if(jwtUtils.validateJwtToken(token)){
            return "Token is valid";
        }
        return "Invalid";
    }



}
