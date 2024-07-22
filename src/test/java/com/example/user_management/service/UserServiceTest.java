package com.example.user_management.service;

import com.example.user_management.api.exception.UserNotFoundException;
import com.example.user_management.entity.User;
import com.example.user_management.repository.UserRepository;
import com.example.user_management.service.user.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserAssertionHelper userAssertionHelper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testGetAllUsers() {
        User user = new User();
        user.setUsername("testuser");

        Page<User> page = new PageImpl<>(Collections.singletonList(user));
        when(userRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<User> result = userService.getAllUsers(0, 10);

        assertEquals(1, result.getTotalElements());
        assertEquals("testuser", result.getContent().get(0).getUsername());
    }

    @Test
    public void testFindByUsername() {
        User user = new User();
        user.setUsername("testuser");
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        User result = userService.findByUsername("testuser");
        assertEquals("testuser", result.getUsername());
        verify(userAssertionHelper, times(1)).asserUsernameNotEmpty("testuser");

    }

    @Test
    public void testGetByUsername_UserNotFound() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findByUsername("unknownuser"));
        verify(userAssertionHelper, times(1)).asserUsernameNotEmpty("unknownuser");
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);
        assertEquals(1, result.getId());
    }
}
