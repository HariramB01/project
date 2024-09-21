package com.eCommerce.InventoryService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomException {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoItemsAvailableException.class)
    public Map<String, String> handleNoItemsAvailable(NoItemsAvailableException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e) {
        return e.getMessage();
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateValueException.class)
    public String handleDuplicateValueException(DuplicateValueException e){
        return e.getMessage();
    }


}
