package com.example.user_manager.service;

import com.example.user_manager.entity.User;
import com.example.user_manager.repository.UserRepository;
import com.example.user_manager.service.exception.UserNotFoundForIdException;
import com.example.user_manager.service.exception.UserNotFoundForUsernameException;
import com.example.user_manager.service.user.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
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
    //todo missing test case UserAssertionHelper
    private UserAssertionHelper userAssertionHelper;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    public void before() {
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password");
        reset(userRepository, userAssertionHelper);
    }

    @AfterEach
    public void after() {
        noMoreInteractions();
    }

    @Test
    public void testCreateUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals(user.getUsername(), createdUser.getUsername());
        verify(userAssertionHelper, times(1)).assertUserNotNullAndPropertiesNotEmpty(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetAllUsers_whenUsersExists_thenUsersReturned() {
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
    public void testGetAllUsers_NoUsersExists_thenEmptyListReturned() {
        List<User> users = Collections.emptyList();
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));
        Page<User> userPage = new PageImpl<>(users, pageable, users.size());

        when(userRepository.findAll(eq(pageable))).thenReturn(userPage);

        Page<User> result = userService.getAllUsers(0, 10, Sort.by(Sort.Direction.ASC, "id"));

        assertEquals(0, result.getTotalElements());
        assertEquals(0, result.getContent().size());
    }
    //todo missing test cases for getAll

    @Test
    public void testFindByUsername() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        User result = userService.findByUsername("testuser");

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());

        verify(userAssertionHelper, times(1)).asserUsernameNotEmpty("testuser");
        verify(userRepository, times(1)).findByUsername("testuser");
    }

    @Test
    public void testFindByUsername_UserNotFound() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        String expectedUsername = "unknownuser";
        assertThrows(UserNotFoundForUsernameException.class, () -> userService.findByUsername(expectedUsername));

        verify(userRepository, times(1)).findByUsername(anyString());
        verify(userAssertionHelper, times(1)).asserUsernameNotEmpty(expectedUsername);
    }

    @Test
    public void testGetUserById() {
        // expect
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // call
        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        // verify
        verify(userAssertionHelper, times(1)).assertUserIdNotNull(1L);
        verify(userRepository, times(1)).findById(1L);
    }


    @Test
    public void testGetUserById_UserNotFound() {
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(UserNotFoundForIdException.class, () -> userService.getUserById(1L));

        verify(userAssertionHelper, times(1)).assertUserIdNotNull(1L);
        verify(userRepository, times(1)).findById(1L);
    }

    private void noMoreInteractions() {
        verifyNoMoreInteractions(userRepository, userAssertionHelper);
    }
}
