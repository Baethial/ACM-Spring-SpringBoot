package dev.jaqs.forecastAPI.service;

import dev.jaqs.forecastAPI.model.ForecastResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ForecastService {

    private static final Integer TIMESTAMPS_NUMBER = 24;
    @Value("${openweathermap.api.key}")
    private String API_KEY;
    private final WebClient webClient;

    @Autowired
    public ForecastService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<ForecastResponse> getForecastByCity(String city) {
        return webClient.get().uri(
                uriBuilder ->
                    uriBuilder.path("/data/2.5/forecast")
                            .queryParam("cnt", TIMESTAMPS_NUMBER)
                            .queryParam("q", city)
                            .queryParam("appid",API_KEY)
                            .queryParam("units", "metric")
                            .build()
                )
                .retrieve()
                .bodyToMono(ForecastResponse.class);
    }

}
