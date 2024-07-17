package com.example.user_management.service.security.impl;

import com.example.user_management.service.model.AuthenticateUserModel;
import com.example.user_management.service.model.AuthorizeUserModel;
import com.example.user_management.entity.User;
import com.example.user_management.service.security.AuthenticationService;
import com.example.user_management.service.UserAssertionHelper;
import com.example.user_management.service.user.UserService;
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
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User authorize(final AuthorizeUserModel authorizeUserModel) {
        userAssertionHelper.assertRegisterUserDtoNotNullAndPropertiesNotEmpty(authorizeUserModel);
        User user = new User();
        user.setUsername(authorizeUserModel.getUsername());
        user.setPassword(passwordEncoder.encode(authorizeUserModel.getPassword()));
        return userService.createUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails authenticate(final AuthenticateUserModel authenticateUserModel) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticateUserModel.getUsername(),
                        authenticateUserModel.getPassword()
                )
        );
        return userDetailsServiceImpl.loadUserByUsername(authenticateUserModel.getUsername());
    }
}
