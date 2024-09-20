package com.example.user_manager.api.model.response;

import com.example.user_manager.api.model.common.ErrorModel;
import com.example.user_manager.api.model.common.ResponseModel;
import com.example.user_manager.api.model.common.UserResponseModel;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class GetUserResponseModel extends ResponseModel<UserResponseModel> {

    public GetUserResponseModel(final UserResponseModel model) {
        setBody(model);
    }

    public GetUserResponseModel(final ErrorModel errorModel) {
        getErrors().add(errorModel);
    }
}
