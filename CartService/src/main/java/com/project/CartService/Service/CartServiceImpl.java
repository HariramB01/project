package com.project.CartService.Service;

import com.project.CartService.Client.Item;
import com.project.CartService.DTO.CartDTO;
import com.project.CartService.Entity.Cart;
import com.project.CartService.Exception.ItemAlreadyExists;
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
        if (cartRepository.existsByItemId(cartDTO.getItemId())) {
            cartRepository.delete(cartDTO.getCartId());
            throw new ItemAlreadyExists("Item already exists in the cart");
        } else {
            Cart cart = ObjectMapper.convertToCart(cartDTO);
            return cartRepository.save(cart);
        }
    }

    @Override
    public Item getCartItemFromCartByItemId(Long itemId, Long id) {
        return cartRepository.findByItemIdAndId(itemId,id);
    }
}
