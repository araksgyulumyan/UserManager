package com.example.user_management.api.model.response;

import com.example.user_management.api.model.ErrorCustomModel;
import com.example.user_management.api.model.ResponseModel;
import com.example.user_management.api.model.UserResponseModel;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class GetUserResponseModel extends ResponseModel<UserResponseModel> {

    public GetUserResponseModel(final UserResponseModel model) {
        setBody(model);
    }

    public GetUserResponseModel(final ErrorCustomModel errorModel) {
        getErrors().add(errorModel);
    }
}
