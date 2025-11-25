package com.acm.proyectofinal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "almacen_producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlmacenProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idAlmacenFK")
    private Almacen almacen;

    @ManyToOne
    @JoinColumn(name = "idProductoFK")
    private Producto producto;

    private Long stock;
    private String direccion;
}
