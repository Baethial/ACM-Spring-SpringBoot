package dev.acm.sistemagestioncomercial.controller;

import dev.acm.sistemagestioncomercial.entity.StoreEntity;
import dev.acm.sistemagestioncomercial.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/{id}")
    public ResponseEntity<StoreEntity> getStoreById(@PathVariable Long id){
        return ResponseEntity.ok(storeService.getStoreById(id));
    }

    @GetMapping
    public ResponseEntity<List<StoreEntity>> getAllStores(){
        return ResponseEntity.ok(storeService.getAllStores());
    }

    @PostMapping
    public ResponseEntity<StoreEntity> saveStore(@RequestBody StoreEntity storeEntity){
        return ResponseEntity.ok(storeService.saveStore(storeEntity));
    }

    @DeleteMapping("/{id}")
    public void deleteStore(@PathVariable Long id){
        storeService.deleteStore(id);
    }

    @PutMapping
    public ResponseEntity<StoreEntity> updateStore(@RequestBody StoreEntity storeEntity){
        return ResponseEntity.ok(storeService.updateStore(storeEntity));
    }

    // Advanced

    @GetMapping("/city/")
    public ResponseEntity<List<StoreEntity>> getAllStoresByCity(@RequestParam String city){
        return ResponseEntity.ok(storeService.getAllStoresByCity(city));
    }
}
