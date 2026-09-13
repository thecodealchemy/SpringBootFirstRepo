package com.example.FirstProject.scheduler;

import com.example.FirstProject.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class UsersNotifierTest extends BaseTest {

    @Autowired
    private UsersNotifier usersNotifier;

    @Test
    void sendNotificationforPastOneWeek() {
        usersNotifier.sendNotificationforPastOneWeek();
    }

}