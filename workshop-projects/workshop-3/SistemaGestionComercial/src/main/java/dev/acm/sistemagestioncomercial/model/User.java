package dev.acm.sistemagestioncomercial.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long id;
    private Role roleEntity;
    // private City city;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private LocalDateTime registrationDate;
    private String phoneNumber;
}
