package dev.acm.sistemagestioncomercial.controller;

import dev.acm.sistemagestioncomercial.entity.CategoryEntity;
import dev.acm.sistemagestioncomercial.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryEntity> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping
    public ResponseEntity<Iterable<CategoryEntity>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping
    public ResponseEntity<CategoryEntity> saveCategory(CategoryEntity categoryEntity){
        return ResponseEntity.ok(categoryService.saveCategory(categoryEntity));
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }

    @PutMapping
    public ResponseEntity<CategoryEntity> updateCategory(@RequestBody CategoryEntity categoryEntity){
        return ResponseEntity.ok(categoryService.updateCategory(categoryEntity));
    }
}
