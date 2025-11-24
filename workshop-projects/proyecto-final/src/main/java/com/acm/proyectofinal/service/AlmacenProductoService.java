package com.acm.proyectofinal.service;

import com.acm.proyectofinal.entity.AlmacenProducto;
import com.acm.proyectofinal.repository.AlmacenProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlmacenProductoService {

    private final AlmacenProductoRepository repo;

    public AlmacenProductoService(AlmacenProductoRepository repo) {
        this.repo = repo;
    }

    // CRUD Básico
    public AlmacenProducto save(AlmacenProducto ap){ return repo.save(ap); }
    public List<AlmacenProducto> findAll(){ return repo.findAll(); }
    public Optional<AlmacenProducto> findById(Long id){ return repo.findById(id); }
    public void delete(Long id){ repo.deleteById(id); }

    // Consultas útiles (Ya definidas en el Repositorio)
    public List<AlmacenProducto> findByAlmacenId(Long idAlmacen) {
        return repo.findByAlmacen_IdAlmacen(idAlmacen);
    }

    public List<AlmacenProducto> findByProductoId(Long idProducto) {
        return repo.findByProducto_IdProducto(idProducto);
    }
}