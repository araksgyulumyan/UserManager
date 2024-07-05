package com.example.user_management.service;

import com.example.user_management.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(final User user);

    Optional<User> getUserById(final Long id);

    Optional<User> findByUsername(final String username);

    List<User> getAllUsers();
}
