package com.FSSE2510_project_jessica.FSSE2510_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CartItemQuantityCannotBeNegativeException extends RuntimeException {
    public CartItemQuantityCannotBeNegativeException(Integer quantity) {
        super("Product Quantity cannot Less Than 1: "+ quantity);
    }
}
