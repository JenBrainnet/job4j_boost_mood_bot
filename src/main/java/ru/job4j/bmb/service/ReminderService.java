package ru.job4j.bmb.service;

import ru.job4j.bmb.repository.UserRepository;
import ru.job4j.bmb.telegram.TelegramBotService;

public class ReminderService {
    private TelegramBotService telegramBotService;
    private UserRepository userRepository;
}
