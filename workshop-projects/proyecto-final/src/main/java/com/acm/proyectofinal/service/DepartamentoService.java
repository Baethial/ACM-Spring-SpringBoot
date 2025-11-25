package com.acm.proyectofinal.service;

import com.acm.proyectofinal.entity.Departamento;
import com.acm.proyectofinal.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    private final DepartamentoRepository repo;

    public DepartamentoService(DepartamentoRepository repo) {
        this.repo = repo;
    }

    public Departamento save(Departamento d){ return repo.save(d); }
    public List<Departamento> findAll(){ return repo.findAll(); }
    public Optional<Departamento> findById(Long id){ return repo.findById(id); }
    public void delete(Long id){ repo.deleteById(id); }
}
