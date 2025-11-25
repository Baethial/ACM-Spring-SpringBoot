package dev.acm.sistemagestioncomercial.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nombre")
    private String firstName;
    @Column(name = "apellido")
    private String lastName;
    private String password;
    private String email;
    @Column(name = "fecha_registro")
    private LocalDateTime registrationDate;
    @Column(name = "telefono")
    private String phoneNumber;

    // This is the "many" side of the relationship
    // This class handles the relationship
    @ManyToOne(fetch = FetchType.LAZY)
    // This annotation goes in the class that handles the relationship
    @JoinColumn(name = "id_rol_fk", nullable = false)
    private RoleEntity roleEntity;
    // private City city;
}
