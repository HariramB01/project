package com.project.CartService.Exception;

public class ItemAlreadyExists extends RuntimeException {
    public ItemAlreadyExists(String message){
        super(message);
    }
}
