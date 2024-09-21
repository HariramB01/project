package com.eCommerce.UserService.Mapper;

import com.eCommerce.UserService.DTO.Cart;
import com.eCommerce.UserService.DTO.ProductDTO;
import com.eCommerce.UserService.DTO.Wishlist;
import com.eCommerce.UserService.Entity.User;
import com.eCommerce.UserService.Feign.CartClient;
import com.eCommerce.UserService.Feign.WishlistClient;
import com.eCommerce.UserService.Response.UserResponse;
import com.eCommerce.UserService.Response.WishlistResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserResponseMapper {

    private static final Logger logger = LoggerFactory.getLogger(UserResponseMapper.class);

    @Autowired
    private CartClient cartClient;

    @Autowired
    private WishlistClient wishlistClient;

    public UserResponse userToUserResponseMapper(User user) {
        UserResponse ur = new UserResponse();
        ur.setId(user.getId());
        ur.setUsername(user.getUsername());
        ur.setPassword(user.getPassword());
        ur.setEmail(user.getEmail());
        ur.setContact(user.getContact());
        ur.setAddresses(user.getAddresses());

        logger.info("Creating cart and wishlist for user ID: {}", user.getId());

        Cart cart = cartClient.createCart(user.getId());
        logger.info("Cart created: {}", cart);

        Wishlist wishlist = wishlistClient.createWishlist(user.getId());
        logger.info("Wishlist created: {}", wishlist);

        WishlistResponseMapper wishlistResponseMapper = new WishlistResponseMapper();

        ur.setCart(cart);
        List<ProductDTO> products = wishlistResponseMapper.productIdsToProducts(wishlist.getProductIds());
        WishlistResponse wishlistResponse = new WishlistResponse();
        wishlistResponse.setProducts(products);
        System.out.println(wishlistResponse.toString());
        ur.setWishlistResponse(wishlistResponse);

        return ur;
    }

    public UserResponse getUserByIdToUserResponseMapper(User user) {
        UserResponse ur = new UserResponse();
        ur.setId(user.getId());
        ur.setUsername(user.getUsername());
        ur.setPassword(user.getPassword());
        ur.setEmail(user.getEmail());
        ur.setContact(user.getContact());
        ur.setAddresses(user.getAddresses());

        logger.info("Creating cart and wishlist for user ID: {}", user.getId());

        Cart cart = cartClient.getCartByUserId(user.getId());
        logger.info("Cart by User Id: {}", cart);

        Wishlist wishlist = wishlistClient.getWishlistByUserId(user.getId());
        logger.info("Wishlist by User Id: {}", wishlist);

        WishlistResponseMapper wishlistResponseMapper = new WishlistResponseMapper();

        ur.setCart(cart);
        List<ProductDTO> products = wishlistResponseMapper.productIdsToProducts(wishlist.getProductIds());
        WishlistResponse wishlistResponse = new WishlistResponse();
        wishlistResponse.setProducts(products);
        System.out.println(wishlistResponse.toString());
        ur.setWishlistResponse(wishlistResponse);

        return ur;
    }
}
