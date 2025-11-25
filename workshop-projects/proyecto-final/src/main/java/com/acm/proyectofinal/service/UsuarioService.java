package com.acm.proyectofinal.service;

import com.acm.proyectofinal.entity.Usuario;
import com.acm.proyectofinal.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public Usuario save(Usuario u) { return repo.save(u); }
    public List<Usuario> findAll() { return repo.findAll(); }
    public Optional<Usuario> findById(Long id) { return repo.findById(id); }
    public void delete(Long id) { repo.deleteById(id); }

    // Queries
    public List<Usuario> buscarPorApellido(String apellido) { return repo.findByApellidoContainingIgnoreCase(apellido); }
    public List<Usuario> listarPorCiudad(String ciudad) { return repo.findByCiudad_NombreIgnoreCase(ciudad); }
    public List<Usuario> listarPorDepartamento(String departamento) { return repo.findByCiudad_Departamento_NombreIgnoreCase(departamento); }
    public List<Usuario> buscarPorNombreConteniendo(String texto) { return repo.findByNombreContainingIgnoreCase(texto); }
}
