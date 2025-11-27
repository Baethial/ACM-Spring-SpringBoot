package dev.acm.sistemagestioncomercial.service;

import dev.acm.sistemagestioncomercial.entity.CityEntity;
import dev.acm.sistemagestioncomercial.persistence.CityRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public CityEntity getCityById(Long id){
        return cityRepository.findById(id).orElse(null);
    }

    public List<CityEntity> getAllCities(){
        return cityRepository.findAll();
    }

    public CityEntity saveCity(CityEntity cityEntity){
        return cityRepository.save(cityEntity);
    }

    public void deleteCity(Long id){
        cityRepository.deleteById(id);
    }

    public CityEntity updateCity(CityEntity cityEntity){
        return cityRepository.save(cityEntity);
    }

}
