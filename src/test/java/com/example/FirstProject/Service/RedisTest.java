package com.example.FirstProject.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.data.redis.core.RedisTemplate;

@DataRedisTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @ParameterizedTest
    @CsvSource({
            "Key1, Value1",
            "Key2, Value2",
            "Key3, Value3"
    })
    public void getSetRedisTest(String key, String value) {
//        redisTemplate.opsForValue().set(key, value);
        assertEquals(value, redisTemplate.opsForValue().get(key));
        assertTrue(redisTemplate.hasKey(key));
    }

}
