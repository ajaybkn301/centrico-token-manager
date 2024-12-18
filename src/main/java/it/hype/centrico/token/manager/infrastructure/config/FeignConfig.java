package it.hype.centrico.token.manager.infrastructure.config;

import feign.Retryer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class FeignConfig {

    @Value("${feign.max-retries}")
    int maxRetries;

    @Value("${feign.max-backoff}")
    Duration maxBackoff;

    @Value("${feign.min-backoff}")
    Duration minBackoff;

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(minBackoff.toMillis(), maxBackoff.toMillis(), maxRetries);
    }
}
