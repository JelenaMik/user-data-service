package com.example.userdatams.repository;

import com.example.userdatams.repository.model.FavoriteProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteProviderRepository extends JpaRepository<FavoriteProvider, Long> {
    FavoriteProvider findByClientIdAndProviderId(Long clientId, Long providerId);
    Boolean existsByClientIdAndProviderId(Long clientId, Long providerId);
    List<FavoriteProvider> findAllByClientId(Long clientId);
}
