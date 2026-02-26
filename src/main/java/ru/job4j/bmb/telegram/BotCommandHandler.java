package ru.job4j.bmb.telegram;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import ru.job4j.bmb.model.Content;
import ru.job4j.bmb.service.MoodService;

@Service
public class BotCommandHandler {
    private MoodService moodService;

    void receive(Content content) {
        System.out.println(content);
    }

    @PostConstruct
    public void init() {
        System.out.println(getClass().getSimpleName() + " initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println(getClass().getSimpleName() + " destroyed");
    }
}
