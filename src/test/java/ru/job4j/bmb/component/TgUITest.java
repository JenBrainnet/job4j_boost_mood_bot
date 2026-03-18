package ru.job4j.bmb.component;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import ru.job4j.bmb.model.Mood;
import ru.job4j.bmb.repository.MoodFakeRepository;
import ru.job4j.bmb.repository.MoodRepository;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {TgUI.class, MoodFakeRepository.class})
class TgUITest {

    @Autowired
    @Qualifier("moodFakeRepository")
    private MoodRepository moodRepository;

    @Autowired
    private TgUI tgUI;

    @Test
    public void whenBtnGood() {
        assertThat(moodRepository).isNotNull();
    }

    @Test
    public void whenBuildButtonsThenReturnMarkup() {
        moodRepository.save(new Mood("Good", true));
        var markup = tgUI.buildButtons();
        assertThat(markup).isNotNull();
        assertThat(markup.getKeyboard()).isNotEmpty();
        assertThat(markup.getKeyboard().get(0).get(0).getText()).isEqualTo("Good");
    }

}