package com.example.user_management.api.controller;

import com.example.user_management.api.converter.UserConverter;
import com.example.user_management.api.model.UserResponseModel;
import com.example.user_management.api.model.response.GetUserResponseModel;
import com.example.user_management.entity.User;
import com.example.user_management.api.model.request.CreateUserRequestModel;
import com.example.user_management.api.model.response.CreateUserResponseModel;
import com.example.user_management.service.impl.UserServiceImpl;
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
    private final UserConverter userConverter;

    @PostMapping("/register")
    public ResponseEntity<CreateUserResponseModel> registerUser(@RequestBody CreateUserRequestModel createUserRequestModel) {
        User user = userService.createUser(userConverter.toUser(createUserRequestModel));
        return new ResponseEntity<>(new CreateUserResponseModel(user.getId(), user.getUsername()), HttpStatus.CREATED);
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
