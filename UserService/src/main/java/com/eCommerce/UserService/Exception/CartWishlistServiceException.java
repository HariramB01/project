package com.eCommerce.UserService.Exception;

public class CartWishlistServiceException extends RuntimeException {
    public CartWishlistServiceException(String message) {
        super(message);
    }

    public CartWishlistServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
