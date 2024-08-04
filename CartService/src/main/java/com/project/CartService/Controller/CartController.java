package com.project.CartService.Controller;

import com.project.CartService.DTO.CartDTO;
import com.project.CartService.Entity.Cart;
import com.project.CartService.Service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody @Valid CartDTO cartDTO) {
        Cart cart = cartService.createCart(cartDTO);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }
}
