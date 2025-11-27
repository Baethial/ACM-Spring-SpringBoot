package dev.acm.sistemagestioncomercial.controller;

import dev.acm.sistemagestioncomercial.entity.CityEntity;
import dev.acm.sistemagestioncomercial.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("/{id}")
    public ResponseEntity<CityEntity> getCityById(@PathVariable Long id){
        return ResponseEntity.ok(cityService.getCityById(id));
    }

    @GetMapping
    public ResponseEntity<List<CityEntity>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @PostMapping
    public ResponseEntity<CityEntity> saveCity(@RequestBody CityEntity cityEntity){
        return ResponseEntity.ok(cityService.saveCity(cityEntity));
    }

    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable Long id){
        cityService.deleteCity(id);
    }

    @PutMapping
    public ResponseEntity<CityEntity> updateCity(@RequestBody CityEntity cityEntity){
        return ResponseEntity.ok(cityService.updateCity(cityEntity));
    }
}
