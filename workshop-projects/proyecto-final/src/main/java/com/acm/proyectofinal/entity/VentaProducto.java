package com.acm.proyectofinal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "venta_producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idVentaFK")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "idProductoFK")
    private Producto producto;

    private Long cantidad;
}
