package com.stepaniak.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "news")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DataEntity {
    @Id
    private String id;

    private String content;

    private String date;

    public DataEntity(String content) {
        this.content = content;
        this.date = LocalDateTime.now().toString();
    }
}
