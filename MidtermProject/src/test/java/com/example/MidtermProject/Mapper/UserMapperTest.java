package com.example.MidtermProject.Mapper;

import com.example.MidtermProject.DTO.UserDTO;
import com.example.MidtermProject.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserMapperTest {
    @Mock
    private UserMapper userMapper;

    @Test
    public void testToUserDTO() {
        User user = new User();
        user.setId(1L);
        user.setEmail("alina@gmail.com");
        user.setPhoneNumber("1234567890");
        user.setName("User");

        UserDTO expectedUserDTO = new UserDTO();
        expectedUserDTO.setId(user.getId());
        expectedUserDTO.setEmail(user.getEmail());
        expectedUserDTO.setPhoneNumber(user.getPhoneNumber());
        expectedUserDTO.setName(user.getName());
        when(userMapper.toUserDTO(user)).thenReturn(expectedUserDTO);

        UserDTO userDTO = userMapper.toUserDTO(user);

        assertEquals(expectedUserDTO.getId(), userDTO.getId());
        assertEquals(expectedUserDTO.getEmail(), userDTO.getEmail());
        assertEquals(expectedUserDTO.getPhoneNumber(), userDTO.getPhoneNumber());
        assertEquals(expectedUserDTO.getName(), userDTO.getName());
    }
}
