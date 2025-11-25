package com.acm.proyectofinal.controller;

import com.acm.proyectofinal.entity.Categoria;
import com.acm.proyectofinal.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Categoria> crear(@RequestBody Categoria c){
        return ResponseEntity.ok(service.save(c));
    }

    @GetMapping
    public List<Categoria> listar(){ return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> porId(@PathVariable Long id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(@PathVariable Long id, @RequestBody Categoria c){
        return service.findById(id).map(existing -> {
            c.setIdCategoria(existing.getIdCategoria());
            return ResponseEntity.ok(service.save(c));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
