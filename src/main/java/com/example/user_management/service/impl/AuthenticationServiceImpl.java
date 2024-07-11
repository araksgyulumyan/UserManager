package com.example.user_management.service.impl;

import com.example.user_management.api.exception.UserNotFoundException;
import com.example.user_management.converter.UserDtoConverter;
import com.example.user_management.dto.LoginUserDto;
import com.example.user_management.dto.RegisterUserDto;
import com.example.user_management.entity.User;
import com.example.user_management.repository.UserRepository;
import com.example.user_management.service.AuthenticationService;
import com.example.user_management.service.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserDtoConverter userDtoConverter;
    private final Helper helper;

    @Override
    @Transactional
    public User register(final RegisterUserDto registerUserDto) {
        helper.assertRegisterUserDtoNotNullAndPropertiesNotEmpty(registerUserDto);
        return userRepository.save(userDtoConverter.toUser(registerUserDto));
    }

    @Override
    public User login(final LoginUserDto loginUserDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDto.getUsername(),
                        loginUserDto.getPassword()
                )
        );

        return userRepository.findByUsername(loginUserDto.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + loginUserDto.getUsername()));
    }
}
