package dev.acm.sistemagestioncomercial.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long id;
    @Column(name = "nombre")
    private String name;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "productos_categorias",
            joinColumns = @JoinColumn(name = "id_categoria"),
            inverseJoinColumns = @JoinColumn(name = "id_producto"))
    private List<ProductEntity> products;
}
