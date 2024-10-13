package com.eCommerce.UserService.Controller;

import com.eCommerce.UserService.Exception.CartWishlistServiceException;
import com.eCommerce.UserService.Mapper.UserResponseWrapper;
import com.eCommerce.basedomains.DTO.UserDTO;
import com.eCommerce.UserService.Entity.User;
import com.eCommerce.UserService.Exception.MethodArgumentNotValidException; // Consider handling globally
import com.eCommerce.UserService.Feign.CartClient;
import com.eCommerce.UserService.Feign.WishlistClient;
import com.eCommerce.UserService.Mapper.UserResponseMapper;
import com.eCommerce.UserService.Response.FallBackResponse;
import com.eCommerce.UserService.Response.UserResponse;
import com.eCommerce.UserService.Service.UserService;
import com.eCommerce.basedomains.DTO.UserNotificationDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.cloud.client.hostname}")
    private String hostName;

    @GetMapping("/info")
    public String info() {
        return "Handled by instance: " + appName + " at " + hostName;
    }

    @Autowired
    private UserService userService;

    @Autowired
    private CartClient cartClient;

    @Autowired
    private WishlistClient wishlistClient;

    @Autowired
    private UserResponseMapper userResponseMapper;

    @PostMapping("/register")
    @Transactional // Ensure rollback on failure
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserDTO user) {
        try {
            UserResponse createdUser = userService.createUser(user);
            logger.info("User registered successfully: {}", createdUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (MethodArgumentNotValidException e) {
            logger.error("Validation failed during user registration: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (CartWishlistServiceException e) {
            logger.error("Failed to register user: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (Exception e) {
            logger.error("User registration failed: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User registration failed.");
        }
    }



    @GetMapping("/{id}")
    @CircuitBreaker(name = "userService", fallbackMethod = "getFallBackMethodUserById")
    public ResponseEntity<UserResponseWrapper> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            UserResponse userResponse = userResponseMapper.getUserByIdToUserResponseMapper(userOptional.get());
            logger.info("Retrieved user: {}", userResponse);
            return ResponseEntity.ok(new UserResponseWrapper(userResponse));
        } else {
            logger.warn("User not found with id: {}", id);
            FallBackResponse fallBackResponse = new FallBackResponse(id, "User not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserResponseWrapper(fallBackResponse));
        }
    }

    // Fallback method remains unchanged
    public ResponseEntity<UserResponseWrapper> getFallBackMethodUserById(Long id, Throwable e) {
        logger.error("Fallback called for getUserById with id: {} due to: {}", id, e.getMessage());
        FallBackResponse fallbackResponse = new FallBackResponse();
        fallbackResponse.setId(id);
        fallbackResponse.setMessage("Service temporarily unavailable. Please try again later.");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new UserResponseWrapper(fallbackResponse));
    }


    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userService.getAllUser();
        List<UserResponse> userResponses = users.stream()
                .map(userResponseMapper::userToUserResponseMapper)
                .toList();
        logger.info("Retrieved all users. Total users: {}", userResponses.size());
        return ResponseEntity.ok(userResponses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        try {
            cartClient.deleteCartByUserId(id);
            logger.info("Deleted cart for user with id: {}", id);
        } catch (Exception e) {
            logger.error("Failed to delete cart for user {}: {}", id, e.getMessage());
        }
        userService.deleteUserById(id);
        logger.info("Deleted user with id: {}", id);
        return ResponseEntity.ok("User deleted successfully.");
    }

    @GetMapping("/getEmail/{id}")
    public ResponseEntity<UserNotificationDTO> getUsernameAndEmail(@PathVariable Long id) {
        Optional<UserNotificationDTO> userNotificationDTO = userService.getUsernameAndEmail(id);
        return userNotificationDTO
                .map(user -> {
                    logger.info("Retrieved user notification info: {}", user);
                    return ResponseEntity.ok(user);
                })
                .orElseGet(() -> {
                    logger.warn("User notification info not found for id: {}", id);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new UserNotificationDTO("User not found."));
                });
    }
}
