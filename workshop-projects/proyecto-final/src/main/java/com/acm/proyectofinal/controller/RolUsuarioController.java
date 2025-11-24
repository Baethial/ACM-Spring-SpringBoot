package com.acm.proyectofinal.controller;

import com.acm.proyectofinal.entity.RolUsuario;
import com.acm.proyectofinal.service.RolUsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolUsuarioController {

    private final RolUsuarioService service;

    public RolUsuarioController(RolUsuarioService service) {
        this.service = service;
    }

    // CRUD: CREAR (CREATE)
    @PostMapping
    public ResponseEntity<RolUsuario> crear(@RequestBody RolUsuario r){
        return ResponseEntity.ok(service.save(r));
    }

    // CRUD: LISTAR TODOS (READ)
    @GetMapping
    public List<RolUsuario> listar(){
        return service.findAll();
    }

    // CRUD: BUSCAR POR ID (READ)
    @GetMapping("/{id}")
    public ResponseEntity<RolUsuario> porId(@PathVariable Long id){
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CRUD: ACTUALIZAR (UPDATE)
    @PutMapping("/{id}")
    public ResponseEntity<RolUsuario> actualizar(@PathVariable Long id, @RequestBody RolUsuario r){
        return service.findById(id).map(existing -> {
            r.setIdRol(existing.getIdRol());
            return ResponseEntity.ok(service.save(r));
        }).orElse(ResponseEntity.notFound().build());
    }

    // CRUD: ELIMINAR (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}