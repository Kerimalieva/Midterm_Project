package com.example.MidtermProject.Mapper;

import com.example.MidtermProject.DTO.UserDTO;
import com.example.MidtermProject.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setName(user.getName());
        return userDTO;
    }
}

