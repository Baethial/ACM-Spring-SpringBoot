package com.acm.proyectofinal.repository;

import com.acm.proyectofinal.entity.AlmacenProducto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlmacenProductoRepository extends JpaRepository<AlmacenProducto, Long> {
    List<AlmacenProducto> findByAlmacen_IdAlmacen(Long idAlmacen);
    List<AlmacenProducto> findByProducto_IdProducto(Long idProducto);
}
