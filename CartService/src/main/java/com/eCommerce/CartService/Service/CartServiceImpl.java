package com.eCommerce.CartService.Service;


import com.eCommerce.CartService.Client.InventoryServiceClient;
import com.eCommerce.CartService.DTO.ProductDTO;
import com.eCommerce.CartService.Exception.ItemAlreadyExists;
import com.eCommerce.CartService.Exception.NoItemsAvailableException;
import com.eCommerce.CartService.Repository.CartRepository;
import com.eCommerce.CartService.Response.CartResponse;
import com.eCommerce.CartService.Entity.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {


    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);


    @Autowired
    private InventoryServiceClient inventoryServiceClient;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart createCart(Cart cart) {
//        Cart existingCart = cartRepository.findCartByUId(cart.getuId());
//        if (existingCart != null) {
//            throw new ItemAlreadyExists("Cart for this User ID " + cart.getuId() + " Already Exists");
//        }

        logger.info("Creating a new cart for User ID: {}", cart.getuId());
        System.out.println(cart);

        Cart savedCart = cartRepository.save(cart);
        CartResponse cartResponse = new CartResponse();
        cartResponse.setId(savedCart.getId());
        cartResponse.setuId(savedCart.getuId());
        cartResponse.setTotalAmount(savedCart.getTotalAmount());

        logger.info("Cart created successfully: {}", cartResponse);
        System.out.println(cartResponse);

        return cart;
    }


    @Override
    public Cart getCartByUserId(Long id) {
        Cart cart = cartRepository.findCartByUId(id);
        if(cart!=null){
            return cart;
        }
        throw new NoItemsAvailableException("Search item ID "+id+" is not available");
    }

    @Override
    public ResponseEntity<CartResponse> addProductToCart(Long cartId, Long productId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        if(!cart.getProductIds().contains(productId)) {
            cart.getProductIds().add(productId);
            cart.setUpdatedAt(LocalDateTime.now());
            ProductDTO product = inventoryServiceClient.getProductById(productId);
            cart.setTotalAmount(cart.getTotalAmount() + product.getPrice());
            cartRepository.save(cart);
            CartResponse cartResponse = new CartResponse();
            cartResponse.setId(cart.getId());
            cartResponse.setuId(cart.getuId());
            cartResponse.setTotalAmount(cart.getTotalAmount());
            cartResponse.setProducts(ConvertProductIdsToProducts(cart.getProductIds()));

            return ResponseEntity.ok(cartResponse);
        }
        throw new ItemAlreadyExists("Item is already available");
    }

    @Override
    public void removeProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        if (!cart.getProductIds().contains(productId)) {
            throw new RuntimeException("Product not found in the cart");
        }
        ProductDTO product = inventoryServiceClient.getProductById(productId);
        cart.setTotalAmount(cart.getTotalAmount()-product.getPrice());
        cart.getProductIds().remove(productId);
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);
        logger.info("Product with ID {} removed from cart with ID {}", productId, cartId);
    }


    private List<ProductDTO> ConvertProductIdsToProducts(List<Long> productIds) {
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Long productId : productIds) {
            ProductDTO product = inventoryServiceClient.getProductById(productId);
            productDTOs.add(product);
        }
        return productDTOs;
    }


}
