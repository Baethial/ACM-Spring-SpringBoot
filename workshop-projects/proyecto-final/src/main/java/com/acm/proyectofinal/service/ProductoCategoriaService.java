package com.acm.proyectofinal.service;

import com.acm.proyectofinal.entity.ProductoCategoria;
import com.acm.proyectofinal.repository.ProductoCategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoCategoriaService {

    private final ProductoCategoriaRepository repo;

    public ProductoCategoriaService(ProductoCategoriaRepository repo) {
        this.repo = repo;
    }

    // CRUD Básico
    public ProductoCategoria save(ProductoCategoria pc){ return repo.save(pc); }
    public List<ProductoCategoria> findAll(){ return repo.findAll(); }
    public Optional<ProductoCategoria> findById(Long id){ return repo.findById(id); }
    public void delete(Long id){ repo.deleteById(id); }
}