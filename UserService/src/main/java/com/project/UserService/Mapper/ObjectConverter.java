package com.project.UserService.Mapper;

import com.project.UserService.DTO.UserDTO;
import com.project.UserService.Entity.User;

public class ObjectConverter {

    public static User convertUserDTO(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setContact(userDTO.getContact());
        return user;
    }

}
