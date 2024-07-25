package com.example.user_management.api.exception;

public class UserUnauthorizedApiException extends ApiException {

    public UserUnauthorizedApiException(final Long userId) {
        super(String.format("User with ID %d is not authorized", userId));
    }
}
