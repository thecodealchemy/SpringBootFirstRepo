package com.example.FirstProject.Entity;

import com.example.FirstProject.Enums.SentimentEnum;
import java.time.LocalDateTime;
import jdk.jfr.StackTrace;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("journals")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString @EqualsAndHashCode // @Data --> contains all these!
@Data
public class Journal {
    @Id
    private ObjectId id;
    @NonNull
    private String name;
    private String content;
    private LocalDateTime date;
    private SentimentEnum sentiment;
}
