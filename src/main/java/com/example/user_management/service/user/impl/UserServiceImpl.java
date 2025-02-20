package com.example.user_management.service.user.impl;

import com.example.user_management.entity.User;
import com.example.user_management.repository.UserRepository;
import com.example.user_management.service.UserAssertionHelper;
import com.example.user_management.service.exception.UserNotFoundForIdException;
import com.example.user_management.service.exception.UserNotFoundForUsernameException;
import com.example.user_management.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserAssertionHelper helper;

    @Override
    @Transactional
    public User createUser(final User user) {
        helper.assertUserNotNullAndPropertiesNotEmpty(user);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(final Long id) {
        helper.assertUserIdNotNull(id);
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundForIdException(id));
    }

    @Override
    public User findByUsername(final String username) {
        helper.asserUsernameNotEmpty(username);
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundForUsernameException(username));
    }

    @Override
    public Page<User> getAllUsers(final Integer page, final Integer size, final Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        return userRepository.findAll(pageable);
    }
}
