package com.eCommerce.WishlistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {


    @Autowired
    private WishlistService wishlistService;

    @PostMapping
    public Wishlist createWishlist(@RequestParam Long id){
        return wishlistService.createWishlist(id);
    }

    @PostMapping("/addProduct")
    public Wishlist addProductToWishlist(@RequestParam Long userId, @RequestParam Long productId) {
        return wishlistService.addProductToWishlist(userId, productId);
    }

    @GetMapping
    public Wishlist getProductByUserId(@RequestParam Long id){
        return wishlistService.getProductByUserId(id);
    }
}
