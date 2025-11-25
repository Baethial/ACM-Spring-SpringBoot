package com.acm.proyectofinal.controller;

import com.acm.proyectofinal.entity.Usuario;
import com.acm.proyectofinal.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService service;
    public UsuarioController(UsuarioService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario u){ return ResponseEntity.ok(service.save(u)); }

    @GetMapping
    public List<Usuario> listar(){ return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> porId(@PathVariable Long id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario u){
        return service.findById(id).map(existing -> {
            u.setIdUsuario(existing.getIdUsuario());
            return ResponseEntity.ok(service.save(u));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        service.delete(id); return ResponseEntity.noContent().build();
    }

    // Consultas específicas
    @GetMapping("/buscar/apellido")
    public List<Usuario> buscarPorApellido(@RequestParam String apellido){ return service.buscarPorApellido(apellido); }

    @GetMapping("/buscar/ciudad")
    public List<Usuario> listarPorCiudad(@RequestParam String ciudad){ return service.listarPorCiudad(ciudad); }

    @GetMapping("/buscar/departamento")
    public List<Usuario> listarPorDepartamento(@RequestParam String depto){ return service.listarPorDepartamento(depto); }

    @GetMapping("/buscar/nombre")
    public List<Usuario> buscarPorNombreContiene(@RequestParam String texto){ return service.buscarPorNombreConteniendo(texto); }
}
