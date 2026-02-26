package ru.job4j.bmb.recommendation;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class RecommendationEngine {
    private ContentProvider contentProvider;

    @PostConstruct
    public void init() {
        System.out.println(getClass().getSimpleName() + " initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println(getClass().getSimpleName() + " destroyed");
    }
}
