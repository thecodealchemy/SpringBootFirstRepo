package com.example.FirstProject.Utils;

import com.example.FirstProject.Entity.Journal;
import java.time.LocalDateTime;

public class Utils { // TODO: Add more constants/functions

    public static boolean isPublishedInXDays(Journal journal, Integer days) {
        return journal.getDate().isAfter(LocalDateTime.now().minusDays(days));
    }
}
