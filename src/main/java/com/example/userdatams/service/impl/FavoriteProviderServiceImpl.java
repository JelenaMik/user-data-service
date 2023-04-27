package com.example.userdatams.service.impl;

import com.example.userdatams.exceptions.FavoriteProviderAlreadyExistsException;
import com.example.userdatams.repository.FavoriteProviderRepository;
import com.example.userdatams.repository.model.FavoriteProvider;
import com.example.userdatams.service.FavoriteProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FavoriteProviderServiceImpl implements FavoriteProviderService {
    private final FavoriteProviderRepository repository;
    @Override
    public void addFavoriteProvider(Long clientId, Long providerId){
        if(Boolean.FALSE.equals(repository.existsByClientIdAndProviderId(clientId, providerId))){
            repository.save(
                    FavoriteProvider.builder()
                            .clientId(clientId)
                            .providerId(providerId)
                            .build()
            );
        } else throw new FavoriteProviderAlreadyExistsException();

    }

    @Override
    public void removeFavoriteProvider(Long clientId, Long providerId){
        Long id = repository.findByClientIdAndProviderId(clientId, providerId).getId();
        repository.deleteById(id);
    }

    @Override
    public List<Long> getClientsFavoriteProvidersList(Long clientId){
        List<FavoriteProvider> list = repository.findAllByClientId(clientId);
        return list.stream()
                .map(FavoriteProvider::getProviderId)
                .toList();
    }
}
