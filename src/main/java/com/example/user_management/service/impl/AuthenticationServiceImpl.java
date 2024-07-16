package com.example.user_management.service.impl;

import com.example.user_management.dto.AuthenticateUserDto;
import com.example.user_management.dto.AuthorizeUserDto;
import com.example.user_management.entity.User;
import com.example.user_management.service.AuthenticationService;
import com.example.user_management.service.UserAssertionHelper;
import com.example.user_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserAssertionHelper userAssertionHelper;
    private final CustomUserDetailsServiceImpl customUserDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User authorize(final AuthorizeUserDto authorizeUserDto) {
        userAssertionHelper.assertRegisterUserDtoNotNullAndPropertiesNotEmpty(authorizeUserDto);
        User user = new User();
        user.setUsername(authorizeUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(authorizeUserDto.getPassword()));
        return userService.createUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails authenticate(final AuthenticateUserDto authenticateUserDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticateUserDto.getUsername(),
                        authenticateUserDto.getPassword()
                )
        );
        return customUserDetailsServiceImpl.loadUserByUsername(authenticateUserDto.getUsername());
    }
}
