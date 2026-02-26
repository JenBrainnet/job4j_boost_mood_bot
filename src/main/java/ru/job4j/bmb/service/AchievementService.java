package ru.job4j.bmb.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;
import ru.job4j.bmb.repository.AchievementRepository;
import ru.job4j.bmb.telegram.TelegramBotService;

@Service
public class AchievementService implements BeanNameAware {

    private TelegramBotService telegramBotService;
    private AchievementRepository achievementRepository;

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
