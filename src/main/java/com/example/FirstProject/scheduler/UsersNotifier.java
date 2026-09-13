package com.example.FirstProject.scheduler;

import com.example.FirstProject.Cache.AppCache;
import com.example.FirstProject.Entity.Journal;
import com.example.FirstProject.Entity.User;
import com.example.FirstProject.Enums.SentimentEnum;
import com.example.FirstProject.Service.EmailService;
import com.example.FirstProject.Utils.Utils;
import com.example.FirstProject.repository.UserCustomRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UsersNotifier {

    @Autowired
    private UserCustomRepository userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 */10 * * * *")
    public void sendNotificationforPastOneWeek() {
        List<User> usersList = userService.findUserswithEmailOptIn();
        for (User user : usersList) {
            List<Journal> journalList = user.getJournalList();
            Map<SentimentEnum, Integer> sentimentCounts = new HashMap<>();
            List<SentimentEnum> sentiments = journalList.stream()
                                                        .filter(j -> Utils.isPublishedInXDays(j, 7))
                                                        .map(Journal::getSentiment).toList();
            for (SentimentEnum sentiment : sentiments) {
                sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
            }
            emailService.sendEmail(user.getEmail(), "Notification from JournalApp", sentimentCounts.toString());
            log.info("Notification sent to user {}", user.getEmail());
        }
    }

    @Scheduled(cron = "0 */10 * * * *")
    public void refreshCache() {
        log.info("Refreshing cache...");
        appCache.init();
    }
}
