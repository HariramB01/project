package com.eCommerce.WishlistService;

public interface WishlistService {
    Wishlist createWishlist(Long id);

    Wishlist addProductToWishlist(Long userId, Long productId);

    Wishlist getProductByUserId(Long id);
}
