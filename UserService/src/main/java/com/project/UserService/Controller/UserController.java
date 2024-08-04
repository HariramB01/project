package com.project.UserService.Controller;

import com.project.UserService.DTO.UserDTO;
import com.project.UserService.Entity.User;
import com.project.UserService.Exception.CustomExceptionHandler;
import com.project.UserService.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User registerUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.registerService(userDTO);
    }
}
