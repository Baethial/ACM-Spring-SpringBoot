package com.acm.proyectofinal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    private String nombre;
    private BigDecimal precio;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    @OneToMany(mappedBy = "producto")
    private List<AlmacenProducto> almacenProductos;

    @OneToMany(mappedBy = "producto")
    private List<ProductoCategoria> productoCategorias;

    @OneToMany(mappedBy = "producto")
    private List<VentaProducto> ventaProductos;
}
