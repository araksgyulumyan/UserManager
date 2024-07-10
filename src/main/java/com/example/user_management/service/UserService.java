package com.example.user_management.service;

import com.example.user_management.api.exception.UserNotFoundException;
import com.example.user_management.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     * Creates new user
     *
     * @param user
     * @return newly created user
     */
    User createUser(final User user);

    /**
     * Get user for provided id
     *
     * @param id
     * @throws UserNotFoundException if user not exists
     * @return User
     */
    User getUserById(final Long id);

    /**
     * Find user by username
     *
     * @param username
     * @throws UserNotFoundException
     * @return User
     */
    User findByUsername(final String username);

    /**
     * Get all users
     * @return List of all users
     */
    List<User> getAllUsers();
}
