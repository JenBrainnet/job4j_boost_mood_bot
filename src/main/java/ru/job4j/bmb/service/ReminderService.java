package ru.job4j.bmb.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import ru.job4j.bmb.repository.UserRepository;
import ru.job4j.bmb.telegram.TelegramBotService;

@Service
public class ReminderService {
    private TelegramBotService telegramBotService;
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        System.out.println(getClass().getSimpleName() + " initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println(getClass().getSimpleName() + " destroyed");
    }
}
