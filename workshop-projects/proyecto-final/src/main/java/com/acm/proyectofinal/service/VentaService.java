package com.acm.proyectofinal.service;

import com.acm.proyectofinal.entity.Venta;
import com.acm.proyectofinal.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {
    private final VentaRepository repo;

    public VentaService(VentaRepository repo) {
        this.repo = repo;
    }

    public Venta save(Venta v) { return repo.save(v); }
    public List<Venta> findAll() { return repo.findAll(); }
    public Optional<Venta> findById(Long id) { return repo.findById(id); }
    public void delete(Long id) { repo.deleteById(id); }

    public List<Venta> listarPorCliente(Long clienteId) { return repo.findByCliente_IdUsuario(clienteId); }
    // 12. Consultar total vendido por fecha (Devuelve el valor Long directamente)
    public Long consultarTotalVendidoPorFecha(LocalDate fecha) {
        return repo.consultarTotalVendidoPorFecha(fecha);
    }
    public List<Venta> ventasMayorA(Long monto) { return repo.findByTotalGreaterThan(monto); }
}
