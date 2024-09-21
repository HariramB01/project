package com.eCommerce.InventoryService.Exception;

public class NoItemsAvailableException extends RuntimeException {

    public NoItemsAvailableException(String message) {
        super(message);
    }
}
