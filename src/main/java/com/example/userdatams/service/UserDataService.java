package com.example.userdatams.service;

import com.example.userdatams.model.UserDataDto;
import com.example.userdatams.repository.model.UserDataEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface UserDataService {
    public UserDataDto getUserData(Long id);
    public List<UserDataDto> getAllUsersData();
//    public void saveUserData(Long userId, String firstName, String lastName, LocalDateTime registrationDate);
    public UserDataDto saveUserData(UserDataDto userData);
    public UserDataDto updateUserData(UserDataDto userDataDto);
}
