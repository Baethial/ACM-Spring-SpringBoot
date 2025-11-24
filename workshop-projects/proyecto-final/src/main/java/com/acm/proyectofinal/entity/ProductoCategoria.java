package com.acm.proyectofinal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "producto_categoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoCategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idProductoFK")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "idCategoriaFK")
    private Categoria categoria;
}
