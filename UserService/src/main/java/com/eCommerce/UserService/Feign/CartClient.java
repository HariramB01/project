package com.eCommerce.UserService.Feign;


import com.eCommerce.CartService.Entity.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cart-service")
public interface CartClient {

    @PostMapping("/api/cart")
    Cart createCart(@RequestParam Long id);

    @GetMapping("/api/cart/{id}")
    Cart getCartByUserId(@PathVariable Long id);

    @DeleteMapping("/api/cart/{id}")
    void deleteCartByUserId(@PathVariable Long id);
}
