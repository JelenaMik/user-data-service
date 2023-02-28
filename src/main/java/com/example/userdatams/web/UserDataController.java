package com.example.userdatams.web;

import com.example.userdatams.model.UserDataDto;
import com.example.userdatams.repository.model.UserDataEntity;
import com.example.userdatams.service.UserDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("api/v1/userdata")
@RequiredArgsConstructor
public class UserDataController {

    private final UserDataService userDataService;

    @PostMapping("/save")
    public UserDataDto saveUserData(@RequestBody UserDataDto userData){
        log.info(userData);
        return userDataService.saveUserData(userData);
    }

    @GetMapping("/all-users-data")
    public ResponseEntity<List<UserDataDto>> seeAllUsersData(){
        return ResponseEntity.ok(userDataService.getAllUsersData());
    }

    @GetMapping("/get-data/{id}")
    public ResponseEntity<UserDataDto> seeUserData(@PathVariable Long id){
        return ResponseEntity.ok(userDataService.getUserData(id));
    }

    @PutMapping("/update-user-data")
    public ResponseEntity<UserDataDto> updateUserData(@RequestBody UserDataDto userDataDto){
        return ResponseEntity.ok(userDataService.updateUserData(userDataDto));
    }


}
