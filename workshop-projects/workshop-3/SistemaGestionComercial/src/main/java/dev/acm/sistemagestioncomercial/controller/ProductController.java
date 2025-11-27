package dev.acm.sistemagestioncomercial.controller;

import dev.acm.sistemagestioncomercial.entity.ProductEntity;
import dev.acm.sistemagestioncomercial.service.ProductService;
import dev.acm.sistemagestioncomercial.service.ProductsStoresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/products/")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ProductsStoresService productsStoresService;


    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<ProductEntity> saveProduct(@RequestBody ProductEntity productEntity){
        return ResponseEntity.ok(productService.saveProduct(productEntity));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @PutMapping
    public ResponseEntity<ProductEntity> updateProduct(@RequestBody ProductEntity productEntity){
        return ResponseEntity.ok(productService.updateProduct(productEntity));
    }

    // Advanced

    @GetMapping("/category/")
    public ResponseEntity<List<ProductEntity>> getAllProductsByCategory(@RequestParam String categoryName){
        return ResponseEntity.ok(productService.getAllProductsByCategory(categoryName));
    }

    @GetMapping("/price/")
    public ResponseEntity<List<ProductEntity>> getAllProductsByPriceBetween(@RequestParam Double minPrice, @RequestParam Double maxPrice){
        return ResponseEntity.ok(productService.getAllProductsByPriceBetween(minPrice, maxPrice));
    }

    @GetMapping("/price_order_desc/")
    public ResponseEntity<List<ProductEntity>> getAllProductsByPriceOrderByPriceDesc(){
        return ResponseEntity.ok(productService.getAllProductsByPriceOrderByPriceDesc());
    }

    @GetMapping("/price_order_asc/")
    public ResponseEntity<List<ProductEntity>> getAllProductsByPriceOrderByPriceAsc(){
        return ResponseEntity.ok(productService.getAllProductsByPriceOrderByPriceAsc());
    }

    @GetMapping("/creation_date/")
    public ResponseEntity<List<ProductEntity>> getAllProductsByCreationDateAfter(@RequestParam LocalDateTime date){
        return ResponseEntity.ok(productService.getAllProductsByCreationDateAfter(date));
    }

    @GetMapping("/store/")
    public ResponseEntity<List<ProductEntity>> getAllProductsByStoreName(@RequestParam String storeName) {
        return ResponseEntity.ok(productsStoresService.getAllProductsByStore(storeName));
    }

    @GetMapping("/best_sellers/")
    public ResponseEntity<List<ProductEntity>> getBestSellerProducts() {
        return ResponseEntity.ok(productService.getBestSellerProducts());
    }
}
