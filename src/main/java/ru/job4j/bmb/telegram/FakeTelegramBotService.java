package ru.job4j.bmb.telegram;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.job4j.bmb.config.OnFakeCondition;
import ru.job4j.bmb.content.Content;
import ru.job4j.bmb.content.SendContent;

@Service
@Conditional(OnFakeCondition.class)
public class FakeTelegramBotService extends TelegramLongPollingBot  implements SendContent {

    @Override
    public void onUpdateReceived(Update update) {
    }

    @Override
    public String getBotUsername() {
        return "fakeBot";
    }

    @Override
    public void send(Content content) {
        System.out.println("Fake bot send: " + content.getText());
    }

}

