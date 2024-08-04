package com.project.CartService.Mapper;

import com.project.CartService.DTO.CartDTO;
import com.project.CartService.Entity.Cart;

public class ObjectMapper {
    public static Cart convertToCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setId(cartDTO.getId()); // Generally, this should be left null to let the database handle ID generation
        cart.setCartId(cartDTO.getCartId());
        cart.setItemId(cartDTO.getItemId());
        cart.setQuantity(cartDTO.getQuantity()); // Set quantity directly
        return cart;
    }
}
