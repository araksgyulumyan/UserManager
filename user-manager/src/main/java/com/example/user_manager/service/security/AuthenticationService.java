package com.example.user_manager.service.security;

import com.example.user_manager.entity.User;
import com.example.user_manager.service.model.AuthenticateUserModel;
import com.example.user_manager.service.model.AuthorizeUserModel;
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
