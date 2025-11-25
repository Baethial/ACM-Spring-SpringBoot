package dev.acm.sistemagestioncomercial.model;

import dev.acm.sistemagestioncomercial.entity.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    private Long id;
    private RoleEnum role;
}
