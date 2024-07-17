package com.example.user_management.api.exception;

public class UserUnauthorizedException extends RuntimeException {

    public UserUnauthorizedException(final Long userId) {
        super(String.format("User with ID %d is not authorized", userId));
    }
}
