package dev.acm.sistemagestioncomercial.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long id;
    @Column(name = "rol")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    /*// This entity does not handle the relationship
    @OneToMany(mappedBy = "roleEntity", fetch = FetchType.LAZY) // name of the Java attribute
    private List<UserEntity> users = new ArrayList<>();*/
}
