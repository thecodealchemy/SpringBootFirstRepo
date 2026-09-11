package com.example.FirstProject.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.FirstProject.Entity.User;
import com.example.FirstProject.repository.UsersRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

class UserServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getUserByUserName() {
        when(usersRepository.getUserByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("AdityaMock").password("123").build());
        assertEquals(4, 2+2);
        assertNotNull(userService.getUserByUserName("Aditya"));
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,2,4",
            "4,4,8"
    })
    void customTest(int a, int b, int c){
        assertEquals(c,a+b);
    }
}