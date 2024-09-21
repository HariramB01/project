package com.eCommerce.UserService.Response;

import com.eCommerce.UserService.DTO.Cart;
import com.eCommerce.UserService.DTO.Wishlist;
import com.eCommerce.UserService.Entity.Address;

import java.util.List;

public class UserResponse {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String contact;
    private List<Address> addresses;
    private Cart cart;
//    private Wishlist wishlist;
    private WishlistResponse wishlistResponse;
    public UserResponse() {
    }

    public UserResponse(Long id, String username, String password, String email, String contact, List<Address> addresses, Cart cart, WishlistResponse wishlistResponse) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.contact = contact;
        this.addresses = addresses;
        this.cart = cart;
//        this.wishlist = wishlist;
        this.wishlistResponse = wishlistResponse;
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

//    public Wishlist getWishlist() {
//        return wishlist;
//    }
//
//    public void setWishlist(Wishlist wishlist) {
//        this.wishlist = wishlist;
//    }


    public WishlistResponse getWishlistResponse() {
        return wishlistResponse;
    }

    public void setWishlistResponse(WishlistResponse wishlistResponse) {
        this.wishlistResponse = wishlistResponse;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", addresses=" + addresses +
                ", cart=" + cart +
//                ", wishlist=" + wishlist +
                ", wishlistResponse=" + wishlistResponse +
                '}';
    }
}
