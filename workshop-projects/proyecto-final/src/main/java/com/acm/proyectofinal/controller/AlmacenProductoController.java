package com.acm.proyectofinal.controller;

import com.acm.proyectofinal.entity.AlmacenProducto;
import com.acm.proyectofinal.service.AlmacenProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario") // Endpoint para gestionar el inventario
public class AlmacenProductoController {

    private final AlmacenProductoService service;

    public AlmacenProductoController(AlmacenProductoService service) {
        this.service = service;
    }

    // CRUD: CREAR (CREATE)
    @PostMapping
    public ResponseEntity<AlmacenProducto> crear(@RequestBody AlmacenProducto ap){
        return ResponseEntity.ok(service.save(ap));
    }

    // CRUD: LISTAR TODOS (READ)
    @GetMapping
    public List<AlmacenProducto> listar(){
        return service.findAll();
    }

    // CRUD: BUSCAR POR ID (READ)
    @GetMapping("/{id}")
    public ResponseEntity<AlmacenProducto> porId(@PathVariable Long id){
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CRUD: ACTUALIZAR (UPDATE)
    @PutMapping("/{id}")
    public ResponseEntity<AlmacenProducto> actualizar(@PathVariable Long id, @RequestBody AlmacenProducto ap){
        return service.findById(id).map(existing -> {
            ap.setId(existing.getId());
            return ResponseEntity.ok(service.save(ap));
        }).orElse(ResponseEntity.notFound().build());
    }

    // CRUD: ELIMINAR (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Consultas específicas de Almacén-Producto
    // Se recomienda usar el AlmacenController para la consulta 9, pero es útil tener este.
    @GetMapping("/por-producto/{idProducto}")
    public List<AlmacenProducto> listarPorProducto(@PathVariable Long idProducto) {
        return service.findByProductoId(idProducto);
    }
}