package com.example.user_management.service.exception;

public class UserNotFoundForUsernameException extends RuntimeException {
    public UserNotFoundForUsernameException(final String username) {
        super("User with username:" + username + " is not found");
    }
}
