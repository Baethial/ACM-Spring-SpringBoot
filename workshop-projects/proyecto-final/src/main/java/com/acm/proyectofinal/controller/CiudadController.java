package com.acm.proyectofinal.controller;

import com.acm.proyectofinal.entity.Ciudad;
import com.acm.proyectofinal.service.CiudadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ciudades")
public class CiudadController {

    private final CiudadService service;

    public CiudadController(CiudadService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Ciudad> crear(@RequestBody Ciudad c){ return ResponseEntity.ok(service.save(c)); }

    @GetMapping
    public List<Ciudad> listar(){ return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> porId(@PathVariable Long id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ciudad> actualizar(@PathVariable Long id, @RequestBody Ciudad c){
        return service.findById(id).map(existing -> {
            c.setIdCiudad(existing.getIdCiudad());
            return ResponseEntity.ok(service.save(c));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Listar por departamento (útil para algunas consultas)
    @GetMapping("/por-departamento")
    public List<Ciudad> porDepartamento(@RequestParam String departamento){
        return service.findByDepartamentoNombre(departamento);
    }
}
