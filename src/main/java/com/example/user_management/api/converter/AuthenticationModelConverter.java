package com.example.user_management.api.converter;

import com.example.user_management.api.model.request.AuthenticationRequestModel;
import com.example.user_management.service.model.AuthenticateUserModel;
import com.example.user_management.service.model.AuthorizeUserModel;
import com.example.user_management.api.model.request.AuthorizationRequestModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationModelConverter {

    public AuthorizeUserModel toAuthorizeUserDto(final AuthorizationRequestModel authorizationRequestModel) {
        AuthorizeUserModel authorizeUserModel = new AuthorizeUserModel();
        authorizeUserModel.setUsername(authorizationRequestModel.getUsername());
        authorizeUserModel.setPassword(authorizationRequestModel.getPassword());
        return authorizeUserModel;
    }

    public AuthenticateUserModel toAuthenticateUserDto(final AuthenticationRequestModel authenticationRequestModel) {
        AuthenticateUserModel authenticateUserModel = new AuthenticateUserModel();
        authenticateUserModel.setUsername(authenticationRequestModel.getUsername());
        authenticateUserModel.setPassword(authenticationRequestModel.getPassword());
        return authenticateUserModel;
    }
}
