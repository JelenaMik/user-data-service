package com.example.userdatams.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FavoriteProviderService {
    void addFavoriteProvider(Long clientId, Long providerId);

    void removeFavoriteProvider(Long clientId, Long providerId);

    List<Long> getClientsFavoriteProvidersList(Long clientId);
}
