package com.eCommerce.WishlistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WishlistServiceImpl implements WishlistService{

    @Autowired
    private WishlistRepository wishlistRepository;


    @Override
    public Wishlist createWishlist(Long id) {
        Wishlist wishlist = new Wishlist();
        wishlist.setuId(id);
        wishlist.setCreatedAt(LocalDateTime.now());
        wishlist.setUpdatedAt(LocalDateTime.now());
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist addProductToWishlist(Long uId, Long productId) {
        Wishlist wishlist = wishlistRepository.findByUId(uId);
        if (wishlist!=null && !wishlist.getProductIds().contains(productId)) {
            wishlist.addProduct(productId);
            wishlist.setUpdatedAt(LocalDateTime.now());
            return wishlistRepository.save(wishlist);
        } else {
            throw new RuntimeException("Wishlist not found for user ID: " + uId);
        }
    }

    @Override
    public Wishlist getProductByUserId(Long id) {
        return wishlistRepository.findByUId(id);
    }
}
