package dev.acm.sistemagestioncomercial.persistence;

import dev.acm.sistemagestioncomercial.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByLastName(String lastName);
    List<UserEntity> findAllByCity_Name(String cityName);
    List<UserEntity> findAllByCity_Department_Name(String departmentName);
    List<UserEntity> findAllByFirstNameContaining(String nameFragment);
}
