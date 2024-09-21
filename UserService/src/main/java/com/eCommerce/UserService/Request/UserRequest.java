package com.eCommerce.UserService.Request;

import com.eCommerce.UserService.Entity.Address;

import java.util.ArrayList;
import java.util.List;

public class UserRequest {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String contact;
    private List<Address> addresses;

    public UserRequest() {
        this.addresses = new ArrayList<>();
    }

    public UserRequest(Long id, String username, String password, String email, String contact) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.contact = contact;
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
