package com.example.user_management.api.exception;

import com.example.user_management.api.model.common.ErrorModel;
import com.example.user_management.api.model.common.ResponseModel;
import com.example.user_management.api.model.response.AuthenticationResponseModel;
import com.example.user_management.api.model.response.GetUserResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ResponseModel> handleUserNotFoundException(Exception ex) {
        ErrorModel errorCustomModel = new ErrorModel();
        String errorMessage = "An unexpected error occurred: " + ex.getMessage();
        errorCustomModel.setReason(errorMessage);
        errorCustomModel.setStatusCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(new GetUserResponseModel(errorCustomModel), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserUnauthorizedException.class)
    public final ResponseEntity<ResponseModel> handleUserNotUnauthorizedException(Exception ex) {
        ErrorModel errorCustomModel = new ErrorModel();
        String errorMessage = "An unexpected error occurred: " + ex.getMessage();
        errorCustomModel.setReason(errorMessage);
        errorCustomModel.setStatusCode(HttpStatus.UNAUTHORIZED.value());

        return new ResponseEntity<>(new AuthenticationResponseModel(errorCustomModel), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<ErrorModel> handleAllOtherExceptions(Exception ex) {
        ErrorModel errorCustomModel = new ErrorModel();
        String errorMessage = "An unexpected error occurred: " + ex.getMessage();
        errorCustomModel.setReason(errorMessage);
        errorCustomModel.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(errorCustomModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
