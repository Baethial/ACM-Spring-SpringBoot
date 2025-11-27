package dev.acm.sistemagestioncomercial.persistence;

import dev.acm.sistemagestioncomercial.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, Long> {

    List<SaleEntity> findAllByUserEntity_id(Long userId);

    @Query("SELECT SUM(s.total) FROM SaleEntity s WHERE CAST(s.sellDate AS date) = :saleDate")
    Double getTotalSalesByDate(@Param("saleDate") LocalDate saleDate);

    List<SaleEntity> findAllByTotalGreaterThan(Double total);

    List<SaleEntity> id(Long id);
}
