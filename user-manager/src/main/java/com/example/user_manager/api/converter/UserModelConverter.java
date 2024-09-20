package com.example.user_manager.api.converter;

import com.example.user_manager.api.model.common.UserResponseModel;
import com.example.user_manager.entity.User;
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
