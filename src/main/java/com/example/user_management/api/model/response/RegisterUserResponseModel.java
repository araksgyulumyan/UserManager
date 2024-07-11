package com.example.user_management.api.model.response;

import com.example.user_management.api.model.common.ErrorCustomModel;
import com.example.user_management.api.model.common.ResponseModel;
import com.example.user_management.api.model.common.UserResponseModel;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class RegisterUserResponseModel extends ResponseModel<UserResponseModel> {

    public RegisterUserResponseModel(final UserResponseModel model) {
        setBody(model);
    }

    public RegisterUserResponseModel(final ErrorCustomModel errorModel) {
        getErrors().add(errorModel);
    }
}
