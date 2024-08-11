package com.project.CartService.Service;

import com.project.CartService.Client.Item;
import com.project.CartService.DTO.CartDTO;
import com.project.CartService.Entity.Cart;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface CartService {
    Cart createCart(@Valid CartDTO cartDTO);

    Item getCartItemFromCartByItemId(Long itemId, Long id);
}
