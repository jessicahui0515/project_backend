package com.FSSE2510_project_jessica.FSSE2510_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(Integer uid, Integer pid) {
        super("Cart Item Not Found By These Uid: " + uid + " & Pid: " + pid);
    }
}
