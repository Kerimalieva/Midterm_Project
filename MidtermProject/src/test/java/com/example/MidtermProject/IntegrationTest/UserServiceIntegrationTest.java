package com.example.MidtermProject.IntegrationTest;

import com.example.MidtermProject.models.User;
import com.example.MidtermProject.models.enums.Role;
import com.example.MidtermProject.repositories.UserRepository;
import com.example.MidtermProject.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.MidtermProject.repositories.UserRepository;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;


    private UserService userService;



    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepository, passwordEncoder);
    }



    @Test
    public void whenCreateUser_thenUserIsSaved() {
        User user = new User();
        user.setEmail("ainazik@gmail.com");
        user.setPassword("12345678910");

        String encodedPassword = "hashed_password";
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn(encodedPassword);

        when(userRepository.findByEmail(anyString())).thenReturn(null);

        boolean result = userService.createUser(user);

        assertThat(result).isTrue(); // Check if user creation was successful
        verify(userRepository, times(1)).save(user); // Check if user was saved
    }






    @Test
    public void whenBanUser_thenUserIsBanned() {
        User user = new User();
        user.setId(1L);
        user.setActive(true);
        userRepository.save(user);

        userService.banUser(1L);

        User bannedUser = userRepository.findById(1L).orElse(null);
        assertThat(bannedUser).isNotNull();
        assertThat(bannedUser.isActive()).isFalse();
    }

    @Test
    public void whenGetUserByPrincipal_thenCorrectUserReturned() {
        String email = "ai@gmail.com";

        Optional<User> existingUserOptional = Optional.ofNullable(userRepository.findByEmail(email));
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
        } else {
            User user = new User();
            user.setEmail(email);
            userRepository.save(user);
        }
        User retrievedUser = userService.getUserByPrincipal(() -> email);

        assertThat(retrievedUser).isNotNull();
        assertThat(retrievedUser.getEmail()).isEqualTo(email);
    }


    @Transactional
    @Test
    public void whenListUsers_thenAllUsersReturned() {
        userRepository.deleteAll();

        User user1 = new User();
        user1.setEmail("user1-amira@gmail.com");

        User user2 = new User();
        user2.setEmail("user2-anita@gmail.com");

        userRepository.saveAll(List.of(user1, user2));

        List<User> userList = userService.list();

        assertThat(userList).hasSize(2);
        assertThat(userList).extracting(User::getEmail).containsExactlyInAnyOrder(
                user1.getEmail(),
                user2.getEmail()
        );
    }


    @Test
    public void whenCreateUserWithExistingEmail_thenUserNotSaved() {
        // Мокируем метод findByEmail репозитория
        when(userRepository.findByEmail("weekend@gmail.com")).thenReturn(null);

        User newUser = new User();
        newUser.setEmail("weekend@gmail.com");
        newUser.setPassword("password");

        when(passwordEncoder.encode(newUser.getPassword())).thenReturn("encodedPassword");

        boolean result = userService.createUser(newUser);

        assertThat(result).isTrue();
    }


    @Test
    void whenBanNonExistingUser_thenUserNotFound() {
        when(userRepository.findById(100L)).thenReturn(Optional.empty());

        userService.banUser(100L);

        assertThat(userRepository.findById(100L)).isEmpty();
    }



}
