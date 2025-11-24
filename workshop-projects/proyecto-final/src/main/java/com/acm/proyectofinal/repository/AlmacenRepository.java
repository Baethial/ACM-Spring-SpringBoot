package com.acm.proyectofinal.repository;

import com.acm.proyectofinal.entity.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlmacenRepository extends JpaRepository<Almacen, Long> {
    List<Almacen> findByCiudad_NombreIgnoreCase(String ciudad);
}
