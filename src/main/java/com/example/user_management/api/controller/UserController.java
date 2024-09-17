package com.example.user_management.api.controller;

import com.example.user_management.api.converter.UserModelConverter;
import com.example.user_management.api.model.common.UserResponseModel;
import com.example.user_management.api.model.response.GetUserResponseModel;
import com.example.user_management.entity.User;
import com.example.user_management.service.user.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final UserModelConverter userModelConverter;

    @GetMapping("/{userId}")
    public ResponseEntity<GetUserResponseModel> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);

        UserResponseModel userResponseModel = new UserResponseModel();
        userResponseModel.setUserId(user.getId());
        userResponseModel.setUsername(user.getUsername());

        return new ResponseEntity<>(new GetUserResponseModel(userResponseModel), HttpStatus.OK);
    }

    @Cacheable(value = "userCache", key = "'users_' + #page + '_' + #size + '_' + #sortBy + '_' + #sortDirection", cacheManager = "redisCacheManager")
    @GetMapping
    public ResponseEntity<Page<UserResponseModel>> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size,
                                                               @RequestParam(defaultValue = "id") String sortBy,
                                                               @RequestParam(defaultValue = "desc") String sortDirection) {

        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Page<User> userPage = userService.getAllUsers(page, size, Sort.by(direction, sortBy));
        List<UserResponseModel> userResponseModels = userPage.stream().map(userModelConverter::toUserResponseModel).toList();
        Page<UserResponseModel> userResponseModelPage = new PageImpl<>(userResponseModels);
        return new ResponseEntity<>(userResponseModelPage, HttpStatus.OK);
    }
}
