package com.project.UserService.Controller;

import com.project.UserService.DTO.UserDTO;
import com.project.UserService.Entity.User;
import com.project.UserService.Exception.CustomExceptionHandler;
import com.project.UserService.Service.AuthRequest;
import com.project.UserService.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public User registerUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.registerService(userDTO);
    }

    @GetMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            return userService.generateToken(authRequest.getUsername());
        }
        throw new RuntimeException("Invalid access");
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
        userService.vaidateToken(token);
        return "Token is valid";
    }


}
