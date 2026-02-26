package ru.job4j.bmb.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;
import ru.job4j.bmb.recommendation.RecommendationEngine;
import ru.job4j.bmb.repository.MoodLogRepository;

@Service
public class MoodService implements BeanNameAware {

    private RecommendationEngine recommendationEngine;
    private MoodLogRepository moodLogRepository;

    @PostConstruct
    public void init() {
        System.out.println(getClass().getSimpleName() + " initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println(getClass().getSimpleName() + " destroyed");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println(getClass().getSimpleName() + " bean name: " + name);
    }
}
