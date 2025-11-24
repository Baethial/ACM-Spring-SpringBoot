package com.acm.proyectofinal.controller;

import com.acm.proyectofinal.entity.ProductoCategoria;
import com.acm.proyectofinal.service.ProductoCategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto-categorias")
public class ProductoCategoriaController {

    private final ProductoCategoriaService service;

    public ProductoCategoriaController(ProductoCategoriaService service) {
        this.service = service;
    }

    // CRUD: CREAR (CREATE)
    @PostMapping
    public ResponseEntity<ProductoCategoria> crear(@RequestBody ProductoCategoria pc){
        return ResponseEntity.ok(service.save(pc));
    }

    // CRUD: LISTAR TODOS (READ)
    @GetMapping
    public List<ProductoCategoria> listar(){
        return service.findAll();
    }

    // CRUD: BUSCAR POR ID (READ)
    @GetMapping("/{id}")
    public ResponseEntity<ProductoCategoria> porId(@PathVariable Long id){
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CRUD: ACTUALIZAR (UPDATE)
    @PutMapping("/{id}")
    public ResponseEntity<ProductoCategoria> actualizar(@PathVariable Long id, @RequestBody ProductoCategoria pc){
        return service.findById(id).map(existing -> {
            pc.setId(existing.getId());
            return ResponseEntity.ok(service.save(pc));
        }).orElse(ResponseEntity.notFound().build());
    }

    // CRUD: ELIMINAR (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}