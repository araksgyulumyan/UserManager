package com.example.user_management.service.impl;

import com.example.user_management.entity.User;
import com.example.user_management.repository.UserRepository;
import com.example.user_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.Assert.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    @Transactional
    public User createUser(final User user) {
        assertUserNotNull(user);
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserById(final Long id) {
        assertUserIdNotNull(id);
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(final String username) {
        asserUsernameNotNull(username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    private void assertUserNotNull(final User user) {
         notNull(user, "User should not be null");
         asserUsernameNotNull(user.getUsername());
         assertPasswordNotNull(user.getPassword());
    }

    private void assertUserIdNotNull(final Long id) {
        notNull(id, "User id should not be null");
    }

    private void asserUsernameNotNull(final String username) {
        hasLength(username, "Username should not be empty");
    }

    private void assertPasswordNotNull(final String password) {
        hasLength(password, "User password should not be empty");
    }
}
