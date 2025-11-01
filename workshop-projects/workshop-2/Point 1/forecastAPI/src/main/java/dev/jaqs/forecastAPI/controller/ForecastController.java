package dev.jaqs.forecastAPI.controller;

import dev.jaqs.forecastAPI.model.dto.ForecastSummaryDTO;
import dev.jaqs.forecastAPI.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * REST Controller for the forecast API.
 * @RestController combines @Controller and @ResponseBody, meaning returned objects are automatically serialized to JSON.
 * @RequestMapping defines the base path for all endpoints in this controller.
 */
@RestController
@RequestMapping("/api/forecast")
public class ForecastController {

    private final ForecastService forecastService;

    /**
     * Constructor for Dependency Injection. Spring handles the creation and wiring of the service.
     * @param forecastService The business logic service.
     */
    @Autowired
    public ForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    /**
     * Primary endpoint to retrieve the aggregated forecast summary.
     * Mapped to GET /api/forecast?city={cityName}
     *
     * @param city The city name provided as a mandatory query parameter.
     * @return A Mono (reactive stream) emitting the aggregated ForecastSummaryDTO.
     */
    @GetMapping()
    public Mono<ForecastSummaryDTO> getForecastByCity(@RequestParam String city) {
        // The controller's primary role is delegation. It calls the service method,
        // which handles fetching and aggregating, and returns the reactive result directly.
        return forecastService.getForecastSummaryByCity(city);
    }
}
