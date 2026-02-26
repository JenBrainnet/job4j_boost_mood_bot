package ru.job4j.bmb.service;

import org.springframework.stereotype.Service;
import ru.job4j.bmb.repository.AchievementRepository;
import ru.job4j.bmb.telegram.TelegramBotService;

@Service
public class AchievementService {
    private TelegramBotService telegramBotService;
    private AchievementRepository achievementRepository;
}
