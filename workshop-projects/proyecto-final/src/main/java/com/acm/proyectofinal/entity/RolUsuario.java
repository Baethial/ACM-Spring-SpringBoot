package com.acm.proyectofinal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rol_usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;

    private String rol;
}
