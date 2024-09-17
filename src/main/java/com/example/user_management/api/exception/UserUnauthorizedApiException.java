package com.example.user_management.api.exception;

public class UserUnauthorizedApiException extends ApiException {

    public UserUnauthorizedApiException(final Long userId) {
        super("User with ID:" + userId + "is not authorized");
    }
}
