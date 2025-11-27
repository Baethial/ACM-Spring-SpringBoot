package dev.acm.sistemagestioncomercial.service;

import dev.acm.sistemagestioncomercial.entity.StoreEntity;
import dev.acm.sistemagestioncomercial.persistence.StoreRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreEntity getStoreById(Long id){
        return storeRepository.findById(id).orElse(null);
    }

    public List<StoreEntity> getAllStores(){
        return storeRepository.findAll();
    }

    public StoreEntity saveStore(StoreEntity storeEntity){
        return storeRepository.save(storeEntity);
    }

    public void deleteStore(Long id){
        storeRepository.deleteById(id);
    }

    public StoreEntity updateStore(StoreEntity storeEntity){
        return storeRepository.save(storeEntity);
    }

    public List<StoreEntity> getAllStoresByCity(String cityName){
        return storeRepository.findAllByCity_Name(cityName);
    }
}
