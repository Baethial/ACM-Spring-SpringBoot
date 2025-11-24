package com.acm.proyectofinal.service;

import com.acm.proyectofinal.entity.Ciudad;
import com.acm.proyectofinal.repository.CiudadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadService {

    private final CiudadRepository repo;

    public CiudadService(CiudadRepository repo) {
        this.repo = repo;
    }

    public Ciudad save(Ciudad c){ return repo.save(c); }
    public List<Ciudad> findAll(){ return repo.findAll(); }
    public Optional<Ciudad> findById(Long id){ return repo.findById(id); }
    public void delete(Long id){ repo.deleteById(id); }

    public List<Ciudad> findByDepartamentoNombre(String departamentoNombre){
        return repo.findByDepartamento_NombreIgnoreCase(departamentoNombre);
    }
}
