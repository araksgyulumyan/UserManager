package com.example.user_management.api.converter;

import com.example.user_management.api.model.request.AuthenticationRequestModel;
import com.example.user_management.dto.AuthenticateUserDto;
import com.example.user_management.dto.AuthorizeUserDto;
import com.example.user_management.api.model.request.AuthorizationRequestModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationModelConverter {

    public AuthorizeUserDto toAuthorizeUserDto(final AuthorizationRequestModel authorizationRequestModel) {
        AuthorizeUserDto authorizeUserDto = new AuthorizeUserDto();
        authorizeUserDto.setUsername(authorizationRequestModel.getUsername());
        authorizeUserDto.setPassword(authorizationRequestModel.getPassword());
        return authorizeUserDto;
    }

    public AuthenticateUserDto toAuthenticateUserDto(final AuthenticationRequestModel authenticationRequestModel) {
        AuthenticateUserDto authenticateUserDto = new AuthenticateUserDto();
        authenticateUserDto.setUsername(authenticationRequestModel.getUsername());
        authenticateUserDto.setPassword(authenticationRequestModel.getPassword());
        return authenticateUserDto;
    }
}
