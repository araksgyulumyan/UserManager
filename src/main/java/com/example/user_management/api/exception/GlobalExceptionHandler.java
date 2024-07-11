package com.example.user_management.api.exception;

import com.example.user_management.api.model.common.ErrorCustomModel;
import com.example.user_management.api.model.common.ResponseModel;
import com.example.user_management.api.model.response.LoginResponseModel;
import com.example.user_management.api.model.response.GetUserResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ResponseModel> handleUserNotFoundException(Exception ex) {
        ErrorCustomModel errorCustomModel = new ErrorCustomModel();
        String errorMessage = "An unexpected error occurred: " + ex.getMessage();
        errorCustomModel.setReason(errorMessage);
        errorCustomModel.setStatusCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(new GetUserResponseModel(errorCustomModel), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserUnauthorizedException.class)
    public final ResponseEntity<ResponseModel> handleUserNotUnauthorizedException(Exception ex) {
        ErrorCustomModel errorCustomModel = new ErrorCustomModel();
        String errorMessage = "An unexpected error occurred: " + ex.getMessage();
        errorCustomModel.setReason(errorMessage);
        errorCustomModel.setStatusCode(HttpStatus.UNAUTHORIZED.value());

        return new ResponseEntity<>(new LoginResponseModel(errorCustomModel), HttpStatus.UNAUTHORIZED);
    }
}
