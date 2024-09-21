package com.eCommerce.InventoryService.Exception;

public class DuplicateValueException extends RuntimeException{

    public DuplicateValueException(String message){
        super(message);
    }

}
