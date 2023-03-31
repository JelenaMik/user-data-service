package com.example.userdatams.repository;

import com.example.userdatams.repository.model.UserDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserDataEntity, Long> {

    List<UserDataEntity> findAll();
    Optional<UserDataEntity> findByUserId(Long userId);

    List<UserDataEntity> findFirst10ByFirstNameContaining(String firstName);
}
