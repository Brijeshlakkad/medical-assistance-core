package com.medicalassistance.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUserRequest extends RuntimeException {
    public InvalidUserRequest() {
        super();
    }

    public InvalidUserRequest(String message) {
        super(message);
    }

    public InvalidUserRequest(String message, Throwable cause) {
        super(message, cause);
    }
}