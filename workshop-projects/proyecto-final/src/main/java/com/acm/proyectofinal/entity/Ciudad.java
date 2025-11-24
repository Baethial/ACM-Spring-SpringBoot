package com.acm.proyectofinal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "ciudad")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ciudad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCiudad;

    @ManyToOne
    @JoinColumn(name = "idDepartamentoFK")
    private Departamento departamento;

    private String nombre;

    @OneToMany(mappedBy = "ciudad")
    private List<Almacen> almacenes;
}
