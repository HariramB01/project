package com.eCommerce.UserService.Feign;

import com.eCommerce.UserService.DTO.Wishlist;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "wishlist-service")
public interface WishlistClient {

    @PostMapping("/api/wishlist")
    Wishlist createWishlist(@RequestParam Long id);

    @GetMapping("/api/wishlist")
    Wishlist getWishlistByUserId(@RequestParam Long id);

}
