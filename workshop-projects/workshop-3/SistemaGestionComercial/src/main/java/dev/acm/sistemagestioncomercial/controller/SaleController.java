package dev.acm.sistemagestioncomercial.controller;

import dev.acm.sistemagestioncomercial.entity.SaleEntity;
import dev.acm.sistemagestioncomercial.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @GetMapping("/{id}")
    public ResponseEntity<SaleEntity> getSaleById(@PathVariable Long id){
        return ResponseEntity.ok(saleService.getSaleById(id));
    }

    @GetMapping
    public ResponseEntity<List<SaleEntity>> getAllSales(){
        return ResponseEntity.ok(saleService.getAllSales());
    }

    @PostMapping
    public ResponseEntity<SaleEntity> saveSale(@RequestBody SaleEntity saleEntity){
        return ResponseEntity.ok(saleService.saveSale(saleEntity));
    }

    @DeleteMapping("/{id}")
    public void deleteSale(@PathVariable Long id){
        saleService.deleteSale(id);
    }

    @PutMapping
    public ResponseEntity<SaleEntity> updateSale(@RequestBody SaleEntity saleEntity){
        return ResponseEntity.ok(saleService.updateSale(saleEntity));
    }

    // Advanced

    @GetMapping("/user/")
    public ResponseEntity<List<SaleEntity>> getSalesFromUserId(@RequestParam Long userId){
        return ResponseEntity.ok(saleService.getAllSalesByUserId(userId));
    }

    @GetMapping("/date/")
    public ResponseEntity<Double> getTotalSalesByDate(@RequestParam LocalDate date){
        return ResponseEntity.ok(saleService.getTotalSalesByDate(date));
    }

    @GetMapping("/greater/")
    public ResponseEntity<List<SaleEntity>> getAllSalesOver(@RequestParam Double amount){
        return ResponseEntity.ok(saleService.getAllSalesOver(amount));
    }


}
