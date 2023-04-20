package com.example.userdatams.web;

import com.example.userdatams.model.UserDataDto;
import com.example.userdatams.model.UserDto;
import com.example.userdatams.service.UserDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("api/v1/userdata")
@RequiredArgsConstructor
public class UserDataController {

    private final UserDataService userDataService;
    @Operation(description = "Saves user data to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The request is successful"),
            @ApiResponse(responseCode = "404", description = "The server cannot find the requested resource"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @PostMapping("/save")
    public ResponseEntity<UserDataDto> saveUserData(@Validated @RequestBody UserDataDto userData){
        return new ResponseEntity<>(userDataService.saveUserData(userData), HttpStatus.CREATED);
    }
    @Operation(description = "Fetches all registered user data")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "The request is successful"),
        @ApiResponse(responseCode = "404", description = "The server cannot find the requested resource"),
        @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping("/all-users-data")
    public ResponseEntity<List<UserDataDto>> seeAllUsersData(){
        return new ResponseEntity<>(userDataService.getAllUsersData(), HttpStatus.OK);
    }

    @Operation(description = "Fetches user data with provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The request is successful"),
            @ApiResponse(responseCode = "404", description = "The server cannot find the requested resource"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping("/get-data/{id}")
    public ResponseEntity<UserDataDto> seeUserData(@PathVariable Long id){
        log.info("fetching user data id: {}", id);
        return new ResponseEntity<>(userDataService.getUserData(id), HttpStatus.OK);
    }

    @Operation(description = "Updates user data in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The request is successful"),
            @ApiResponse(responseCode = "404", description = "The server cannot find the requested resource"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @PutMapping("/update-user-data")
    public ResponseEntity<UserDataDto> updateUserData(@RequestBody UserDataDto userDataDto){
        return new ResponseEntity<>(userDataService.updateUserData(userDataDto), HttpStatus.OK);
    }
    @Operation(description = "Search users by first name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The request is successful"),
            @ApiResponse(responseCode = "404", description = "The server cannot find the requested resource"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping("/search-users")
    public ResponseEntity<List<UserDataDto>> searchByFirstName(String firstName){
        return new ResponseEntity<>(userDataService.findUserDataByFirstName(firstName), HttpStatus.OK);
    }
    @Operation(description = "Fetches providers user data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The request is successful"),
            @ApiResponse(responseCode = "404", description = "The server cannot find the requested resource"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping("/provider-data")
    public ResponseEntity<List<UserDataDto>> getProvidersData(@RequestParam List<Long> providerList){
        return new ResponseEntity<>(userDataService.findUserDataForProviderList(providerList), HttpStatus.OK);
    }


}
