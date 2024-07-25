package com.example.user_management.api.exception;

public class ApiException extends RuntimeException{

    public ApiException(String message) {
        super(message);
    }
}
