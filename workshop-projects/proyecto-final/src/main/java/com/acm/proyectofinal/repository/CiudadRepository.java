package com.acm.proyectofinal.repository;

import com.acm.proyectofinal.entity.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
    List<Ciudad> findByDepartamento_NombreIgnoreCase(String departamentoNombre);
}
