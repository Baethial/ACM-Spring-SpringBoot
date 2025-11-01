package dev.jaqs.forecastAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Forecast API microservice.
 * The @SpringBootApplication annotation enables:
 * 1. Component scanning (to find controllers, services, repositories).
 * 2. Auto-configuration (sets up Tomcat, WebClient, etc.).
 * 3. Configuration properties scanning.
 */
@SpringBootApplication
public class ForecastApiApplication {

	/**
	 * The main method, which uses SpringApplication.run() to bootstrap the application.
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ForecastApiApplication.class, args);
	}

}
