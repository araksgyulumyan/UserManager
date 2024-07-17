package com.example.user_management.api.controller;

import com.example.user_management.api.converter.AuthenticationModelConverter;
import com.example.user_management.api.model.common.AuthenticationCommonResponseModel;
import com.example.user_management.api.model.common.UserResponseModel;
import com.example.user_management.api.model.request.AuthenticationRequestModel;
import com.example.user_management.api.model.request.AuthorizationRequestModel;
import com.example.user_management.api.model.response.AuthenticationResponseModel;
import com.example.user_management.api.model.response.AuthorizationResponseModel;
import com.example.user_management.entity.User;
import com.example.user_management.service.security.AuthenticationService;
import com.example.user_management.service.security.impl.JwtServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtServiceImpl jwtService;
    private final AuthenticationService authenticationService;
    private final AuthenticationModelConverter authenticationModelConverter;

    @PostMapping("/register")
    public ResponseEntity<AuthorizationResponseModel> authorize(@Valid @RequestBody AuthorizationRequestModel authorizationRequestModel) {
        User user = authenticationService.authorize(authenticationModelConverter.toAuthorizeUserDto(authorizationRequestModel));

        UserResponseModel userResponseModel = new UserResponseModel();
        userResponseModel.setUserId(user.getId());
        userResponseModel.setUsername(user.getUsername());

        return new ResponseEntity<>(new AuthorizationResponseModel(userResponseModel), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseModel> authenticate(@RequestBody AuthenticationRequestModel authenticationRequestModel) {
        UserDetails authenticatedUser = authenticationService.authenticate(authenticationModelConverter.toAuthenticateUserDto(authenticationRequestModel));
        String jwtToken = jwtService.generateToken(authenticatedUser);

        AuthenticationCommonResponseModel authenticationCommonResponseModel = new AuthenticationCommonResponseModel();
        authenticationCommonResponseModel.setToken(jwtToken);
        authenticationCommonResponseModel.setExpiresIn(jwtService.getExpirationTime());
        return new ResponseEntity<>(new AuthenticationResponseModel(authenticationCommonResponseModel), HttpStatus.OK);
    }
}
