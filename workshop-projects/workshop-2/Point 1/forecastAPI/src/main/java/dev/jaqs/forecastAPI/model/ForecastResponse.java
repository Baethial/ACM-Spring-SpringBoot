package dev.jaqs.forecastAPI.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.io.Serializable;
import java.util.List;

public class ForecastResponse implements Serializable {

    /*
     "list": [
         {
         "main": {
             "temp": 288.46,
             "feels_like": 288.19,
             "temp_min": 287.75,
             "temp_max": 288.46,
             "sea_level": 1001,
             "humidity": 82,
         },
         "weather": [
             {
             "main": "Rain",
             "description": "light rain",
             }
         ],
         "dt_txt": "2025-10-31 18:00:00"
         },
        ...
     ]
     */

    @JsonAlias("list")
    private List<WeatherResponse> weatherResponseList;

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

    public static class WeatherResponse {

        private Main main;
        private List<Weather> weather;
        @JsonAlias("dt_txt")
        private String timeStamp;

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

        public static class Main {

            private Double temp;
            @JsonAlias("feels_like")
            private Double feelsLike;
            @JsonAlias("temp_min")
            private Double tempMin;
            @JsonAlias("temp_max")
            private Double tempMax;
            private Double humidity;

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

        public static class Weather {

            private String main;
            private String description;

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
