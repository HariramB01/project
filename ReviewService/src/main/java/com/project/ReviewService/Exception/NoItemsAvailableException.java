package com.project.ReviewService.Exception;

public class NoItemsAvailableException extends RuntimeException{

    public NoItemsAvailableException(String message){
        super(message);
    }

}
