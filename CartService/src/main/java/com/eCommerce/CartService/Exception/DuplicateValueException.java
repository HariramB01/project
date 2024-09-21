package com.eCommerce.CartService.Exception;

public class DuplicateValueException extends RuntimeException{

    public DuplicateValueException(String message){
        super(message);
    }

}
