package com.example.user_management.service;

import com.example.user_management.api.exception.UserNotFoundApiException;
import com.example.user_management.entity.User;
import com.example.user_management.repository.UserRepository;
import com.example.user_management.service.user.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.List;
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

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password");
    }

    @Test
    public void testCreateUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals(user.getUsername(), createdUser.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetAllUsers() {
        User user = new User();
        user.setUsername("testUser");

        List<User> users = List.of(user);
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
        Page<User> userPage = new PageImpl<>(users, pageable, users.size());

        when(userRepository.findAll(eq(pageable))).thenReturn(userPage);

        Page<User> result = userService.getAllUsers(0, 10, Sort.by(Sort.Direction.DESC, "id"));

        assertEquals(1, result.getTotalElements());
        assertEquals("testUser", result.getContent().get(0).getUsername());
    }

    @Test
    public void testGetAllUsers_NoUsersExists() {
        List<User> users = Collections.emptyList();
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));
        Page<User> userPage = new PageImpl<>(users, pageable, users.size());

        when(userRepository.findAll(eq(pageable))).thenReturn(userPage);

        Page<User> result = userService.getAllUsers(0, 10, Sort.by(Sort.Direction.ASC, "id"));

        assertEquals(0, result.getTotalElements());
        assertEquals(0, result.getContent().size());
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
    public void testFindByUsername_UserNotFound() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundApiException.class, () -> userService.findByUsername("unknownuser"));
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

    @Test
    public void testGetUserById_UserNotFound() {
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(UserNotFoundApiException.class, () -> userService.getUserById(1L));
        verify(userRepository, times(1)).findById(1L);
    }
}
