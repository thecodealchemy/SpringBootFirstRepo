package com.example.FirstProject.Service;

import com.example.FirstProject.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class EmailServiceTest extends BaseTest {

    @Autowired
    private EmailService emailService;

    @Test
    void sendEmail() {
        emailService.sendEmail(
                "aditya829925@gmail.com",
                "Hey there!",
                "How are you this is sample mail! @Peace"
        );
    }
}