package dev.jaqs.forecastAPI.service;

import dev.jaqs.forecastAPI.model.ForecastResponse;
import dev.jaqs.forecastAPI.model.dto.ForecastSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Service component responsible for data fetching, aggregation, and business logic.
 * @Service marks this class as a Spring component for dependency injection.
 */
@Service
public class ForecastService {

    // Constants based on OpenWeatherMap 3-hourly reports:
    private static final Integer REPORTS_NUMBER = 24; // Total number of reports requested (72 hours of data).
    private static final Integer REPORTS_FOR_24H = 8; // Number of reports used for the 24-hour summary (8 * 3 hours = 24h).

    // Injects the API key from application.properties using the @Value annotation.
    @Value("${openweathermap.api.key}")
    private String API_KEY;

    private final WebClient webClient;
    // Formatter to safely parse the "YYYY-MM-DD HH:MM:SS" timestamp string from the API.
    private final DateTimeFormatter apiDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Constructor for Dependency Injection. Spring automatically provides the WebClient bean.
     * @param webClient The configured WebClient instance.
     */
    @Autowired
    public ForecastService(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Private helper method to handle the external API request reactively.
     * @param city The city name for the query.
     * @return A Mono that will emit the raw ForecastResponse when data is received.
     */
    private Mono<ForecastResponse> getRawForecastByCity(String city) {
        return webClient.get().uri(
                        uriBuilder ->
                                uriBuilder.path("/data/2.5/forecast")
                                        .queryParam("cnt", REPORTS_NUMBER)
                                        .queryParam("q", city)
                                        .queryParam("appid",API_KEY)
                                        .queryParam("units", "metric") // Ensures temperature is in Celsius
                                        .build()
                )
                .retrieve()
                .bodyToMono(ForecastResponse.class);
    }

    /**
     * Public method to fetch raw data and transform it into the aggregated summary DTO.
     * This method uses the reactive 'map' operator to chain the transformation logic.
     * @param city The city name.
     * @return A Mono emitting the final ForecastSummaryDTO.
     */
    public Mono<ForecastSummaryDTO> getForecastSummaryByCity(String city) {
        return getRawForecastByCity(city)
                .map(this::createSummaryFromResponse);
    }

    /**
     * Aggregation orchestrator: takes the raw response and delegates aggregation to helper methods.
     * @param response The raw response from the external API.
     * @return The aggregated DTO.
     */
    private ForecastSummaryDTO createSummaryFromResponse(ForecastResponse response) {
        List<ForecastResponse.WeatherResponse> weatherResponseList = response.getWeatherResponseList();

        if (weatherResponseList == null || weatherResponseList.isEmpty()) {
            // Handle case where API returns no data gracefully
            return new ForecastSummaryDTO(null, List.of());
        }

        // 1. Create 24 Hour Summary
        ForecastSummaryDTO.Summary24h summary24h = create24hSummary(weatherResponseList);

        // 2. Create 3 Day Summary
        List<ForecastSummaryDTO.SingleDaySummary> threeDaySummary = createThreeDaySummary(weatherResponseList);

        return new ForecastSummaryDTO(summary24h, threeDaySummary);
    }

    /**
     * Calculates the summary for the next 24 hours using aggregation over the first 8 reports.
     */
    private ForecastSummaryDTO.Summary24h create24hSummary(List<ForecastResponse.WeatherResponse> weatherResponseList) {

        // Use subList for clean demarcation of the 24-hour window
        List<ForecastResponse.WeatherResponse> weatherResponseList24h = weatherResponseList.subList(0, REPORTS_FOR_24H);

        // Calculate Average Temperature using Java Streams:
        Double avgTemp24h = weatherResponseList24h.stream()
                .mapToDouble(w -> w.getMain().getTemp()) // Extracts 'temp' field
                .average()
                .orElse(0.0);

        // Round the result for clean presentation
        Double roundedAvgTemp24h = Math.round(avgTemp24h * 10.0) / 10.0;

        // Determine the predominant weather condition (mode/most frequent) using Streams and Collectors:
        String generalWeather24h = weatherResponseList24h.stream()
                .flatMap(r -> r.getWeather().stream())  // Flatten the List<Weather> inside each report
                .map(w -> w.getMain())                   // Get the general weather string (e.g., "Rain")
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()))                  // Group strings and count occurrences
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())               // Find the string with the highest count
                .map(Map.Entry::getKey)                          // Extract the string value
                .orElse("Clear");

        // The time of the last report in the 24h window
        String lastUpdateTime =weatherResponseList24h.get(REPORTS_FOR_24H-1).getTimeStamp();

        return new ForecastSummaryDTO.Summary24h(roundedAvgTemp24h, generalWeather24h, lastUpdateTime);

    }

    /**
     * Calculates the summary for the next three calendar days.
     */
    private List<ForecastSummaryDTO.SingleDaySummary> createThreeDaySummary(List<ForecastResponse.WeatherResponse> weatherResponseList) {

        // STEP 1: Group weather reports by unique calendar date (YYYY-MM-DD)
        Map<String, List<ForecastResponse.WeatherResponse>> dailyReportsMap = weatherResponseList.stream()
                .collect(Collectors.groupingBy(
                        // Parse timestamp and extract the LocalDate for grouping
                        r -> LocalDateTime.parse(r.getTimeStamp(), apiDateTimeFormatter).toLocalDate().toString()
                ));

        // STEP 2: Process the grouped data
        return dailyReportsMap.entrySet().stream()
                // Sort entries by date key to ensure chronological order
                .sorted(Map.Entry.comparingByKey())

                // IMPORTANT: Limit to the first 3 entries to ensure strict adherence to "3-day summary",
                // preventing spillover into a partial 4th day.
                .limit(3)

                // STEP 3: Transform each day's group into a summary object
                .map(entry -> {
                    String date = entry.getKey();
                    List<ForecastResponse.WeatherResponse> dayReports = entry.getValue();

                    // Calculate daily average temperature
                    Double avgTemp = dayReports.stream()
                            .mapToDouble(r -> r.getMain().getTemp())
                            .average()
                            .orElse(0.0);

                    // Determine dominant weather condition using the same mode calculation logic
                    String dominantWeather = dayReports.stream()
                            .flatMap(r -> r.getWeather().stream())
                            .map(w -> w.getMain())
                            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                            .entrySet().stream()
                            .max(Map.Entry.comparingByValue())
                            .map(Map.Entry::getKey)
                            .orElse("Clear");

                    // Round average temperature
                    double roundedAvgTemp = Math.round(avgTemp * 10.0) / 10.0;

                    // STEP 4: Create the final summary object for this day
                    return new ForecastSummaryDTO.SingleDaySummary(date, roundedAvgTemp, dominantWeather);
                })
                .collect(Collectors.toList());
    }
}