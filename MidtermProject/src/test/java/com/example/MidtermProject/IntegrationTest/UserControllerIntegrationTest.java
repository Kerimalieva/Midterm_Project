package com.example.MidtermProject.IntegrationTest;

import com.example.MidtermProject.controllers.UserController;
import com.example.MidtermProject.models.User;
import com.example.MidtermProject.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerIntegrationTest {

    @Mock
    UserService userService;

    @Mock
    Model model;

    @Mock
    Principal principal;

    @InjectMocks
    UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrationSuccess() {
        User user = new User();
        user.setEmail("arthur@gmail.com");
        user.setPassword("0708");

        when(userService.createUser(user)).thenReturn(true);

        String result = userController.createUser(user, model);

        assertEquals("redirect:/login", result);
        verify(userService, times(1)).createUser(user);
        verifyNoMoreInteractions(userService);
    }

    @Test
    void testRegistrationFailure() {
        User user = new User();
        user.setEmail("arthur@gmail.com");
        user.setPassword("0708");

        when(userService.createUser(user)).thenReturn(false);

        String result = userController.createUser(user, model);

        assertEquals("registration", result);
        verify(userService, times(1)).createUser(user);
        verify(model, times(1)).addAttribute(eq("errorMessage"), anyString());
        verifyNoMoreInteractions(userService);
        verifyNoMoreInteractions(model);
    }

    @Test
    void testProfile() {
        User user = new User();
        user.setEmail("hanzada@gmail.com");
        user.setName("Hanzada");

        when(principal.getName()).thenReturn(user.getEmail());
        when(userService.getUserByPrincipal(principal)).thenReturn(user);

        String result = userController.profile(principal, model);

        assertEquals("profile", result);
        verify(userService, times(1)).getUserByPrincipal(principal);
        verify(model, times(1)).addAttribute("user", user);
        verifyNoMoreInteractions(userService);
        verifyNoMoreInteractions(model);
    }

}
