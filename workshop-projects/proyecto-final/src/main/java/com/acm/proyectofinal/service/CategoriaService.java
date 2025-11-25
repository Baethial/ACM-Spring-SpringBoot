package com.acm.proyectofinal.service;

import com.acm.proyectofinal.entity.Categoria;
import com.acm.proyectofinal.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository repo;

    public CategoriaService(CategoriaRepository repo) {
        this.repo = repo;
    }

    public Categoria save(Categoria c){ return repo.save(c); }
    public List<Categoria> findAll(){ return repo.findAll(); }
    public Optional<Categoria> findById(Long id){ return repo.findById(id); }
    public void delete(Long id){ repo.deleteById(id); }
}
