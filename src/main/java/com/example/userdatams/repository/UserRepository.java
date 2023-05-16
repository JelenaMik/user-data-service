package com.example.userdatams.repository;

import com.example.userdatams.enums.Role;
import com.example.userdatams.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    List<User> findFirst10ByOrderByIdAsc();
    List <User> findFirst10ByEmailContaining(String email);
    List <User> findFirst10ByRole(Role role);
    Boolean existsByEmail(String email);
    Boolean existsByRoleAndId(Role role, Long Id);

}
