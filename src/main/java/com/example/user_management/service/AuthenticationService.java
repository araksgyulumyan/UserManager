package com.example.user_management.service;

import com.example.user_management.dto.LoginUserDto;
import com.example.user_management.dto.RegisterUserDto;
import com.example.user_management.entity.User;

public interface AuthenticationService {

    /**
     * Register user
     *
     * @param registerUserDto model for registration
     * @return newly registered user
     */
    User register(final RegisterUserDto registerUserDto);

    /**
     * Login user with credentials
     *
     * @param loginUserDto model for login
     * @return logged in User
     */
    User login(final LoginUserDto loginUserDto);
}
