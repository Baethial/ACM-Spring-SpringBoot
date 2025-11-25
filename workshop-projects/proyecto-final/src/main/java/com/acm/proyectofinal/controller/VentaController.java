package com.acm.proyectofinal.controller;

import com.acm.proyectofinal.entity.Venta;
import com.acm.proyectofinal.service.VentaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {
    private final VentaService service;
    public VentaController(VentaService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Venta> crear(@RequestBody Venta v){ return ResponseEntity.ok(service.save(v)); }

    @GetMapping
    public List<Venta> listar(){ return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> porId(@PathVariable Long id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/por-cliente/{clienteId}")
    public List<Venta> porCliente(@PathVariable Long clienteId){ return service.listarPorCliente(clienteId); }

    @GetMapping("/total-por-fecha")
    public Long consultarTotalVendidoPorFecha(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha){
        return service.consultarTotalVendidoPorFecha(fecha);
    }

    @GetMapping("/mayor-que")
    public List<Venta> ventasMayorA(@RequestParam Long monto){ return service.ventasMayorA(monto); }
}
