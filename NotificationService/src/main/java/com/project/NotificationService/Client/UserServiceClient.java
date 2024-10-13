package com.project.NotificationService.Client;


import com.eCommerce.basedomains.DTO.UserNotificationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE", path = "/api/v1/user")
public interface UserServiceClient {
    @GetMapping("/getEmail/{id}")
    ResponseEntity<UserNotificationDTO> getUsernameAndEmail(@PathVariable Long id);
}
