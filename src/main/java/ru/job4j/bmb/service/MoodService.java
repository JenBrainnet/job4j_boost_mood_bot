package ru.job4j.bmb.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.job4j.bmb.content.Content;
import ru.job4j.bmb.model.Award;
import ru.job4j.bmb.model.MoodLog;
import ru.job4j.bmb.model.User;
import ru.job4j.bmb.repository.AchievementRepository;
import ru.job4j.bmb.repository.MoodLogRepository;
import ru.job4j.bmb.repository.UserRepository;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class MoodService {

    private final RecommendationEngine recommendationEngine;
    private final MoodLogRepository moodLogRepository;
    private final UserRepository userRepository;
    private final AchievementRepository achievementRepository;
    private final ApplicationEventPublisher publisher;
    private final DateTimeFormatter  formatter = DateTimeFormatter
            .ofPattern("dd-MM-yyyy HH:mm")
            .withZone(ZoneId.systemDefault());

    public MoodService(RecommendationEngine recommendationEngine,
                       MoodLogRepository moodLogRepository,
                       UserRepository userRepository,
                       AchievementRepository achievementRepository,
                       ApplicationEventPublisher publisher) {
        this.recommendationEngine = recommendationEngine;
        this.moodLogRepository = moodLogRepository;
        this.userRepository = userRepository;
        this.achievementRepository = achievementRepository;
        this.publisher = publisher;
    }

    public Content chooseMood(User user, Long moodId) {
        var content = recommendationEngine.recommendFor(user.getChatId(), moodId);
        publisher.publishEvent(new UserEvent(this, user));
        return content;
    }

    public Optional<Content> weekMoodLogCommand(long chatId, Long clientId) {
        var content = new Content(chatId);
        long weekAgo = Instant.now().minus(7, ChronoUnit.DAYS).getEpochSecond();
        var logs = moodLogRepository.findByUserIdAndCreatedAtGreaterThanEqual(clientId, weekAgo);
        content.setText(formatMoodLogs(logs, "Mood log for week:"));
        return Optional.of(content);
    }

    public Optional<Content> monthMoodLogCommand(long chatId, Long clientId) {
        var content = new Content(chatId);
        long monthAgo = Instant.now().minus(30, ChronoUnit.DAYS).getEpochSecond();
        var logs = moodLogRepository.findByUserIdAndCreatedAtGreaterThanEqual(clientId, monthAgo);
        content.setText(formatMoodLogs(logs, "Mood log for month:"));
        return Optional.of(content);
    }

    private String formatMoodLogs(List<MoodLog> logs, String title) {
        if (logs.isEmpty()) {
            return title + ":\nNo mood logs found.";
        }
        var sb = new StringBuilder(title + ":\n");
        logs.forEach(log -> {
            String formattedDate = formatter.format(Instant.ofEpochSecond(log.getCreatedAt()));
            sb.append(formattedDate).append(": ").append(log.getMood().getText()).append("\n");
        });
        return sb.toString();
    }

    public Optional<Content> awards(long chatId, Long clientId) {
        var content = new Content(chatId);
        var achievements = achievementRepository.findByUserId(clientId);
        if (achievements.isEmpty()) {
            content.setText("No awards received yet.");
            return Optional.of(content);
        }
        var sb = new StringBuilder();
        achievements.forEach(achievement -> {
            Award award = achievement.getAward();
            sb.append(award.getTitle())
                    .append(": ")
                    .append(award.getDescription())
                    .append("\n");
        });
        content.setText(sb.toString());
        return Optional.of(content);
    }

}
