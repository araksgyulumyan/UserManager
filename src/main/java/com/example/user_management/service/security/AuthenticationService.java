package com.example.user_management.service.security;

import com.example.user_management.service.model.AuthenticateUserModel;
import com.example.user_management.service.model.AuthorizeUserModel;
import com.example.user_management.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

    /**
     * Authorize user
     *
     * @param authorizeUserModel model for registration
     * @return newly registered user
     */
    User authorize(final AuthorizeUserModel authorizeUserModel);

    /**
     * Authenticate user with credentials
     *
     * @param authenticateUserModel model for login
     * @return logged in User
     */
    UserDetails authenticate(final AuthenticateUserModel authenticateUserModel);
}
