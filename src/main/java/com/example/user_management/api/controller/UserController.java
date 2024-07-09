package com.example.user_management.api.controller;

import com.example.user_management.api.converter.UserConverter;
import com.example.user_management.entity.User;
import com.example.user_management.api.model.request.CreateUserRequestModel;
import com.example.user_management.api.model.response.CreateUserResponseModel;
import com.example.user_management.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        Optional<User> optionalUser = userService.findByUsername(user.getUsername());
        if (optionalUser.isPresent() && user.getPassword().equals(optionalUser.get().getPassword())) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
