package ru.job4j.bmb.telegram;

import org.springframework.stereotype.Service;
import ru.job4j.bmb.model.Content;

@Service
public class TelegramBotService {
    private BotCommandHandler handler;

    public TelegramBotService(BotCommandHandler handler) {
        this.handler = handler;
    }

    public void receive(Content content) {
        handler.receive(content);
    }
}
