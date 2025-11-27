package dev.acm.sistemagestioncomercial.service;

import dev.acm.sistemagestioncomercial.entity.DepartmentEntity;
import dev.acm.sistemagestioncomercial.persistence.DepartmentRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentEntity getDepartmentById(Long id){
        return departmentRepository.findById(id).orElse(null);
    }

    public List<DepartmentEntity> getAllDepartments(){
        return departmentRepository.findAll();
    }

    public DepartmentEntity saveDepartment(DepartmentEntity departmentEntity){
        return departmentRepository.save(departmentEntity);
    }

    public void deleteDepartment(Long id){
        departmentRepository.deleteById(id);
    }

    public DepartmentEntity updateDepartment(DepartmentEntity departmentEntity){
        return departmentRepository.save(departmentEntity);
    }
}
