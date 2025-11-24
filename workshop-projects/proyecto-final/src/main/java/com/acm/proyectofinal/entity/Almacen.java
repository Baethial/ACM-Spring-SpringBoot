package com.acm.proyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "almacen")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Almacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlmacen;

    @ManyToOne
    @JoinColumn(name = "idCiudadFK")
    private Ciudad ciudad;

    private String nombre;
    @JsonIgnore
    @OneToMany(mappedBy = "almacen")
    private List<AlmacenProducto> almacenProductos;
}
