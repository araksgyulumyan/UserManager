package com.example.user_management.api.controller;

import com.example.user_management.api.model.common.UserResponseModel;
import com.example.user_management.api.model.response.GetUserResponseModel;
import com.example.user_management.entity.User;
import com.example.user_management.service.user.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/{userId}")
    public ResponseEntity<GetUserResponseModel> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);

        UserResponseModel userResponseModel = new UserResponseModel();
        userResponseModel.setUserId(user.getId());
        userResponseModel.setUsername(user.getUsername());

        return new ResponseEntity<>(new GetUserResponseModel(userResponseModel), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Page<User>> getAllUsers(@RequestParam int page, @RequestParam int size) {
        Page<User> users = userService.getAllUsers(page, size);
        return ResponseEntity.ok(users);
    }
}
