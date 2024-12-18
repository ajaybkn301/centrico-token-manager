package it.hype.centrico.token.manager.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "#{@environment.getProperty('spring.data.mongodb.collection')}")
public class TokenLock {

    @Id
    private String id;

    private String lockedBy;
    private Instant lockedAt;
    private Instant lockUntil;

    private String centricoToken;

    @LastModifiedDate
    private LocalDateTime createdAt;

}
