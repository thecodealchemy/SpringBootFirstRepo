package com.example.FirstProject.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public <T> T get(String key, Class<T> entityClass) {
        try {
            String jsonValue = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonValue, entityClass);
        } catch (Exception e) {
            log.error("Failed to get key: {} with error: {}", key, e.getMessage());
            return null;
        }
    }

    public void put(String key, Object value, Integer ttl) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonValue = mapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key, jsonValue, ttl, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Failed to put key: {} with error: {}", key, e.getMessage());
        }
    }

}
