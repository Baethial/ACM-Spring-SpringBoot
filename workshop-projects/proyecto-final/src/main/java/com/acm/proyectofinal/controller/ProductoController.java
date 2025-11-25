package com.acm.proyectofinal.controller;

import com.acm.proyectofinal.entity.Producto;
import com.acm.proyectofinal.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoService service;
    public ProductoController(ProductoService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto p){ return ResponseEntity.ok(service.save(p)); }

    @GetMapping
    public List<Producto> listar(){ return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> porId(@PathVariable Long id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto p){
        return service.findById(id).map(existing -> {
            p.setIdProducto(existing.getIdProducto());
            return ResponseEntity.ok(service.save(p));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        service.delete(id); return ResponseEntity.noContent().build();
    }

    // Consultas
    @GetMapping("/por-categoria")
    public List<Producto> porCategoria(@RequestParam String categoria){ return service.listarPorCategoria(categoria); }

    @GetMapping("/rango-precio")
    public List<Producto> porRangoPrecio(@RequestParam BigDecimal min, @RequestParam BigDecimal max){ return service.buscarPorRangoPrecio(min, max); }

    @GetMapping("/orden/asc")
    public List<Producto> ordenarAsc(){ return service.orderByPrecioAsc(); }

    @GetMapping("/orden/desc")
    public List<Producto> ordenarDesc(){ return service.orderByPrecioDesc(); }

    @GetMapping("/creados-despues")
    public List<Producto> creadosDespues(@RequestParam String fechaIso){ // fechaIso ejemplo: 2025-01-01T00:00:00
        LocalDateTime fecha = LocalDateTime.parse(fechaIso);
        return service.listarProductosCreadoDespues(fecha);
    }

    @GetMapping("/mas-vendidos")
    public List<Producto> masVendidos(){ return service.productosMasVendidos(); }
}
