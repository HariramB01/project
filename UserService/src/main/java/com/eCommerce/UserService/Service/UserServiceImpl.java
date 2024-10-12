package com.eCommerce.UserService.Service;


import com.eCommerce.basedomains.DTO.UserDTO;
import com.eCommerce.UserService.Entity.User;
import com.eCommerce.UserService.Feign.CartClient;
import com.eCommerce.UserService.Feign.WishlistClient;
import com.eCommerce.UserService.Mapper.UserResponseMapper;
import com.eCommerce.UserService.Repository.UserRepository;
import com.eCommerce.UserService.Response.UserResponse;
import com.eCommerce.basedomains.DTO.UserNotificationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
=======

>>>>>>> b253af3 (Cloud config & EDA)
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private RoleRepository roleRepository;

    @Autowired
    private CartClient cartClient;

    @Autowired
    private WishlistClient wishlistClient;

    @Autowired
    private UserResponseMapper userResponseMapper;

    @Override
<<<<<<< HEAD
    public UserResponse createUser(User user) {
        user.setAddresses(new ArrayList<>());
        User savedUser = userRepository.save(user);
=======
    public UserResponse createUser(UserDTO user) {
        User newUser = new User();
//        Role role = new Role();
//        if(user.getRole().equals("USER"))
//            role = roleRepository.findByRole("ROLE_USER");
//        else if(user.getRole().equals("ADMIN"))
//            role = roleRepository.findByRole("ROLE_ADMIN");
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setContact(user.getContact());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setAddresses(new ArrayList<>());
//        newUser.setRoles(role);
        User savedUser = userRepository.save(newUser);
>>>>>>> b253af3 (Cloud config & EDA)
        logger.info("Saved User: {}", savedUser);
        UserResponse userResponse = userResponseMapper.userToUserResponseMapper(savedUser);
        logger.info("UserResponse created: {}", userResponse);
        return userResponse;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<UserNotificationDTO> getUsernameAndEmail(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserNotificationDTO(user.getUsername(), user.getEmail()));
    }

}
