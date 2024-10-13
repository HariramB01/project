package com.eCommerce.UserService.Service;


import com.eCommerce.UserService.Exception.CartWishlistServiceException;
import com.eCommerce.basedomains.DTO.UserDTO;
import com.eCommerce.UserService.Entity.User;
import com.eCommerce.UserService.Feign.CartClient;
import com.eCommerce.UserService.Feign.WishlistClient;
import com.eCommerce.UserService.Mapper.UserResponseMapper;
import com.eCommerce.UserService.Repository.UserRepository;
import com.eCommerce.UserService.Response.UserResponse;
import com.eCommerce.basedomains.DTO.UserNotificationDTO;
import feign.FeignException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Override
//    @Transactional // Rollback on failure
//    public UserResponse createUser(UserDTO user) {
//        User newUser = new User();
//        newUser.setUsername(user.getUsername());
//        newUser.setEmail(user.getEmail());
//        newUser.setContact(user.getContact());
//        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
//        newUser.setAddresses(new ArrayList<>());
//
//        User savedUser = userRepository.save(newUser);
//        try {
//            cartClient.createCart(savedUser.getId());
//            wishlistClient.createWishlist(savedUser.getId());
//            logger.info("Cart and wishlist created for user ID: {}", savedUser.getId());
//        } catch (FeignException e) {
//            logger.error("Feign error occurred while creating cart/wishlist for user ID {}: {}", savedUser.getId(), e.getMessage());
//            throw new RuntimeException("Failed to create cart/wishlist for user ID: " + savedUser.getId());
//        }
//
//        return userResponseMapper.userToUserResponseMapper(savedUser);
//    }

//    @Override
//    @Transactional  // Ensure transaction management
//    public UserResponse createUser(UserDTO user) {
//        User newUser = new User();
//        newUser.setUsername(user.getUsername());
//        newUser.setEmail(user.getEmail());
//        newUser.setContact(user.getContact());
//        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
//        newUser.setAddresses(new ArrayList<>());
//
//        // Save the user in the database
//        User savedUser = userRepository.save(newUser);
//        logger.info("Saved User: {}", savedUser);
//
//        // Attempt to create a cart
//        try {
//            cartClient.createCart(savedUser.getId());
//            logger.info("Cart created for user ID: {}", savedUser.getId());
//        } catch (FeignException e) {
//            logger.error("Error creating cart for user ID {}: {}", savedUser.getId(), e.getMessage());
//            throw new CartWishlistServiceException("Failed to create cart for user.");
//        }
//
//        // Attempt to create a wishlist
//        try {
//            wishlistClient.createWishlist(savedUser.getId());
//            logger.info("Wishlist created for user ID: {}", savedUser.getId());
//        } catch (FeignException e) {
//            logger.error("Error creating wishlist for user ID {}: {}", savedUser.getId(), e.getMessage());
//            throw new CartWishlistServiceException("Failed to create wishlist for user.");
//        }
//
//        // Return response
//        return userResponseMapper.userToUserResponseMapper(savedUser);
//    }

    @Override
    @Transactional  // Ensure transaction management
    public UserResponse createUser(UserDTO user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setContact(user.getContact());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setAddresses(new ArrayList<>());

        // Save the user in the database
        User savedUser = userRepository.save(newUser);
        logger.info("Saved User: {}", savedUser);

        // Attempt to create a cart
        try {
            cartClient.createCart(savedUser.getId());
            logger.info("Cart created for user ID: {}", savedUser.getId());
        } catch (FeignException e) {
            logger.error("Error creating cart for user ID {}: {}", savedUser.getId(), e.getMessage());
            throw new CartWishlistServiceException("Failed to create cart for user.");
        }

        // Attempt to create a wishlist
        try {
            wishlistClient.createWishlist(savedUser.getId());
            logger.info("Wishlist created for user ID: {}", savedUser.getId());
        } catch (FeignException e) {
            logger.error("Error creating wishlist for user ID {}: {}", savedUser.getId(), e.getMessage());
            throw new CartWishlistServiceException("Failed to create wishlist for user.");
        }

        // Return response
        return userResponseMapper.userToUserResponseMapper(savedUser);
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
