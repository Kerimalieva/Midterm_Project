package com.example.MidtermProject.controllers;

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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void login() {
        Model model = mock(Model.class);
        Principal principal = mock(Principal.class);
        User user = new User();

        when(userService.getUserByPrincipal(principal)).thenReturn(user);

        String viewName = userController.login(principal, model);

        assertEquals("login", viewName);
        verify(model).addAttribute("user", user);
    }

    @Test
    void profile() {
        Model model = mock(Model.class);
        Principal principal = mock(Principal.class);
        User user = new User();

        when(userService.getUserByPrincipal(principal)).thenReturn(user);

        String viewName = userController.profile(principal, model);

        assertEquals("profile", viewName);
        verify(model).addAttribute("user", user);
    }

    @Test
    void registration() {
        Model model = mock(Model.class);
        Principal principal = mock(Principal.class);
        User user = new User();

        when(userService.getUserByPrincipal(principal)).thenReturn(user);

        String viewName = userController.registration(principal, model);

        assertEquals("registration", viewName);
        verify(model).addAttribute("user", user);
    }

    @Test
    void createUser() {
        Model model = mock(Model.class);
        User user = new User();

        when(userService.createUser(user)).thenReturn(true);

        String viewName = userController.createUser(user, model);

        assertEquals("redirect:/login", viewName);
    }

    @Test
    void userInfo() {
        Model model = mock(Model.class);
        Principal principal = mock(Principal.class);
        User user = new User();

        String viewName = userController.userInfo(user, model, principal);

        assertEquals("user-info", viewName);
        verify(model).addAttribute("user", user);
        verify(model).addAttribute("userByPrincipal", userService.getUserByPrincipal(principal));
        verify(model).addAttribute("products", user.getProducts());
    }
}
