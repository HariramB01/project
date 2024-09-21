package com.eCommerce.CartService.Exception;

public class NoItemsAvailableException extends RuntimeException {

    public NoItemsAvailableException(String message) {
        super(message);
    }
}
