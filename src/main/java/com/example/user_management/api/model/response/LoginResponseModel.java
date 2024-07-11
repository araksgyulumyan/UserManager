package com.example.user_management.api.model.response;

import com.example.user_management.api.model.common.AuthenticationResponseModel;
import com.example.user_management.api.model.common.ErrorCustomModel;
import com.example.user_management.api.model.common.ResponseModel;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class LoginResponseModel extends ResponseModel<AuthenticationResponseModel> {

    public LoginResponseModel(final AuthenticationResponseModel model) {
        setBody(model);
    }

    public LoginResponseModel(final ErrorCustomModel errorModel) {
        getErrors().add(errorModel);
    }

}
