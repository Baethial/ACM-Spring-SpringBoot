package dev.jaqs.forecastAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration class to define beans required by the application.
 * @Configuration marks this class as a source of bean definitions.
 */
@Configuration
public class WebClientConfig {

    /**
     * Creates and configures a WebClient bean for connecting to the OpenWeatherMap API.
     * WebClient is the recommended non-blocking HTTP client for Spring WebFlux applications.
     *
     * @return A configured WebClient instance.
     */
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                // Set the base URL here to avoid repeating it in every service call.
                .baseUrl("https://api.openweathermap.org")
                .build();
    }
}
