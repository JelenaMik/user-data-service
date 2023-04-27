package com.example.userdatams.web;

import com.example.userdatams.service.FavoriteProviderService;
import com.example.userdatams.service.UserDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("api/v1/favorite")
@RequiredArgsConstructor
public class FavoriteProviderController {
    private final FavoriteProviderService service;
    private final UserDataService userDataService;

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addFavoriteProvider(@Validated Long clientId, @Validated Long providerId){
        service.addFavoriteProvider(clientId, providerId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<HttpStatus> removeFavoriteProvider(Long clientId, Long providerId){
        service.removeFavoriteProvider(clientId, providerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/providers-list/{clientId}")
    public ResponseEntity<List> getFavoriteProvidersList(@PathVariable Long clientId){
        List<Long> providersIds = service.getClientsFavoriteProvidersList(clientId);
        return new ResponseEntity<>(userDataService.findUserDataForProviderList(providersIds), HttpStatus.OK);
    }
}
