package com.example.userdatams.service.impl;

import com.example.userdatams.mapper.UserDataEntityMapper;
import com.example.userdatams.model.UserDataDto;
import com.example.userdatams.handlers.exceptions.UserDataNotFoundException;
import com.example.userdatams.repository.UserDataRepository;
import com.example.userdatams.repository.model.UserDataEntity;
import com.example.userdatams.service.UserDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class UserDataServiceImpl implements UserDataService {

    private final UserDataRepository userDataRepository;
    private final UserDataEntityMapper userDataEntityMapper;
    @Override
    public UserDataDto getUserData( Long userId ){
        UserDataEntity userData = userDataRepository.findByUserId( userId ).orElseThrow( UserDataNotFoundException::new );
        return userDataEntityMapper.entityToDto( userData );
    }

    @Override
    public List<UserDataDto> getAllUsersData(){
        List<UserDataDto> userDataDtoList = userDataRepository.findAll().stream()
                .map(userDataEntity -> userDataEntityMapper.entityToDto(userDataEntity))
                .collect(Collectors.toList());
        return userDataDtoList;
    }

    @Override
    public UserDataDto saveUserData(UserDataDto userData){
    userData.setRegistrationDate(LocalDateTime.now());
    log.info("userDataDto: {}", userData);

        return userDataEntityMapper.entityToDto(
                userDataRepository.save( userDataEntityMapper.dtoToEntity(userData) )
        );
    }

    @Override
    public UserDataDto updateUserData(UserDataDto userDataDto){
        UserDataEntity userData = userDataRepository.findByUserId(userDataDto.getUserId()).orElseThrow( UserDataNotFoundException::new );
        userData.setFirstName(userDataDto.getFirstName());
        userData.setLastName(userDataDto.getLastName());
        userDataRepository.save(userData);
        return userDataEntityMapper.entityToDto(userData);
    }

    @Override
    public UserDataDto getUser(Long userId){
        UserDataEntity user = userDataRepository.findByUserId(userId).orElseThrow( UserDataNotFoundException::new );
        return userDataEntityMapper.entityToDto(user);
    }





}
