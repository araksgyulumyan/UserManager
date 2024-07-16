package com.example.user_management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizeUserDto {

    private String username;
    private String password;
}
