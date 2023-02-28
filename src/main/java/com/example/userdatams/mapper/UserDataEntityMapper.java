package com.example.userdatams.mapper;

import com.example.userdatams.model.UserDataDto;
import com.example.userdatams.repository.model.UserDataEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDataEntityMapper {

    UserDataEntity dtoToEntity(UserDataDto userDataDto);

    UserDataDto entityToDto(UserDataEntity userDataEntity);

}
