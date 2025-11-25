package com.acm.proyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "venta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    // LADO QUE SE MANTIENE
    // Cuando serialices una Venta, se incluirá la información de su cliente (Usuario).
    @ManyToOne
    @JoinColumn(name = "idClienteFK")
    private Usuario cliente;

    private LocalDateTime fechaVenta;

    private Long total;

    // El JsonIgnore en 'detalles' evita que al listar ventas se listen también los productos de esa venta.
    @JsonIgnore
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<VentaProducto> detalles;
}