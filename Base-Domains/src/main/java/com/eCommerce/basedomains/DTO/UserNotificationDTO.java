package com.eCommerce.basedomains.DTO;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserNotificationDTO {

    private String username;
    private String email;

    public UserNotificationDTO(String s) {
    }
}
