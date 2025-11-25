package com.acm.proyectofinal.repository;

import com.acm.proyectofinal.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    // 11. Listar ventas por usuario cliente.
    List<Venta> findByCliente_IdUsuario(Long clienteId);

    /**
     * 12. Consulta Total Vendido por Fecha (Corregida)
     * Utiliza la función 'DATE' (específica de JPA/Hibernate) para extraer
     * la parte de la fecha de la columna LocalDateTime y compararla con el LocalDate de entrada.
     * Retorna la suma total de las ventas.
     */
    @Query("SELECT SUM(v.total) FROM Venta v WHERE FUNCTION('DATE', v.fechaVenta) = :fecha")
    Long consultarTotalVendidoPorFecha(java.time.LocalDate fecha);
    // Nota: El retorno es Long porque el campo 'total' en tu entidad Venta es Long.
    // Si hubieras usado BigDecimal para 'total', el retorno aquí debería ser BigDecimal.

    // 13. Buscar ventas con monto mayor a X valor.
    List<Venta> findByTotalGreaterThan(Long monto);
}