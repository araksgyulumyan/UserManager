package com.example.user_manager.api.exception;

import com.example.user_manager.api.model.common.ErrorModel;
import com.example.user_manager.api.model.common.ResponseModel;
import com.example.user_manager.api.model.response.AuthenticationResponseModel;
import com.example.user_manager.api.model.response.GetUserResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalApiExceptionHandler {

    @ExceptionHandler(UserNotFoundApiException.class)
    public final ResponseEntity<ResponseModel> handleUserNotFoundException(Exception ex) {
        ErrorModel errorCustomModel = new ErrorModel();
        String errorMessage = "An unexpected error occurred: " + ex.getMessage();
        errorCustomModel.setReason(errorMessage);
        errorCustomModel.setStatusCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(new GetUserResponseModel(errorCustomModel), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserUnauthorizedApiException.class)
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
