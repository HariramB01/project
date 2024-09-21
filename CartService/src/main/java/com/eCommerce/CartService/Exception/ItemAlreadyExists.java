package com.eCommerce.CartService.Exception;

public class ItemAlreadyExists extends RuntimeException {
    public ItemAlreadyExists(String message){
        super(message);
    }
}
