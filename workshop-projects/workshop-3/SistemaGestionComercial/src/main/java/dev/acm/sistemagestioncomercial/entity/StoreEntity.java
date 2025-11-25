package dev.acm.sistemagestioncomercial.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "almacenes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_almacen")
    private Long id;
    @Column(name = "nombre")
    private String name;

}
