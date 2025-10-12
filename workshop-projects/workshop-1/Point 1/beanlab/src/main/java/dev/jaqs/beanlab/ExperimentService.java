package dev.jaqs.beanlab;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component("nombreBean")
// @Component("beanFromClass")
@Lazy
public class ExperimentService {

    public ExperimentService() {
        System.out.println("ExperimentService Bean created");
    }
}
