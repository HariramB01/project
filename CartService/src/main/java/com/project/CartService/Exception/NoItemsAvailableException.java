package com.project.CartService.Exception;

public class NoItemsAvailableException extends RuntimeException {

    public NoItemsAvailableException(String message) {
        super(message);
    }
}
