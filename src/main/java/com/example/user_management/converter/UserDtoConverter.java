package com.example.user_management.converter;

import com.example.user_management.dto.RegisterUserDto;
import com.example.user_management.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDtoConverter {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User toUser(final RegisterUserDto registerUserDto) {
        User user = new User();
        user.setUsername(registerUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        return user;
    }
}
