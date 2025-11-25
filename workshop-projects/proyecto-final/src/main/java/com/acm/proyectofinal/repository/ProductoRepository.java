package com.acm.proyectofinal.repository;

import com.acm.proyectofinal.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByProductoCategorias_Categoria_NombreIgnoreCase(String categoriaNombre);

    List<Producto> findByPrecioBetween(BigDecimal min, BigDecimal max);

    List<Producto> findByOrderByPrecioAsc();
    List<Producto> findByOrderByPrecioDesc();

    List<Producto> findByFechaCreacionAfter(LocalDateTime fecha);

    // Productos más vendidos (top N) - JPQL con conteo
    @Query("SELECT p FROM Producto p JOIN p.ventaProductos vp GROUP BY p ORDER BY SUM(vp.cantidad) DESC")
    List<Producto> findProductosMasVendidos();
}
