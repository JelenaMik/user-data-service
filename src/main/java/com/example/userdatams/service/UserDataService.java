package com.example.userdatams.service;

import com.example.userdatams.model.UserDataDto;
import com.example.userdatams.model.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserDataService {
    UserDataDto getUserData(Long id);
    List<UserDataDto> getAllUsersData();
    UserDataDto saveUserData(UserDataDto userData);
    UserDataDto updateUserData(UserDataDto userDataDto);
    UserDataDto getUser(Long userId);
    List<UserDataDto> findUserDataByFirstName(String firstName);
    List<UserDataDto> findUserDataForProviderList(List<Long> providerList);
}
