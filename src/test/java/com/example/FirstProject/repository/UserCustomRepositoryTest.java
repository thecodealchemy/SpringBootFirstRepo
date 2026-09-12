package com.example.FirstProject.repository;

import com.example.FirstProject.BaseTest;
import com.example.FirstProject.Entity.User;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserCustomRepositoryTest extends BaseTest {

    @Autowired
    private UserCustomRepository userCustomRepository;

    @Test
    public void testUserEmailOptIn(){
        List<User> usersList = userCustomRepository.findUserswithEmailOptIn();
        usersList.forEach(System.out::println);
    }
}