package com.example.user_management.api.exception;

public class UserNotFoundApiException extends ApiException {

    public UserNotFoundApiException(final Long id) {
        super("User with id:" + id + " is not found");
    }

    public UserNotFoundApiException(final String username) {
        super("User with username:" + username + " is not found");
    }
}
