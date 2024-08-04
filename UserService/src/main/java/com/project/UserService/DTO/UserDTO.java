package com.project.UserService.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

public class UserDTO {

    private Long id;

    @NotNull(message = "Username should not be null")
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
    private String username;

    @NotNull(message = "Password should not be null")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{4,20}$",
            message = "Password must be between 4 and 20 characters, contain at least one digit, one special character, and no spaces")
    private String password;

    @NotNull(message = "Email should not be null")
    @Pattern(regexp = "^[^@\\s]{4,}@(gmail\\.com|yahoo\\.com)$",
            message = "Email must be at least 4 characters before '@' and end with '@gmail.com' or '@yahoo.com'")
    private String email;

    @NotNull(message = "Contact should not be null")
    @Pattern(regexp = "^\\d{10}$", message = "Contact number must be exactly 10 digits")
    private String contact;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Username should not be null") @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters") String getUsername() {
        return username;
    }

    public void setUsername(@NotNull(message = "Username should not be null") @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters") String username) {
        this.username = username;
    }

    public @NotNull(message = "Password should not be null") @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{4,20}$",
            message = "Password must be between 4 and 20 characters, contain at least one digit, one special character, and no spaces") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "Password should not be null") @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{4,20}$",
            message = "Password must be between 4 and 20 characters, contain at least one digit, one special character, and no spaces") String password) {
        this.password = password;
    }

    public @NotNull(message = "Email should not be null") @Pattern(regexp = "^[^@\\s]{4,}@(gmail\\.com|yahoo\\.com)$",
            message = "Email must be at least 4 characters before '@' and end with '@gmail.com' or '@yahoo.com'") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "Email should not be null") @Pattern(regexp = "^[^@\\s]{4,}@(gmail\\.com|yahoo\\.com)$",
            message = "Email must be at least 4 characters before '@' and end with '@gmail.com' or '@yahoo.com'") String email) {
        this.email = email;
    }

    public @NotNull(message = "Contact should not be null") @Pattern(regexp = "^\\d{10}$", message = "Contact number must be exactly 10 digits") String getContact() {
        return contact;
    }

    public void setContact(@NotNull(message = "Contact should not be null") @Pattern(regexp = "^\\d{10}$", message = "Contact number must be exactly 10 digits") String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
