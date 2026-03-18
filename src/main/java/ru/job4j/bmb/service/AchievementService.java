package ru.job4j.bmb.service;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.bmb.content.Content;
import ru.job4j.bmb.content.SendContent;
import ru.job4j.bmb.model.Achievement;
import ru.job4j.bmb.repository.AchievementRepository;
import ru.job4j.bmb.repository.AwardRepository;
import ru.job4j.bmb.repository.MoodLogRepository;

@Service
public class AchievementService implements ApplicationListener<UserEvent> {

    private final SendContent sendContent;
    private final MoodLogRepository moodLogRepository;
    private final AwardRepository awardRepository;
    private final AchievementRepository achievementRepository;

    public AchievementService(SendContent sendContent,
                              MoodLogRepository moodLogRepository,
                              AwardRepository awardRepository,
                              AchievementRepository achievementRepository) {
        this.sendContent = sendContent;
        this.moodLogRepository = moodLogRepository;
        this.awardRepository = awardRepository;
        this.achievementRepository = achievementRepository;
    }

    @Transactional
    @Override
    public void onApplicationEvent(UserEvent event) {
        var user = event.getUser();

        long goodMoodCount = moodLogRepository
                .findByUserId(user.getId())
                .stream()
                .filter(moodLog -> moodLog.getMood().isGood())
                .count();

        var achievements = achievementRepository.findByUserId(user.getId());

        for (var award : awardRepository.findAll()) {
            boolean hasEnoughMoods = goodMoodCount >= award.getDays();
            boolean exists = achievements
                    .stream()
                    .anyMatch(a -> a.getAward().getId().equals(award.getId()));

            if (hasEnoughMoods && !exists) {
                var achievement = new Achievement();
                achievement.setUser(user);
                achievement.setAward(award);
                achievementRepository.save(achievement);

                var content = new Content(user.getChatId());
                content.setText(String.format(
                        "Вы получили награду за %d дней хорошего настроения! %s",
                        award.getDays(),
                        award.getTitle())
                );
                sendContent.send(content);
            }
        }
    }

}
