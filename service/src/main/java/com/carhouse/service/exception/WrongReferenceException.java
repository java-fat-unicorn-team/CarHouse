package com.carhouse.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
public class WrongReferenceException extends RuntimeException {
    public WrongReferenceException(String message) {
        super(message);
    }
}
