package com.FSSE2510_project_jessica.FSSE2510_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransactionStatusAlreadyUpdatedException extends RuntimeException {
    public TransactionStatusAlreadyUpdatedException(String status) {
        super("Transaction Status Already Updated: " + status);
    }
}
