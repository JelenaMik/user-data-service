package com.example.userdatams.service.impl;

import com.example.userdatams.enums.Role;
import com.example.userdatams.mapper.UserEntityMapper;
import com.example.userdatams.model.UserDto;
import com.example.userdatams.repository.UserRepository;
import com.example.userdatams.repository.model.UserEntity;
import com.example.userdatams.exceptions.UserDataNotFoundException;
import com.example.userdatams.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public Long saveUser(UserDto userDto) {
        if(userDto.getRole()==null) userDto.setRole(Role.USER);
        Long userId = userRepository.save(userEntityMapper.dtoToEntity(userDto)).getId();
        return userId;
    }

    @Override
    public Long validateUserInfo(UserDto userDto){
        UserEntity user = userRepository.findByEmailAndAndPassword(userDto.getEmail(),
                userDto.getPassword()).orElseThrow(UserDataNotFoundException::new);
        return userEntityMapper.entityToDto(user).getId();

    }

    @Override
    public void changeUserRole(Long id, String role){
        UserEntity user = userRepository.findById(id).orElseThrow(UserDataNotFoundException::new);
        user.setRole(Role.valueOf(role.toUpperCase()));
        userRepository.save(user);
    }

    @Override
    public String getUserRole(Long userId){
        UserEntity user = userRepository.findById(userId).orElseThrow( UserDataNotFoundException::new );
        return user.getRole().toString().toLowerCase();
    }

}
