package com.eCommerce.UserService.Service;


import com.eCommerce.UserService.Entity.User;
import com.eCommerce.UserService.Request.UserRequest;
import com.eCommerce.UserService.Response.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserResponse createUser(User user);

    Optional<User> getUserById(Long id);

    List<User> getAllUser();

    void deleteUserById(Long id);

}
