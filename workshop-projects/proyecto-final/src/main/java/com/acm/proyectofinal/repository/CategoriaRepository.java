package com.acm.proyectofinal.repository;

import com.acm.proyectofinal.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
