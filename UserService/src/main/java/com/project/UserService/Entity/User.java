package com.project.UserService.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "UserTable", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "contact")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull(message = "username should not be null")
//    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
    private String username;

//    @NotNull(message = "password should not be null")
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{4,20}$",
//            message = "Password must be between 4 and 20 characters, contain at least one digit, one special character, and no spaces")
    private String password;

//    @NotNull(message = "Email should not be null")
//    @Pattern(regexp = "^[^@\\s]{4,}@(gmail\\.com|yahoo\\.com)$",
//            message = "Email must be at least 4 characters before '@' and end with '@gmail.com' or '@yahoo.com'")
    private String email;

//    @NotNull(message = "Contact should not be null")
//    @Pattern(regexp = "^\\d{10}$", message = "Contact number must be exactly 10 digits")
    private String contact;

    public User() {
    }

    public User(Long id, String username, String password, String email, String contact) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
