package com.example.userdatams.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserDataDto {

    private Long id;
    private Long userId;

    private String firstName;

    private String lastName;
    @Nullable
    private LocalDateTime registrationDate;
}
