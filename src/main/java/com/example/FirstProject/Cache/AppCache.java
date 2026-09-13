package com.example.FirstProject.Cache;

import com.example.FirstProject.Entity.AppConfig;
import com.example.FirstProject.repository.AppCacheRepository;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppCache {

    public Map<String, String> APP_CACHE;

    @Autowired
    private AppCacheRepository appCacheRepository;

    @PostConstruct
    public void init() {
        APP_CACHE = new HashMap<>();
        List<AppConfig> configs = appCacheRepository.findAll();
        for(AppConfig config : configs) {
            APP_CACHE.put(config.getKey(), config.getValue());
            log.info("App Cache Key: {} and Value: {}", config.getKey(), config.getValue());
        }
    }

}
