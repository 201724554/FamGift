package com.example.FamGift.common.exception;

import org.springframework.stereotype.Component;

@Component
public class CustomException extends RuntimeException {
    public CustomException() {}
    public CustomException(String message) { super(message); }

    public CustomException(ErrorMessage message) {
        super(message.getMessage());
    }
}
