package com.example.userdatams.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "favourite_providers")
public class FavoriteProvider {
    @NotNull
    private Long clientId;
    @NotNull
    private Long providerId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
