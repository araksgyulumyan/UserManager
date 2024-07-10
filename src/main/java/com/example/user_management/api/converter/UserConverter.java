package com.example.user_management.api.converter;

import com.example.user_management.entity.User;
import com.example.user_management.api.model.request.CreateUserRequestModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConverter {

    public User toUser(final CreateUserRequestModel createUserRequestModel) {
        User user = new User();
        user.setUsername(createUserRequestModel.getUsername());
        user.setPassword(createUserRequestModel.getPassword());
        return user;
    }
}
