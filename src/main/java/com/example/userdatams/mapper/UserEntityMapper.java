package com.example.userdatams.mapper;

import com.example.userdatams.model.UserDto;
import com.example.userdatams.repository.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserEntity dtoToEntity(UserDto userDto);
    UserDto entityToDto(UserEntity userEntity);
}
