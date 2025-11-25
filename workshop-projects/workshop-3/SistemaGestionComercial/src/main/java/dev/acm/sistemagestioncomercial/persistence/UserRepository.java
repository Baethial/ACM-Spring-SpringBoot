package dev.acm.sistemagestioncomercial.persistence;

import dev.acm.sistemagestioncomercial.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
