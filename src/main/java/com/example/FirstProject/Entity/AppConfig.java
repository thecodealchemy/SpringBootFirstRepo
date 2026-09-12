package com.example.FirstProject.Entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("config_journal_app")
@Data
public class AppConfig {
    private String key;
    private String value;
}
