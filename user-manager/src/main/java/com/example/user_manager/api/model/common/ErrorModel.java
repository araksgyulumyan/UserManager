package com.example.user_manager.api.model.common;

import lombok.Data;

@Data
public class ErrorModel {

    String reason;

    int statusCode;

}
