package com.example.user_manager.api.model.response;

import com.example.user_manager.api.model.common.ErrorModel;
import com.example.user_manager.api.model.common.ResponseModel;
import com.example.user_manager.api.model.common.UserResponseModel;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class AuthorizationResponseModel extends ResponseModel<UserResponseModel> {

    public AuthorizationResponseModel(final UserResponseModel model) {
        setBody(model);
    }

    public AuthorizationResponseModel(final ErrorModel errorModel) {
        getErrors().add(errorModel);
    }
}
