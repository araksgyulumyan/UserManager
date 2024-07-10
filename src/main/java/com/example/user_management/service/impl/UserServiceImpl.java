package com.example.user_management.service.impl;

import com.example.user_management.api.exception.UserNotFoundException;
import com.example.user_management.entity.User;
import com.example.user_management.repository.UserRepository;
import com.example.user_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.util.Assert.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    @Transactional
    public User createUser(final User user) {
        assertUserNotNullAndPropertiesNotEmpty(user);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(final Long id) {
        assertUserIdNotNull(id);
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public User findByUsername(final String username) {
        asserUsernameNotEmpty(username);
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    private void assertUserNotNullAndPropertiesNotEmpty(final User user) {
         notNull(user, "User should not be null");
        asserUsernameNotEmpty(user.getUsername());
        assertPasswordNotEmpty(user.getPassword());
    }

    private void assertUserIdNotNull(final Long id) {
        notNull(id, "User id should not be null");
    }

    private void asserUsernameNotEmpty(final String username) {
        hasLength(username, "Username should not be empty");
    }

    private void assertPasswordNotEmpty(final String password) {
        hasLength(password, "User password should not be empty");
    }
}
