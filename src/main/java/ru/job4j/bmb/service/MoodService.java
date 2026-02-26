package ru.job4j.bmb.service;

import org.springframework.stereotype.Service;
import ru.job4j.bmb.recommendation.RecommendationEngine;
import ru.job4j.bmb.repository.MoodLogRepository;

@Service
public class MoodService {
    private RecommendationEngine recommendationEngine;
    private MoodLogRepository moodLogRepository;
}
