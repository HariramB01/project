package com.eCommerce.UserService.Feign;

import com.eCommerce.basedomains.DTO.WishlistDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "wishlist-service", url = "http://localhost:8084/api/wishlist")
public interface WishlistClient {

<<<<<<< HEAD
    @PostMapping
    Wishlist createWishlist(@RequestParam Long id);

    @GetMapping
    Wishlist getWishlistByUserId(@RequestParam Long id);
=======
    @PostMapping("/api/wishlist")
    WishlistDTO createWishlist(@RequestParam Long id);

    @GetMapping("/api/wishlist")
    WishlistDTO getWishlistByUserId(@RequestParam Long id);
>>>>>>> b253af3 (Cloud config & EDA)

}
