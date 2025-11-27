package dev.acm.sistemagestioncomercial.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ventas_productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesProductsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta_producto")
    private Long id;
    @Column(name = "cantidad")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venta_fk")
    private SaleEntity saleEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto_fk")
    private ProductEntity productEntity;
}
