package com.example.user_manager.service.security.impl;

import com.example.user_manager.service.security.CustomUserDetails;
import com.example.user_manager.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return new CustomUserDetails(userService.findByUsername(username));
    }
}
