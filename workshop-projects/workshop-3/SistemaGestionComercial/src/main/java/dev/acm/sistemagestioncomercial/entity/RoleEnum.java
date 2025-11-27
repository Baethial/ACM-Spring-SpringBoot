package dev.acm.sistemagestioncomercial.entity;

import lombok.Getter;

@Getter
public enum RoleEnum {

    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN"),
    ROLE_MODERATOR("MODERATOR");

    private final String roleName;

    RoleEnum(String roleName) {
        this.roleName = roleName;
    }
}
