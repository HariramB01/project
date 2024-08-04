package com.project.UserService.Service;

import com.project.UserService.DTO.UserDTO;
import com.project.UserService.Entity.User;
import jakarta.validation.Valid;

public interface UserService {

    public User registerService(@Valid UserDTO user);

}
