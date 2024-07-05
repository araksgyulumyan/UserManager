package com.example.user_management.service.impl;

import com.example.user_management.entity.User;
import com.example.user_management.repository.UserRepository;
import com.example.user_management.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
    public Optional<User> getUserById(final Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(final String username) {
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    private void assertUserNotNull(final User user) {
         notNull(user, "User should not be null");
         hasLength(user.getUsername(), "User username should not be empty");
         hasLength(user.getPassword(), "User password should not be empty");
    }
}
