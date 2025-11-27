package dev.acm.sistemagestioncomercial.persistence;

import dev.acm.sistemagestioncomercial.entity.ProductEntity;
import dev.acm.sistemagestioncomercial.entity.SalesProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesProductsRepository extends JpaRepository<SalesProductsEntity, Long> {

    @Query("SELECT sp.productEntity FROM SalesProductsEntity sp " +
            "GROUP BY sp.productEntity.id " +
            "ORDER BY SUM(sp.quantity) DESC " +
            "LIMIT 3")
    List<ProductEntity> getTopThreeBestSellingProducts();

}
