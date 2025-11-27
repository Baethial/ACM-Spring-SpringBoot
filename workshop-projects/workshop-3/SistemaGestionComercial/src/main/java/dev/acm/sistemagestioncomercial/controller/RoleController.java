package dev.acm.sistemagestioncomercial.controller;

import dev.acm.sistemagestioncomercial.entity.RoleEntity;
import dev.acm.sistemagestioncomercial.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/{id}")
    public ResponseEntity<RoleEntity> getRoleById(@PathVariable Long id){
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @GetMapping
    public ResponseEntity<List<RoleEntity>> getAllRoles(){
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @PostMapping
    public ResponseEntity<RoleEntity> saveRole(@RequestBody RoleEntity roleEntity){
        return ResponseEntity.ok(roleService.saveRole(roleEntity));
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id){
        roleService.deleteRole(id);
    }

    @PutMapping
    public ResponseEntity<RoleEntity> updateRole(@RequestBody RoleEntity roleEntity){
        return ResponseEntity.ok(roleService.updateRole(roleEntity));
    }
}
