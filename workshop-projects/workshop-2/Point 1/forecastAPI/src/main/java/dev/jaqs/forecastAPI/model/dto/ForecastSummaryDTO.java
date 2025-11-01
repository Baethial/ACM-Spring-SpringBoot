package dev.jaqs.forecastAPI.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Data Transfer Object (DTO) used to present the aggregated forecast summary to API consumers.
 * This class summarizes the raw 3-hourly data into 24-hour and 3-day views.
 */
public class ForecastSummaryDTO implements Serializable {

    private Summary24h next24hSummary;
    private List<SingleDaySummary> threeDaySummary;

    // Standard constructors and getters/setters...

    public ForecastSummaryDTO() {
    }

    public ForecastSummaryDTO(Summary24h next24hSummary, List<SingleDaySummary> threeDaySummary) {
        this.next24hSummary = next24hSummary;
        this.threeDaySummary = threeDaySummary;
    }

    public Summary24h getNext24hSummary() {
        return next24hSummary;
    }

    public void setNext24hSummary(Summary24h next24hSummary) {
        this.next24hSummary = next24hSummary;
    }

    public List<SingleDaySummary> getThreeDaySummary() {
        return threeDaySummary;
    }

    public void setThreeDaySummary(List<SingleDaySummary> threeDaySummary) {
        this.threeDaySummary = threeDaySummary;
    }

    /**
     * Nested DTO class summarizing the weather for the next 24 hours (8 reports).
     */
    public static class Summary24h implements Serializable {

        private Double averageTemperature;
        private String generalWeatherDescription; // The most frequent weather type (e.g., Rain, Clouds)
        private String lastUpdateTime; // Timestamp of the last report used (24h mark)

        // Standard constructors and getters/setters...

        public Summary24h() {
        }

        public Summary24h(Double averageTemperature, String generalWeatherDescription, String lastUpdateTime) {
            this.averageTemperature = averageTemperature;
            this.generalWeatherDescription = generalWeatherDescription;
            this.lastUpdateTime = lastUpdateTime;
        }

        public Double getAverageTemperature() {
            return averageTemperature;
        }

        public void setAverageTemperature(Double averageTemperature) {
            this.averageTemperature = averageTemperature;
        }

        public String getGeneralWeatherDescription() {
            return generalWeatherDescription;
        }

        public void setGeneralWeatherDescription(String generalWeatherDescription) {
            this.generalWeatherDescription = generalWeatherDescription;
        }

        public String getLastUpdateTime() {
            return lastUpdateTime;
        }

        public void setLastUpdateTime(String lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }
    }

    /**
     * Nested DTO class summarizing the weather for a single calendar day.
     */
    public static class SingleDaySummary implements Serializable {

        private String date; // Date string (YYYY-MM-DD)
        private Double averageTemperature;
        private String dominantWeather; // The most frequent weather type for the entire day

        // Standard constructors and getters/setters...

        public SingleDaySummary() {
        }

        public SingleDaySummary(String date, Double averageTemperature, String dominantWeather) {
            this.date = date;
            this.averageTemperature = averageTemperature;
            this.dominantWeather = dominantWeather;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Double getAverageTemperature() {
            return averageTemperature;
        }

        public void setAverageTemperature(Double averageTemperature) {
            this.averageTemperature = averageTemperature;
        }

        public String getDominantWeather() {
            return dominantWeather;
        }

        public void setDominantWeather(String dominantWeather) {
            this.dominantWeather = dominantWeather;
        }
    }
}