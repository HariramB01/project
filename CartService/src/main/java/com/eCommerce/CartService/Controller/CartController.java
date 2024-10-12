package com.eCommerce.CartService.Controller;

import com.eCommerce.CartService.Entity.Cart;
import com.eCommerce.CartService.Service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService cartService;

    @PostMapping
    public Cart createCart(@RequestParam Long id) {
        logger.info("Creating cart for user ID: {}", id);
        Cart cart = new Cart();
        cart.setUId(id);
        cart.setCreatedAt(LocalDateTime.now());
        cart.setUpdatedAt(LocalDateTime.now());
        return cartService.createCart(cart);
    }

    @GetMapping("/{id}")
    public Cart getCartByUserId(@PathVariable Long id) {
        return cartService.getCartByUserId(id);
    }

}
