package com.example.userdatams.service.impl;

import com.example.userdatams.mapper.UserDataEntityMapper;
import com.example.userdatams.model.UserDataDto;
import com.example.userdatams.handlers.exceptions.UserDataNotFoundException;
import com.example.userdatams.model.UserDto;
import com.example.userdatams.repository.UserDataRepository;
import com.example.userdatams.repository.model.UserDataEntity;
import com.example.userdatams.service.UserDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        return userDataRepository.findAll().stream()
                .map(userDataEntityMapper::entityToDto)
                .toList();
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

    @Override
    public List<UserDataDto> findUserDataByFirstName(String firstName){
        return userDataRepository.findFirst10ByFirstNameContaining(firstName)
                .stream()
                .map(userDataEntityMapper::entityToDto)
                .toList();
    }

    @Override
    public List<UserDataDto> findUserDataForProviderList(List<UserDto> providerList){
        return providerList.stream()
                .map(provider -> userDataRepository.findByUserId(provider.getId()).get())
                .toList().stream()
                .map(userDataEntityMapper::entityToDto)
                .toList();

    }





}
