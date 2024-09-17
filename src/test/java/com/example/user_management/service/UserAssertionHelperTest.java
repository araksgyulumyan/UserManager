package com.example.user_management.service;

import com.example.user_management.entity.User;
import com.example.user_management.service.model.AuthorizeUserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserAssertionHelperTest {

    @InjectMocks
    private UserAssertionHelper userAssertionHelper;

    private User user;
    private AuthorizeUserModel authorizeUserModel;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername("testuser");
        user.setPassword("testpass");

        authorizeUserModel = new AuthorizeUserModel();
        authorizeUserModel.setUsername("testAuthorizedUser");
        authorizeUserModel.setPassword("testAuthorizedUserPass");
    }

    @Test
    public void testAssertUserNotNullAndPropertiesNotEmpty_Success() {
        assertDoesNotThrow(() -> userAssertionHelper.assertUserNotNullAndPropertiesNotEmpty(user));
    }

    @Test
    public void testAssertUserNotNullAndPropertiesNotEmpty_UserIsNull() {
        user = null;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userAssertionHelper.assertUserNotNullAndPropertiesNotEmpty(user);
        });

        assertEquals("User should not be null", exception.getMessage());
    }

    @Test
    public void testAssertUserNotNullAndPropertiesNotEmpty_UsernameIsEmpty() {
        user.setUsername("");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userAssertionHelper.assertUserNotNullAndPropertiesNotEmpty(user);
        });

        assertEquals("Username should not be empty", exception.getMessage());
    }

    @Test
    public void testAssertUserNotNullAndPropertiesNotEmpty_PasswordIsEmpty() {
        user.setPassword("");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userAssertionHelper.assertUserNotNullAndPropertiesNotEmpty(user);
        });

        assertEquals("User password should not be empty", exception.getMessage());
    }

    @Test
    public void testAssertRegisterUserDtoNotNullAndPropertiesNotEmpty_Success() {
        assertDoesNotThrow(() -> userAssertionHelper.assertRegisterUserDtoNotNullAndPropertiesNotEmpty(authorizeUserModel));
    }

    @Test
    public void testAssertRegisterUserDtoNotNullAndPropertiesNotEmpty_UserDtoIsNull() {
        authorizeUserModel = null;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userAssertionHelper.assertRegisterUserDtoNotNullAndPropertiesNotEmpty(authorizeUserModel);
        });

        assertEquals("User should not be null", exception.getMessage());
    }

    @Test
    public void testAssertRegisterUserDtoNotNullAndPropertiesNotEmpty_UsernameIsEmpty() {
        authorizeUserModel.setUsername("");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userAssertionHelper.assertRegisterUserDtoNotNullAndPropertiesNotEmpty(authorizeUserModel);
        });

        assertEquals("Username should not be empty", exception.getMessage());
    }

    @Test
    public void testAssertRegisterUserDtoNotNullAndPropertiesNotEmpty_PasswordIsEmpty() {
        authorizeUserModel.setPassword("");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userAssertionHelper.assertRegisterUserDtoNotNullAndPropertiesNotEmpty(authorizeUserModel);
        });

        assertEquals("User password should not be empty", exception.getMessage());
    }

    @Test
    public void testAsserUsernameNotEmpty_Success() {
        assertDoesNotThrow(() -> userAssertionHelper.asserUsernameNotEmpty("validUsername"));
    }

    @Test
    public void testAsserUsernameNotEmpty_UsernameIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userAssertionHelper.asserUsernameNotEmpty("");
        });

        assertEquals("Username should not be empty", exception.getMessage());
    }

    @Test
    public void testAssertPasswordNotEmpty_Success() {
        assertDoesNotThrow(() -> userAssertionHelper.assertPasswordNotEmpty("validPassword"));
    }

    @Test
    public void testAssertPasswordNotEmpty_PasswordIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userAssertionHelper.assertPasswordNotEmpty("");
        });

        assertEquals("User password should not be empty", exception.getMessage());
    }

    @Test
    public void testAssertUserIdNotNull_Success() {
        assertDoesNotThrow(() -> userAssertionHelper.assertUserIdNotNull(1L));
    }

    @Test
    public void testAssertUserIdNotNull_IdIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userAssertionHelper.assertUserIdNotNull(null);
        });

        assertEquals("User id should not be null", exception.getMessage());
    }
}
