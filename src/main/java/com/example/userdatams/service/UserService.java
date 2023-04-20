package com.example.userdatams.service;

import com.example.userdatams.model.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Long saveUser(UserDto userDto);

    Long validateUserInfo(UserDto userDto);

    void changeUserRole(Long id, String role);

    String getUserRole(Long userId);
}
