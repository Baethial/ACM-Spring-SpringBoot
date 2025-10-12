package dev.jaqs.beanlab;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExperimentServiceConfig {

    @Bean("nombreBean")
    // @Bean("beanFromConfig")
    // @Lazy
    public ExperimentService experimentService() {
        System.out.println("Created from configuration class");
        return new ExperimentService();
    }
}
