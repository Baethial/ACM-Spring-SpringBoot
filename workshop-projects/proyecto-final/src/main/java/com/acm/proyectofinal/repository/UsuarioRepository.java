package com.acm.proyectofinal.repository;

import com.acm.proyectofinal.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByApellidoContainingIgnoreCase(String apellido);
    List<Usuario> findByCiudad_NombreIgnoreCase(String nombreCiudad);
    List<Usuario> findByCiudad_Departamento_NombreIgnoreCase(String nombreDepartamento);
    List<Usuario> findByNombreContainingIgnoreCase(String texto);
}
