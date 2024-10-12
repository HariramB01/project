package com.project.NotificationService.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "From email is required")
    @Size(max = 255, message = "From email must be less than 255 characters")
    private String fromEmail;

    @NotBlank(message = "To email is required")
    @Size(max = 255, message = "To email must be less than 255 characters")
    private String toEmail;

    @NotBlank(message = "Subject is required")
    @Size(max = 255, message = "Subject must be less than 255 characters")
    private String subject;

    @NotBlank(message = "Body is required")
    @Size(max = 5000, message = "Body must be less than 5000 characters")
    private String body;

}
