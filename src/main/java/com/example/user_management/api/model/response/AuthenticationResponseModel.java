package com.example.user_management.api.model.response;

import com.example.user_management.api.model.common.AuthenticationCommonResponseModel;
import com.example.user_management.api.model.common.ErrorModel;
import com.example.user_management.api.model.common.ResponseModel;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class AuthenticationResponseModel extends ResponseModel<AuthenticationCommonResponseModel> {

    public AuthenticationResponseModel(final AuthenticationCommonResponseModel model) {
        setBody(model);
    }

    public AuthenticationResponseModel(final ErrorModel errorModel) {
        getErrors().add(errorModel);
    }

}
