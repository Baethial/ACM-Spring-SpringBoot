package dev.acm.sistemagestioncomercial.service;

import dev.acm.sistemagestioncomercial.entity.ProductEntity;
import dev.acm.sistemagestioncomercial.entity.ProductsStoresEntity;
import dev.acm.sistemagestioncomercial.persistence.ProductsStoresRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class ProductsStoresService {

    private final ProductsStoresRepository productsStoresRepository;

    public ProductsStoresEntity getProductsStoresById(Long id){
        return productsStoresRepository.findById(id).orElse(null);
    }

    public List<ProductsStoresEntity> getAllProductsStores(){
        return productsStoresRepository.findAll();
    }

    public ProductsStoresEntity saveProductsStores(ProductsStoresEntity productsStoresEntity){
        return productsStoresRepository.save(productsStoresEntity);
    }

    public void deleteProductsStores(Long id){
        productsStoresRepository.deleteById(id);
    }

    public ProductsStoresEntity updateProductsStores(ProductsStoresEntity productsStoresEntity){
        return productsStoresRepository.save(productsStoresEntity);
    }

    public List<ProductEntity> getAllProductsByStore(String storeName){
        return productsStoresRepository.findAllByStoreEntity_Name(storeName)
                .stream()
                .map(ProductsStoresEntity::getProductEntity)
                .toList();
    }
}
