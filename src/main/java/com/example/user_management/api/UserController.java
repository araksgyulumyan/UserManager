package com.example.user_management.api;

import com.example.user_management.converter.UserConverter;
import com.example.user_management.entity.User;
import com.example.user_management.model.request.CreateUserRequestModel;
import com.example.user_management.model.response.CreateUserResponseModel;
import com.example.user_management.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
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
    public CreateUserResponseModel registerUser(@RequestBody CreateUserRequestModel createUserRequestModel) {

        User user = userService.createUser(userConverter.toUser(createUserRequestModel));
        return new CreateUserResponseModel(user.getId(), user.getUsername(), user.getPassword());
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        Optional<User> optionalUser = userService.findByUsername(user.getUsername());
        if (optionalUser.isPresent() && user.getPassword().equals(optionalUser.get().getPassword())) {
            return "Successful login";
        } else {
            return "Invalid username or password";
        }
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
