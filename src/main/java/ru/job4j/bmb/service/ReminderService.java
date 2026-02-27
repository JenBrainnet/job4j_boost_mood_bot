package ru.job4j.bmb.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.job4j.bmb.repository.UserRepository;

@Service
public class ReminderService implements BeanNameAware {

    private final TgRemoteService tgRemoteService;
    private final UserRepository userRepository;

    public ReminderService(TgRemoteService tgRemoteService, UserRepository userRepository) {
        this.tgRemoteService = tgRemoteService;
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
            var message = new SendMessage();
            message.setChatId(user.getChatId());
            message.setText("Ping");
            tgRemoteService.send(message);
        }
    }

}
