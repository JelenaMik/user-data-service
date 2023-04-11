package com.example.userdatams;

import com.example.userdatams.enums.Role;
import com.example.userdatams.model.UserDataDto;
import com.example.userdatams.model.UserDto;
import com.example.userdatams.repository.model.UserDataEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ObjectsForUserDataControllerTest {

    UserDataDto userDataDto = new UserDataDto();
    UserDataDto userDataDtoUpdated = new UserDataDto();
    UserDataEntity userDataEntityNoId = new UserDataEntity();
    UserDataEntity userDataEntity = new UserDataEntity();
    UserDataEntity userDataEntity2 = new UserDataEntity();
    UserDataEntity userDataEntity3 = new UserDataEntity();
    UserDataEntity userDataEntityUpdated = new UserDataEntity();
    UserDto user1 = new UserDto();
    UserDto user2 = new UserDto();
    UserDto user3 = new UserDto();

    @BeforeEach
    void setUp(){
        userDataDto = UserDataDto.builder()
                .userId(1L)
                .firstName("Anna")
                .lastName("Liepina")
                .build();

        userDataEntityNoId = UserDataEntity.builder()
                .userId(1L)
                .firstName("Anna")
                .lastName("Liepina")
                .build();

        userDataEntity = UserDataEntity.builder()
                .id(10L)
                .userId(1L)
                .firstName("Anna")
                .lastName("Liepina")
                .build();

        userDataEntity2 = UserDataEntity.builder()
                .id(10L)
                .userId(2L)
                .firstName("Anna")
                .lastName("Liepina")
                .build();

        userDataEntity3 = UserDataEntity.builder()
                .id(10L)
                .userId(3L)
                .firstName("Anna")
                .lastName("Liepina")
                .build();

        userDataEntityUpdated = UserDataEntity.builder()
                .id(10L)
                .userId(1L)
                .firstName("Anna")
                .lastName("Kalnina")
                .build();
        userDataDtoUpdated = UserDataDto.builder()
                .id(10L)
                .userId(1L)
                .firstName("Anna")
                .lastName("Kalnina")
                .build();
        user1 = UserDto.builder()
                .id(1L)
                .role(Role.PROVIDER)
                .build();
        user2 = UserDto.builder()
                .id(2L)
                .role(Role.PROVIDER)
                .build();
        user3 = UserDto.builder()
                .id(3L)
                .role(Role.PROVIDER)
                .build();


    }

}
