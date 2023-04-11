package com.example.userdatams;

import com.example.userdatams.exceptions.UserDataNotFoundException;
import com.example.userdatams.model.UserDataDto;
import com.example.userdatams.model.UserDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@MockBean(UserDataRepository.class)
public class UserDataControllerGetSpringBootTests extends ObjectsForUserDataControllerTest{

    @Autowired
    UserDataRepository repository;
    @Autowired
    private MockMvc mockMvc;


    @Test
    @SneakyThrows
    void getAllUsersData(){

        when(repository.findAll()).thenReturn(List.of(userDataEntity));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/userdata/all-users-data")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andReturn();
    }
    @Test
    @SneakyThrows
    void getAllUsersDataEmptyList(){

        when(repository.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/userdata/all-users-data")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)))
                .andReturn();
    }

    @Test
    @SneakyThrows
    void getUsersDataByIdSuccess(){

        when(repository.findByUserId(1L)).thenReturn(Optional.ofNullable(userDataEntity));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/userdata/get-data/1")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();
    }
    @Test
    @SneakyThrows
    void getUsersDataByIdEmptyOptional(){

        when(repository.findByUserId(1L)).thenReturn(Optional.ofNullable(null));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/userdata/get-data/1")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(res -> assertTrue(res.getResolvedException() instanceof UserDataNotFoundException))
                .andReturn();
    }

    @Test
    @SneakyThrows
    void findByUserNameSuccess(){

        when(repository.findFirst10ByFirstNameContaining("Anna")).thenReturn(List.of(userDataEntity, userDataEntityUpdated));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/userdata/search-users")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .queryParam("firstName", "Anna"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andReturn();
    }

    @Test
    @SneakyThrows
    void findByUserNameEmptyList(){

        when(repository.findFirst10ByFirstNameContaining("Anna")).thenReturn(new ArrayList<>());

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/userdata/search-users")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .queryParam("firstName", "Anna"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)))
                .andReturn();
    }

    @Test
    @SneakyThrows
    void getProvidersData(){

        when(repository.findByUserId(1L)).thenReturn(Optional.ofNullable(userDataEntity));
        when(repository.findByUserId(2L)).thenReturn(Optional.ofNullable(userDataEntity2));
        when(repository.findByUserId(3L)).thenReturn(Optional.ofNullable(userDataEntity3));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/userdata/provider-data")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(listAsJsonString(List.of(user1, user2, user3))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andReturn();
    }

    @Test
    @SneakyThrows
    void getProvidersDataEmptyList(){

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/userdata/provider-data")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(listAsJsonString(new ArrayList<>())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)))
                .andReturn();
    }

    public static String listAsJsonString(List<UserDto> providerList) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(providerList);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }




}
