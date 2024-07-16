package com.example.user_management.api.model.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationCommonResponseModel {

    private String token;

    private long expiresIn;
}
