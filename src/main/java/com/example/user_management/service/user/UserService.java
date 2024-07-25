package com.example.user_management.service.user;

import com.example.user_management.api.exception.UserNotFoundApiException;
import com.example.user_management.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

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
     * @return User
     * @throws UserNotFoundApiException if user not exists
     */
    User getUserById(final Long id);

    /**
     * Find user by username
     *
     * @param username of the User
     * @return User
     * @throws UserNotFoundApiException if user is not found
     */
    User findByUsername(final String username);

    /**
     * Get all users
     *
     * @return All users with pagination
     */
    Page<User> getAllUsers(final Integer page, final Integer size, final Sort sort);
}
