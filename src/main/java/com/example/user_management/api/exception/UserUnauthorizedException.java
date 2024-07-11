package com.example.user_management.api.exception;

public class UserUnauthorizedException extends RuntimeException {

    public UserUnauthorizedException(String message) {
        super(message);
    }
}
