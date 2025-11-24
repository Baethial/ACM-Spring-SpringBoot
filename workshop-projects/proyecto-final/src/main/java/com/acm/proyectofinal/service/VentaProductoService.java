package com.acm.proyectofinal.service;

import com.acm.proyectofinal.entity.VentaProducto;
import com.acm.proyectofinal.repository.VentaProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaProductoService {

    private final VentaProductoRepository repo;

    public VentaProductoService(VentaProductoRepository repo) {
        this.repo = repo;
    }

    // CRUD Básico
    public VentaProducto save(VentaProducto vp){ return repo.save(vp); }
    public List<VentaProducto> findAll(){ return repo.findAll(); }
    public Optional<VentaProducto> findById(Long id){ return repo.findById(id); }
    public void delete(Long id){ repo.deleteById(id); }
}