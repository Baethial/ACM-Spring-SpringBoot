package dev.jaqs.forecastAPI.controller;

import dev.jaqs.forecastAPI.model.ForecastResponse;
import dev.jaqs.forecastAPI.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/forecast")
public class ForecastController {

    private final ForecastService forecastService;

    @Autowired
    public ForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping()
    public Mono<ForecastResponse> getForecastByCity(@RequestParam String city) {
        return forecastService.getForecastByCity(city);
    }
}
