package com.acm.proyectofinal.controller;

import com.acm.proyectofinal.entity.Almacen;
import com.acm.proyectofinal.entity.AlmacenProducto;
import com.acm.proyectofinal.service.AlmacenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/almacenes")
public class AlmacenController {

    private final AlmacenService service;

    public AlmacenController(AlmacenService service) {
        this.service = service;
    }

    // CRUD
    @PostMapping
    public ResponseEntity<Almacen> crear(@RequestBody Almacen a){
        return ResponseEntity.ok(service.save(a));
    }

    @GetMapping
    public List<Almacen> listar(){ return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Almacen> porId(@PathVariable Long id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Almacen> actualizar(@PathVariable Long id, @RequestBody Almacen a){
        return service.findById(id).map(existing -> {
            a.setIdAlmacen(existing.getIdAlmacen());
            return ResponseEntity.ok(service.save(a));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Consultas pedidas por la entrega
    @GetMapping("/por-ciudad")
    public List<Almacen> almacenesPorCiudad(@RequestParam String ciudad){
        return service.listarPorCiudad(ciudad);
    }

    @GetMapping("/{id}/productos")
    public List<AlmacenProducto> productosEnAlmacen(@PathVariable Long id){
        return service.listarProductosEnAlmacen(id);
    }
}
