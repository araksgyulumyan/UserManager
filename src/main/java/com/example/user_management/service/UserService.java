package com.example.user_management.service;

import com.example.user_management.api.exception.UserNotFoundException;
import com.example.user_management.entity.User;

import java.util.List;

public interface UserService {

    /**
     * Creates new user
     *
     * @param user to be created
     * @return newly created user
     */
    User createUser(final User user);

    /**
     * Get user for provided id
     *
     * @param id of the User
     * @throws UserNotFoundException if user not exists
     * @return User
     */
    User getUserById(final Long id);

    /**
     * Find user by username
     *
     * @param username of the User
     * @throws UserNotFoundException if user is not found
     * @return User
     */
    User findByUsername(final String username);

    /**
     * Get all users
     * @return List of all users
     */
    List<User> getAllUsers();
}
