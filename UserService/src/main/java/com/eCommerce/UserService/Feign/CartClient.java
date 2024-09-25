package com.eCommerce.UserService.Feign;


import com.eCommerce.CartService.Entity.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cart-service", url = "http://localhost:8082/api/cart")
public interface CartClient {

    @PostMapping
    Cart createCart(@RequestParam Long id);

    @GetMapping("/{id}")
    Cart getCartByUserId(@PathVariable Long id);

    @DeleteMapping("/{id}")
    void deleteCartByUserId(@PathVariable Long id);
}
