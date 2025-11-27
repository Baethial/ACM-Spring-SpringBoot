package dev.acm.sistemagestioncomercial.service;

import dev.acm.sistemagestioncomercial.entity.CategoryEntity;
import dev.acm.sistemagestioncomercial.persistence.CategoryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryEntity getCategoryById(Long id){
        return categoryRepository.findById(id).orElse(null);
    }

    public List<CategoryEntity> getAllCategories(){
        return categoryRepository.findAll();
    }

    public CategoryEntity saveCategory(CategoryEntity categoryEntity){
        return categoryRepository.save(categoryEntity);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

    public CategoryEntity updateCategory(CategoryEntity categoryEntity){
        return categoryRepository.save(categoryEntity);
    }

}
