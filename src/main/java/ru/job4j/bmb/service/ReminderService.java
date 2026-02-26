package ru.job4j.bmb.service;

import org.springframework.stereotype.Service;
import ru.job4j.bmb.repository.UserRepository;
import ru.job4j.bmb.telegram.TelegramBotService;

@Service
public class ReminderService {
    private TelegramBotService telegramBotService;
    private UserRepository userRepository;
}
