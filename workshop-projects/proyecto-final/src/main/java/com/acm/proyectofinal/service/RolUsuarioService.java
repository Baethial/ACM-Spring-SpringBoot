package com.acm.proyectofinal.service;

import com.acm.proyectofinal.entity.RolUsuario;
import com.acm.proyectofinal.repository.RolUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolUsuarioService {

    private final RolUsuarioRepository repo;

    public RolUsuarioService(RolUsuarioRepository repo) {
        this.repo = repo;
    }

    // CRUD Básico
    public RolUsuario save(RolUsuario r){ return repo.save(r); }
    public List<RolUsuario> findAll(){ return repo.findAll(); }
    public Optional<RolUsuario> findById(Long id){ return repo.findById(id); }
    public void delete(Long id){ repo.deleteById(id); }
}