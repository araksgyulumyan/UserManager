package com.example.user_manager.service;

import com.example.user_manager.entity.User;
import com.example.user_manager.service.model.AuthorizeUserModel;
import org.springframework.stereotype.Component;

import static org.springframework.util.Assert.hasLength;
import static org.springframework.util.Assert.notNull;

@Component
public class UserAssertionHelper {

    public void assertUserNotNullAndPropertiesNotEmpty(final User user) {
        notNull(user, "User should not be null");
        asserUsernameNotEmpty(user.getUsername());
        assertPasswordNotEmpty(user.getPassword());
    }

    public void assertRegisterUserDtoNotNullAndPropertiesNotEmpty(final AuthorizeUserModel authorizeUserModel) {
        notNull(authorizeUserModel, "User should not be null");
        asserUsernameNotEmpty(authorizeUserModel.getUsername());
        assertPasswordNotEmpty(authorizeUserModel.getPassword());
    }

    public void asserUsernameNotEmpty(final String username) {
        hasLength(username, "Username should not be empty");
    }

    public void assertPasswordNotEmpty(final String password) {
        hasLength(password, "User password should not be empty");
    }

    public void assertUserIdNotNull(final Long id) {
        notNull(id, "User id should not be null");
    }
}
