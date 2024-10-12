package com.eCommerce.UserService.Service;


import com.eCommerce.basedomains.DTO.UserDTO;
import com.eCommerce.UserService.Entity.User;
import com.eCommerce.UserService.Response.UserResponse;
import com.eCommerce.basedomains.DTO.UserNotificationDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserResponse createUser(UserDTO user);

    Optional<User> getUserById(Long id);

    List<User> getAllUser();

    void deleteUserById(Long id);

    Optional<UserNotificationDTO> getUsernameAndEmail(Long id);
}
