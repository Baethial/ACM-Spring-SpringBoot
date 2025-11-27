package dev.acm.sistemagestioncomercial.service;

import dev.acm.sistemagestioncomercial.entity.ProductEntity;
import dev.acm.sistemagestioncomercial.persistence.ProductRepository;
import dev.acm.sistemagestioncomercial.persistence.SalesProductsRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final SalesProductsRepository salesProductsRepository;

    public ProductEntity getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public List<ProductEntity> getAllProducts(){
        return productRepository.findAll();
    }

    public ProductEntity saveProduct(ProductEntity productEntity){
        return productRepository.save(productEntity);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public ProductEntity updateProduct(ProductEntity productEntity){
        return productRepository.save(productEntity);
    }

    // Advanced

    public List<ProductEntity> getAllProductsByCategory(String categoryName){
        return productRepository.findAllByCategories_Name(categoryName);
    }

    public List<ProductEntity> getAllProductsByPriceBetween(Double minPrice, Double maxPrice){
        return productRepository.findAllByPriceBetween(minPrice, maxPrice);
    }

    public List<ProductEntity> getAllProductsByCreationDateAfter(LocalDateTime date){
        return productRepository.findAllByCreationDateAfter(date);
    }

    public List<ProductEntity> getAllProductsByPriceOrderByPriceDesc(){
        return productRepository.findAllByOrderByPriceDesc();
    }

    public List<ProductEntity> getAllProductsByPriceOrderByPriceAsc(){
        return productRepository.findAllByOrderByPriceAsc();
    }

    public List<ProductEntity> getBestSellerProducts(){
        return salesProductsRepository.getTopThreeBestSellingProducts();
    }
}
