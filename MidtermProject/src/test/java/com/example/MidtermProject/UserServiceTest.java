package com.example.MidtermProject;

import com.example.MidtermProject.models.User;
import com.example.MidtermProject.models.enums.Role;
import com.example.MidtermProject.repositories.UserRepository;
import com.example.MidtermProject.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Principal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateUser_Successful() {
        User user = new User();
        user.setEmail("mem@gmail.com");
        user.setPassword("password");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        boolean result = userService.createUser(user);

        assertTrue(result);
        assertTrue(user.isActive());
        assertEquals(Role.ROLE_USER, user.getRoles().iterator().next());
        assertEquals("encodedPassword", user.getPassword());
        verify(userRepository, times(1)).findByEmail(user.getEmail());
        verify(passwordEncoder, times(1)).encode("password");
        verify(userRepository, times(1)).save(user);
    }



    @Test
    void testCreateUser_UserAlreadyExists() {
        User existingUser = new User();
        existingUser.setEmail("e@gmail.com");
        when(userRepository.findByEmail(existingUser.getEmail())).thenReturn(existingUser);

        boolean result = userService.createUser(existingUser);

        assertFalse(result);
        verify(userRepository, times(1)).findByEmail(existingUser.getEmail());
        verifyNoMoreInteractions(userRepository);
        verifyNoInteractions(passwordEncoder);
    }

    @Test
    void testList() {
        List<User> userList = Arrays.asList(new User(), new User(), new User());
        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.list();

        assertEquals(userList, result);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testBanUser_BanUser() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setActive(true);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.banUser(userId);

        assertFalse(user.isActive());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testBanUser_UnbanUser() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setActive(false);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.banUser(userId);


        assertTrue(user.isActive());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user);
    }


    @Test
    void testGetUserByPrincipal_WithPrincipal() {
        String email = "zarina@gmail.com";
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn(email);
        User user = new User();
        user.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(user);

        User result = userService.getUserByPrincipal(principal);

        assertEquals(user, result);
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void testGetUserByPrincipal_WithoutPrincipal() {
        Principal principal = null;

        User result = userService.getUserByPrincipal(principal);

        assertNotNull(result);
        verifyNoInteractions(userRepository);
    }

    @Test
    void testGetUserById_UserExists() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.getUserById(userId);

        assertEquals(user, result);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetUserById_UserNotExists() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        User result = userService.getUserById(userId);

        assertNull(result);
        verify(userRepository, times(1)).findById(userId);
    }
}
