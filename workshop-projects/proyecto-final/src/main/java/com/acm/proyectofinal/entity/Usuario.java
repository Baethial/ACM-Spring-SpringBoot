package com.acm.proyectofinal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name = "idRolFK")
    private RolUsuario rol;

    private String nombre;
    private String apellido;
    private String username;
    private String password;
    private String email;
    private LocalDateTime fechaRegistro;
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "idCiudadFK")
    private Ciudad ciudad;

    @OneToMany(mappedBy = "cliente")
    private List<Venta> ventas;
}
