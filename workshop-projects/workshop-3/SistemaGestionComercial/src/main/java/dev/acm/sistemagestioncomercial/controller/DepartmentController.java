package dev.acm.sistemagestioncomercial.controller;

import dev.acm.sistemagestioncomercial.entity.DepartmentEntity;
import dev.acm.sistemagestioncomercial.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentEntity> getDepartmentById(@PathVariable Long id){
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentEntity>> getAllDepartments(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PostMapping
    public ResponseEntity<DepartmentEntity> saveDepartment(@RequestBody DepartmentEntity departmentEntity){
        return ResponseEntity.ok(departmentService.saveDepartment(departmentEntity));
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartment(id);
    }

    @PutMapping
    public ResponseEntity<DepartmentEntity> updateDepartment(@RequestBody DepartmentEntity departmentEntity){
        return ResponseEntity.ok(departmentService.updateDepartment(departmentEntity));
    }
}
