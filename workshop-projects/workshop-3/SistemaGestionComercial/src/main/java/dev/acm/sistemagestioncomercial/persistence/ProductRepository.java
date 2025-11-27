package dev.acm.sistemagestioncomercial.persistence;

import dev.acm.sistemagestioncomercial.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAllByCategories_Name(String categoryName);
    List<ProductEntity> findAllByPriceBetween(Double minPrice, Double maxPrice);
    List<ProductEntity> findAllByOrderByPriceDesc();
    List<ProductEntity> findAllByOrderByPriceAsc();
    List<ProductEntity> findAllByCreationDateAfter(LocalDateTime date);


}
