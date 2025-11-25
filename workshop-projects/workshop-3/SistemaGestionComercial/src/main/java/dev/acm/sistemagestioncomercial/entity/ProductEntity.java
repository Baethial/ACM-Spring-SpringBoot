package dev.acm.sistemagestioncomercial.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

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
}
