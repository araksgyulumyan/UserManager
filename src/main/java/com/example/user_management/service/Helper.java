package com.example.user_management.service;

import com.example.user_management.dto.RegisterUserDto;
import com.example.user_management.entity.User;
import org.springframework.stereotype.Component;

import static org.springframework.util.Assert.hasLength;
import static org.springframework.util.Assert.notNull;

@Component
public class Helper {

    public void assertUserNotNullAndPropertiesNotEmpty(final User user) {
        notNull(user, "User should not be null");
        asserUsernameNotEmpty(user.getUsername());
        assertPasswordNotEmpty(user.getPassword());
    }

    public void assertRegisterUserDtoNotNullAndPropertiesNotEmpty(final RegisterUserDto registerUserDto) {
        notNull(registerUserDto, "User should not be null");
        asserUsernameNotEmpty(registerUserDto.getUsername());
        assertPasswordNotEmpty(registerUserDto.getPassword());
    }

    public void asserUsernameNotEmpty(final String username) {
        hasLength(username, "Username should not be empty");
    }

    public void assertPasswordNotEmpty(final String password) {
        hasLength(password, "User password should not be empty");
    }

    public void assertUserIdNotNull(final Long id) {
        notNull(id, "User id should not be null");
    }
}
