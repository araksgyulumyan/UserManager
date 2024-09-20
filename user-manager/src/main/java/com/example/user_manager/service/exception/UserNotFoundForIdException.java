package com.example.user_manager.service.exception;

public class UserNotFoundForIdException extends RuntimeException {

    public UserNotFoundForIdException(final Long id) {
        super("User with id:" + id + " is not found");
    }
}
