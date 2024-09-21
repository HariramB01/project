package com.eCommerce.CartService.Service;

import com.eCommerce.CartService.Response.CartResponse;
import com.eCommerce.CartService.Entity.Cart;
import org.springframework.http.ResponseEntity;

public interface CartService {
    Cart createCart(Cart cart);

    Cart getCartByUserId(Long id);

    ResponseEntity<CartResponse> addProductToCart(Long id, Long productId);

    void removeProductFromCart(Long cartId, Long productId);
}
