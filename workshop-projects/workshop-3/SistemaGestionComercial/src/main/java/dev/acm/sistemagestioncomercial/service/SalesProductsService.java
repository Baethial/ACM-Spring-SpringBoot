package dev.acm.sistemagestioncomercial.service;

import dev.acm.sistemagestioncomercial.entity.SalesProductsEntity;
import dev.acm.sistemagestioncomercial.persistence.SalesProductsRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class SalesProductsService {

    private final SalesProductsRepository salesProductsRepository;

    public SalesProductsEntity getSalesProductsById(Long id){
        return salesProductsRepository.findById(id).orElse(null);
    }

    public List<SalesProductsEntity> getAllSalesProducts(){
        return salesProductsRepository.findAll();
    }

    public SalesProductsEntity saveSalesProducts(SalesProductsEntity salesProductsEntity){
        return salesProductsRepository.save(salesProductsEntity);
    }

    public void deleteSalesProducts(Long id){
        salesProductsRepository.deleteById(id);
    }

    public SalesProductsEntity updateSalesProducts(SalesProductsEntity salesProductsEntity){
        return salesProductsRepository.save(salesProductsEntity);
    }
}
