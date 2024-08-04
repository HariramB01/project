package com.project.CartService.Service;

import com.project.CartService.DTO.CartDTO;
import com.project.CartService.Entity.Cart;
import com.project.CartService.Mapper.ObjectMapper;
import com.project.CartService.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart createCart(CartDTO cartDTO) {
        // Check if a Cart with the same itemId already exists
        if (cartRepository.existsByItemId(cartDTO.getItemId())) {
            // Handle case where cart with this itemId already exists
            // Create a new cart with the updated quantity
            Cart existingCart = cartRepository.findByItemId(cartDTO.getItemId()).get();
            existingCart.setQuantity(existingCart.getQuantity() + cartDTO.getQuantity());
            return cartRepository.save(existingCart);
        } else {
            // Create a new cart
            Cart cart = ObjectMapper.convertToCart(cartDTO);
            return cartRepository.save(cart);
        }
    }
}
