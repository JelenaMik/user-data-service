package com.example.userdatams.repository.model;

import jakarta.persistence.Column;
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
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

//@ApiModel(value = "Model of authentication credential")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "userData")
public class UserDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @NotNull
    @Column (name="userId")
    private Long userId;
    @NotNull
    @NotBlank
    @Column(name="firstName")
    private String firstName;
    @NotNull
    @NotBlank
    @Column(name="lastName")
    private String lastName;
    @Column(name="registrationDate")
    @CreationTimestamp
    private LocalDateTime registrationDate;

}
