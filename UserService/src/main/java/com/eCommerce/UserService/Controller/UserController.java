package com.eCommerce.UserService.Controller;

import com.eCommerce.basedomains.DTO.UserDTO;
import com.eCommerce.UserService.Entity.User;
import com.eCommerce.UserService.Exception.MethodArgumentNotValidException;
import com.eCommerce.UserService.Feign.CartClient;
import com.eCommerce.UserService.Feign.WishlistClient;
import com.eCommerce.UserService.Mapper.UserResponseMapper;
import com.eCommerce.UserService.Response.FallBackResponse;
import com.eCommerce.UserService.Response.UserResponse;
import com.eCommerce.UserService.Service.UserService;
import com.eCommerce.basedomains.DTO.UserNotificationDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CartClient cartClient;

    @Autowired
    private WishlistClient wishlistClient;

    @Autowired
    private UserResponseMapper userResponseMapper;

    @PostMapping("/register")
<<<<<<< HEAD
    public ResponseEntity<UserResponse> registerUser(@RequestBody User user) {
        logger.info("Registering user: {}", user);
        UserResponse userResponse = userService.createUser(user);
        return ResponseEntity.ok(userResponse);
=======
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserDTO user) {
        try {
            UserResponse createdUser = userService.createUser(user);
            return ResponseEntity.ok(createdUser);
        } catch (MethodArgumentNotValidException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
>>>>>>> b253af3 (Cloud config & EDA)
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "userService", fallbackMethod = "getFallBackMethodUserById")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            UserResponse userResponse = userResponseMapper.getUserByIdToUserResponseMapper(userOptional.get());
            return ResponseEntity.ok(userResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<FallBackResponse> getFallBackMethodUserById(Long id, Throwable e) {
        logger.error("Fallback called for getUserById with id: {} due to: {}", id, e.getMessage());
        FallBackResponse fallbackResponse = new FallBackResponse();
        fallbackResponse.setId(id);
        fallbackResponse.setMessage("Service temporarily unavailable. Please try again later.");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(fallbackResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userService.getAllUser();
        List<UserResponse> userResponses = users.stream()
                .map(userResponseMapper::userToUserResponseMapper)
                .toList();
        return ResponseEntity.ok(userResponses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        try {
            cartClient.deleteCartByUserId(id);
        } catch (Exception e) {
            logger.error("Failed to delete cart for user {}: {}", id, e.getMessage());
        }
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/getEmail/{id}")
    public ResponseEntity<UserNotificationDTO> getUsernameAndEmail(@PathVariable Long id) {
        Optional<UserNotificationDTO> userNotificationDTO = userService.getUsernameAndEmail(id);
        return userNotificationDTO
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
