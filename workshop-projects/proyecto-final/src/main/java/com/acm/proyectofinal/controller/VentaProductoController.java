package com.acm.proyectofinal.controller;

import com.acm.proyectofinal.entity.VentaProducto;
import com.acm.proyectofinal.service.VentaProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venta-detalles") // Detalles de una venta
public class VentaProductoController {

    private final VentaProductoService service;

    public VentaProductoController(VentaProductoService service) {
        this.service = service;
    }

    // CRUD: CREAR (CREATE)
    @PostMapping
    public ResponseEntity<VentaProducto> crear(@RequestBody VentaProducto vp){
        return ResponseEntity.ok(service.save(vp));
    }

    // CRUD: LISTAR TODOS (READ)
    @GetMapping
    public List<VentaProducto> listar(){
        return service.findAll();
    }

    // CRUD: BUSCAR POR ID (READ)
    @GetMapping("/{id}")
    public ResponseEntity<VentaProducto> porId(@PathVariable Long id){
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CRUD: ACTUALIZAR (UPDATE)
    @PutMapping("/{id}")
    public ResponseEntity<VentaProducto> actualizar(@PathVariable Long id, @RequestBody VentaProducto vp){
        return service.findById(id).map(existing -> {
            vp.setId(existing.getId());
            return ResponseEntity.ok(service.save(vp));
        }).orElse(ResponseEntity.notFound().build());
    }

    // CRUD: ELIMINAR (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}