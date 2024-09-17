package com.example.user_management.service.exception;

public class UserNotFoundForIdException extends RuntimeException {

    public UserNotFoundForIdException(final Long id) {
        super("User with id:" + id + " is not found");
    }
}
