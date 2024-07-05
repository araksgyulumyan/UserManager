package com.example.user_management.converter;

import com.example.user_management.entity.User;
import com.example.user_management.model.request.CreateUserRequestModel;
import org.springframework.stereotype.Component;

import static org.springframework.util.Assert.hasLength;
import static org.springframework.util.Assert.notNull;

@Component
public class UserConverter {

    private UserConverter() {
    }

    public User toUser(final CreateUserRequestModel createUserRequestModel) {
        assertCreateUserRequestModelExists(createUserRequestModel);
        User user = new User();
        user.setUsername(createUserRequestModel.getUsername());
        user.setPassword(createUserRequestModel.getPassword());
        return user;
    }

    private void assertCreateUserRequestModelExists(final CreateUserRequestModel createUserRequestModel) {
        notNull(createUserRequestModel, "Create user model should not be null");
        hasLength(createUserRequestModel.getUsername(), "Create user model username should not be empty");
        hasLength(createUserRequestModel.getPassword(), "Create user model password should not be empty");
    }
}
