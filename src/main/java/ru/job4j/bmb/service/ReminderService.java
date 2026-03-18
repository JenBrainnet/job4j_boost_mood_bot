package ru.job4j.bmb.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.job4j.bmb.content.Content;
import ru.job4j.bmb.repository.UserRepository;
import ru.job4j.bmb.telegram.TelegramBotService;

@Service
public class ReminderService implements BeanNameAware {

    private final TelegramBotService botService;
    private final UserRepository userRepository;

    public ReminderService(TelegramBotService botService, UserRepository userRepository) {
        this.botService = botService;
        this.userRepository = userRepository;
    }

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

    @Scheduled(fixedRateString = "${remind.period}")
    public void ping() {
        for (var user : userRepository.findAll()) {
            Content content = new Content(user.getChatId());
            content.setText("Ping");
            botService.send(content);
        }
    }

}
