package com.example.user_manager.api.converter;

import com.example.user_manager.api.model.request.AuthenticationRequestModel;
import com.example.user_manager.api.model.request.AuthorizationRequestModel;
import com.example.user_manager.service.model.AuthenticateUserModel;
import com.example.user_manager.service.model.AuthorizeUserModel;
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
