package dev.acm.sistemagestioncomercial.persistence;

import dev.acm.sistemagestioncomercial.entity.ProductsStoresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsStoresRepository extends JpaRepository<ProductsStoresEntity, Long> {

    List<ProductsStoresEntity> findAllByStoreEntity_Name(String storeName);
}
