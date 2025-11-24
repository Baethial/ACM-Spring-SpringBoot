package com.acm.proyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore; // Importante para romper el ciclo
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

    // AÑADE @JsonIgnore AQUÍ
    // Cuando serializamos una Venta, se incluye el Usuario, y este no intentará serializar
    // recursivamente su lista de ventas, rompiendo el ciclo.
    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Venta> ventas;
}