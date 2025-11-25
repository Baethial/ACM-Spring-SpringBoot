package dev.acm.sistemagestioncomercial.persistence;

import dev.acm.sistemagestioncomercial.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
