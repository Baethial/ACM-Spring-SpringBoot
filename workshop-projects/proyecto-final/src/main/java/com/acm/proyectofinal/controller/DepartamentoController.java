package com.acm.proyectofinal.controller;

import com.acm.proyectofinal.entity.Departamento;
import com.acm.proyectofinal.service.DepartamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    private final DepartamentoService service;

    public DepartamentoController(DepartamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Departamento> crear(@RequestBody Departamento d){ return ResponseEntity.ok(service.save(d)); }

    @GetMapping
    public List<Departamento> listar(){ return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> porId(@PathVariable Long id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> actualizar(@PathVariable Long id, @RequestBody Departamento d){
        return service.findById(id).map(existing -> {
            d.setIdDepartamento(existing.getIdDepartamento());
            return ResponseEntity.ok(service.save(d));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
