package com.example.userdatams;

import com.example.userdatams.exceptions.UserDataNotFoundException;
import com.example.userdatams.model.UserDataDto;
import com.example.userdatams.repository.UserDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@MockBean(UserDataRepository.class)
public class UserDataControllerPutPostSpringBootTests extends ObjectsForUserDataControllerTest{

    @Autowired
    UserDataRepository repository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    void saveUserDataSuccess(){

        when(repository.save(userDataEntityNoId)).thenReturn(userDataEntity);


        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/userdata/save")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(userDataDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").exists())
                .andReturn();
    }

    @Test
    @SneakyThrows
    void saveUserDataBlankLastName(){
        userDataDto = UserDataDto.builder()
                .userId(1L)
                .firstName("Anna")
                .lastName("")
                .build();

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/userdata/save")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(userDataDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof MethodArgumentNotValidException))
                .andReturn();
    }
    @Test
    @SneakyThrows
    void saveUserDataNullLastName(){
        userDataDto = UserDataDto.builder()
                .userId(1L)
                .firstName("Anna")
                .build();

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/userdata/save")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(userDataDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof MethodArgumentNotValidException))
                .andReturn();
    }
    @Test
    @SneakyThrows
    void saveUserDataBlankFirstName(){
        userDataDto = UserDataDto.builder()
                .userId(1L)
                .firstName("")
                .lastName("Liepina")
                .build();

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/userdata/save")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(userDataDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof MethodArgumentNotValidException))
                .andReturn();
    }
    @Test
    @SneakyThrows
    void saveUserDataNullFirstName(){
        userDataDto = UserDataDto.builder()
                .userId(1L)
                .lastName("Liepina")
                .build();

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/userdata/save")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(userDataDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof MethodArgumentNotValidException))
                .andReturn();
    }

    public static String asJsonString(UserDataDto userDataDto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(userDataDto);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @SneakyThrows
    void updateUserDataSuccess(){

        when(repository.findByUserId(1L)).thenReturn(Optional.ofNullable(userDataEntity));
        when(repository.save(userDataEntityUpdated)).thenReturn(userDataEntityUpdated);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/userdata/update-user-data")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(userDataDtoUpdated)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Kalnina"))
                .andReturn();
    }

    @Test
    @SneakyThrows
    void updateUserDataUserNotFound(){

        when(repository.findByUserId(1L)).thenReturn(Optional.ofNullable(null));

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/userdata/update-user-data")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(userDataDtoUpdated)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof UserDataNotFoundException))
                .andReturn();
    }

}

