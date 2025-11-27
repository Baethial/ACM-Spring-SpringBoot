package dev.acm.sistemagestioncomercial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "precio")
    private Double price;
    @Column(name = "descripcion")
    private String description;
    @Column(name = "fecha_creacion")
    @CreationTimestamp
    private LocalDateTime creationDate;
    @Column(name = "fecha_actualizacion")
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name = "productos_categorias",
            joinColumns = @JoinColumn(name = "id_producto_fk"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria_fk"))
    private List<CategoryEntity> categories;
}
