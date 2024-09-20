package com.example.user_manager.api.controller;

import com.example.user_manager.api.converter.AuthenticationModelConverter;
import com.example.user_manager.api.model.common.AuthenticationCommonResponseModel;
import com.example.user_manager.api.model.common.UserResponseModel;
import com.example.user_manager.api.model.request.AuthenticationRequestModel;
import com.example.user_manager.api.model.request.AuthorizationRequestModel;
import com.example.user_manager.api.model.response.AuthenticationResponseModel;
import com.example.user_manager.api.model.response.AuthorizationResponseModel;
import com.example.user_manager.entity.User;
import com.example.user_manager.service.security.AuthenticationService;
import com.example.user_manager.service.security.impl.JwtServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

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
        LOGGER.error("error tests");
        LOGGER.info("info tests");
        LOGGER.debug("debug tests");
        UserDetails authenticatedUser = authenticationService.authenticate(authenticationModelConverter.toAuthenticateUserDto(authenticationRequestModel));
        String jwtToken = jwtService.generateToken(authenticatedUser);

        AuthenticationCommonResponseModel authenticationCommonResponseModel = new AuthenticationCommonResponseModel();
        authenticationCommonResponseModel.setToken(jwtToken);
        authenticationCommonResponseModel.setExpiresIn(jwtService.getExpirationTime());
        return new ResponseEntity<>(new AuthenticationResponseModel(authenticationCommonResponseModel), HttpStatus.OK);
    }
}
