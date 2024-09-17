package com.example.user_management.api.converter;

import com.example.user_management.api.model.common.UserResponseModel;
import com.example.user_management.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserModelConverter {

    public UserResponseModel toUserResponseModel(final User user) {
        UserResponseModel userResponseModel = new UserResponseModel();
        userResponseModel.setUserId(user.getId());
        userResponseModel.setUsername(user.getUsername());
        return userResponseModel;
    }
}
