package dev.acm.sistemagestioncomercial.service;

import dev.acm.sistemagestioncomercial.entity.SaleEntity;
import dev.acm.sistemagestioncomercial.persistence.SaleRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;

    public SaleEntity getSaleById(Long id){
        return saleRepository.findById(id).orElse(null);
    }

    public List<SaleEntity> getAllSales(){
        return saleRepository.findAll();
    }

    public SaleEntity saveSale(SaleEntity saleEntity){
        return saleRepository.save(saleEntity);
    }

    public void deleteSale(Long id){
        saleRepository.deleteById(id);
    }

    public SaleEntity updateSale(SaleEntity saleEntity){
        return saleRepository.save(saleEntity);
    }

    // Advanced

    public List<SaleEntity> getAllSalesByUserId(Long id){
        return saleRepository.findAllByUserEntity_id(id);
    }

    public Double getTotalSalesByDate(LocalDate date){
        return saleRepository.getTotalSalesByDate(date);
    }

    public List<SaleEntity> getAllSalesOver(Double amount){
        return saleRepository.findAllByTotalGreaterThan(amount);
    }
}
