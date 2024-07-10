package com.example.user_management.api.exception;

import com.example.user_management.api.model.ErrorCustomModel;
import com.example.user_management.api.model.ResponseModel;
import com.example.user_management.api.model.response.GetUserResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ResponseModel> handleUserNotFoundException(Exception ex, WebRequest request) {
        ErrorCustomModel errorCustomModel = new ErrorCustomModel();
        String errorMessage = "An unexpected error occurred: " + ex.getMessage();
        errorCustomModel.setReason(errorMessage);
        errorCustomModel.setStatusCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(new GetUserResponseModel(errorCustomModel), HttpStatus.NOT_FOUND);
    }
}
