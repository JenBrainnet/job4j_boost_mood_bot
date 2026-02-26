package ru.job4j.bmb.telegram;

import org.springframework.stereotype.Service;
import ru.job4j.bmb.model.Content;
import ru.job4j.bmb.service.MoodService;

@Service
public class BotCommandHandler {
    private MoodService moodService;

    void receive(Content content) {
        System.out.println(content);
    }
}
