package com.example.user_management.api.model.common;

import lombok.Data;

@Data
public class ErrorModel {

    String reason;

    int statusCode;

}
