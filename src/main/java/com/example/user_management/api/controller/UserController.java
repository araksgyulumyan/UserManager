package com.example.user_management.api.controller;

import com.example.user_management.api.converter.UserModelConverter;
import com.example.user_management.api.model.common.AuthenticationResponseModel;
import com.example.user_management.api.model.common.UserResponseModel;
import com.example.user_management.api.model.request.LoginRequestModel;
import com.example.user_management.api.model.response.LoginResponseModel;
import com.example.user_management.api.model.response.GetUserResponseModel;
import com.example.user_management.entity.User;
import com.example.user_management.api.model.request.RegisterUserRequestModel;
import com.example.user_management.api.model.response.RegisterUserResponseModel;
import com.example.user_management.service.impl.AuthenticationServiceImpl;
import com.example.user_management.service.impl.JwtServiceImpl;
import com.example.user_management.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final AuthenticationServiceImpl authenticationService;
    private final JwtServiceImpl jwtService;
    private final UserModelConverter userConverter;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseModel> registerUser(@Valid @RequestBody RegisterUserRequestModel createUserRequestModel) {
        User user = authenticationService.register(userConverter.toRegisterUserDto(createUserRequestModel));

        UserResponseModel userResponseModel = new UserResponseModel();
        userResponseModel.setUserId(user.getId());
        userResponseModel.setUsername(user.getUsername());

        return new ResponseEntity<>(new RegisterUserResponseModel(userResponseModel), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseModel> login(@RequestBody LoginRequestModel loginRequestModel) {
        User authenticatedUser = authenticationService.login(userConverter.toLoginUserDto(loginRequestModel));
        String jwtToken = jwtService.generateToken(authenticatedUser);


        AuthenticationResponseModel authenticationResponseModel = new AuthenticationResponseModel();
        authenticationResponseModel.setToken(jwtToken);
        authenticationResponseModel.setExpiresIn(jwtService.getExpirationTime());
        return new ResponseEntity<>(new LoginResponseModel(authenticationResponseModel), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GetUserResponseModel> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);

        UserResponseModel userResponseModel = new UserResponseModel();
        userResponseModel.setUserId(user.getId());
        userResponseModel.setUsername(user.getUsername());

        return new ResponseEntity<>(new GetUserResponseModel(userResponseModel), HttpStatus.OK);
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
