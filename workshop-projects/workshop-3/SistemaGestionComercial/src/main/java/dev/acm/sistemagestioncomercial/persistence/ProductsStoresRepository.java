package dev.acm.sistemagestioncomercial.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsStoresRepository extends JpaRepository<ProductsStoresRepository, Long> {
}
