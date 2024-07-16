package com.example.user_management.service;

import com.example.user_management.dto.AuthenticateUserDto;
import com.example.user_management.dto.AuthorizeUserDto;
import com.example.user_management.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

    /**
     * Authorize user
     *
     * @param authorizeUserDto model for registration
     * @return newly registered user
     */
    User authorize(final AuthorizeUserDto authorizeUserDto);

    /**
     * Authenticate user with credentials
     *
     * @param authenticateUserDto model for login
     * @return logged in User
     */
    UserDetails authenticate(final AuthenticateUserDto authenticateUserDto);
}
