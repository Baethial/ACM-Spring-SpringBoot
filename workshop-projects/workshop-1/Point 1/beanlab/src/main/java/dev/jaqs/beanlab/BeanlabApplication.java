package dev.jaqs.beanlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeanlabApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeanlabApplication.class, args);
	}

//	@Bean
//	ApplicationRunner runner(@Qualifier("nombreBean") ExperimentService experimentService) {
//		return args -> {
//			System.out.println("Starting application");
//		};
//	}
}
