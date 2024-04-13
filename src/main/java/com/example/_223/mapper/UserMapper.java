package com.example._223.mapper;


import com.example._223.model.entity.User;
import com.example.starter.incomeclient.model.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserDTO toDto(User user) {
        UserDTO userDTO2 = new UserDTO();
        userDTO2.setId(user.getId());
        return userDTO2;
    }

    public User toUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        return user;
    }
}
