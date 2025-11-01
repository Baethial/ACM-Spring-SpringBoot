package dev.jaqs.forecastAPI.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.io.Serializable;
import java.util.List;

/**
 * Model class representing the top-level structure of the OpenWeatherMap 5-day/3-hour forecast response.
 * This class is used internally by the application to deserialize the raw API response body.
 * Implements Serializable as a standard practice for DTOs/models.
 */
public class ForecastResponse implements Serializable {

    // The JSON response contains a list of reports under the key "list".
    // @JsonAlias maps the external "list" key to the internal "weatherResponseList" field.
    @JsonAlias("list")
    private List<WeatherResponse> weatherResponseList;

    // --- Standard POJO Boilerplate (Constructors, Getters, Setters) ---

    public ForecastResponse() {
    }

    public ForecastResponse(List<WeatherResponse> weatherResponseList) {
        this.weatherResponseList = weatherResponseList;
    }

    public List<WeatherResponse> getWeatherResponseList() {
        return weatherResponseList;
    }

    public void setWeatherResponseList(List<WeatherResponse> weatherResponseList) {
        this.weatherResponseList = weatherResponseList;
    }

    /**
     * Nested static class representing a single 3-hour forecast report entry in the API's "list" array.
     * Making it static allows it to be instantiated independently of the outer class.
     */
    public static class WeatherResponse {

        private Main main; // Holds temperature and humidity details (nested below)
        private List<Weather> weather; // Holds descriptive weather condition (nested below)
        @JsonAlias("dt_txt")
        private String timeStamp; // Forecast time in "YYYY-MM-DD HH:MM:SS" format

        // --- Standard POJO Boilerplate (Constructors, Getters, Setters) ---

        public WeatherResponse() {
        }

        public WeatherResponse(Main main, List<Weather> weather, String timeStamp) {
            this.main = main;
            this.weather = weather;
            this.timeStamp = timeStamp;
        }

        public Main getMain() {
            return main;
        }

        public void setMain(Main main) {
            this.main = main;
        }

        public List<Weather> getWeather() {
            return weather;
        }

        public void setWeather(List<Weather> weather) {
            this.weather = weather;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

        /**
         * Nested static class for the 'main' block, containing key numerical data.
         * The @JsonAlias is necessary for properties that use underscores in the JSON (`feels_like`).
         */
        public static class Main {

            private Double temp;
            @JsonAlias("feels_like")
            private Double feelsLike;
            @JsonAlias("temp_min")
            private Double tempMin;
            @JsonAlias("temp_max")
            private Double tempMax;
            private Double humidity;

            // --- Standard POJO Boilerplate (Constructors, Getters, Setters) ---

            public Main() {
            }

            public Main(Double temp, Double feelsLike, Double tempMin, Double tempMax, Double humidity) {
                this.temp = temp;
                this.feelsLike = feelsLike;
                this.tempMin = tempMin;
                this.tempMax = tempMax;
                this.humidity = humidity;
            }

            public Double getTemp() {
                return temp;
            }

            public void setTemp(Double temp) {
                this.temp = temp;
            }

            public Double getFeelsLike() {
                return feelsLike;
            }

            public void setFeelsLike(Double feelsLike) {
                this.feelsLike = feelsLike;
            }

            public Double getTempMin() {
                return tempMin;
            }

            public void setTempMin(Double tempMin) {
                this.tempMin = tempMin;
            }

            public Double getTempMax() {
                return tempMax;
            }

            public void setTempMax(Double tempMax) {
                this.tempMax = tempMax;
            }

            public Double getHumidity() {
                return humidity;
            }

            public void setHumidity(Double humidity) {
                this.humidity = humidity;
            }
        }

        /**
         * Nested static class for the 'weather' list element, containing descriptive condition.
         */
        public static class Weather {

            private String main; // General condition (e.g., Rain, Clouds, Clear)
            private String description; // Detailed description (e.g., light rain)

            // --- Standard POJO Boilerplate (Constructors, Getters, Setters) ---

            public Weather() {
            }

            public Weather(String main, String description) {
                this.main = main;
                this.description = description;
            }

            public String getMain() {
                return main;
            }

            public void setMain(String main) {
                this.main = main;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

    }
}
