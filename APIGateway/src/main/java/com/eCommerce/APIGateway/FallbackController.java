package com.eCommerce.APIGateway;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/userServiceFallback")
    public ResponseEntity<String> userServiceFallback() {
        return ResponseEntity.status(503).body("User Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/cartServiceFallback")
    public ResponseEntity<String> cartServiceFallback() {
        return ResponseEntity.status(503).body("Cart Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/inventoryServiceFallback")
    public ResponseEntity<String> inventoryServiceFallback() {
        return ResponseEntity.status(503).body("Inventory Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/orderServiceFallback")
    public ResponseEntity<String> orderServiceFallback() {
        return ResponseEntity.status(503).body("Order Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/notificationServiceFallback")
    public ResponseEntity<String> notificationServiceFallback() {
        return ResponseEntity.status(503).body("Notification Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/securityServiceFallback")
    public ResponseEntity<String> securityServiceFallback() {
        return ResponseEntity.status(503).body("Security Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/wishlistServiceFallback")
    public ResponseEntity<String> wishlistServiceFallback() {
        return ResponseEntity.status(503).body("Wishlist Service is currently unavailable. Please try again later.");
    }
}

