package it.hype.centrico.token.manager.domain.service.impl;

import it.hype.authhandler.model.User;
import it.hype.centrico.token.manager.domain.exception.GenericHypeException;
import it.hype.centrico.token.manager.domain.exception.TokenPollException;
import it.hype.centrico.token.manager.domain.model.TokenLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class TokenPollingService {

    private final TokenRepoWrapperService tokenRepoWrapperService;
    private final SleeperService sleeperService;

    @Value("${lock.maxRetries}")
    private int maxRetries;

    @Value("${lock.pollBackoff}")
    private Duration pollDuration;

    public String pollToken(User user) {
        try {
            for (int retries = 0; retries < maxRetries; retries++) {
                sleeperService.sleep(pollDuration.toMillis());
                Optional<String> centricoToken = getCachedCentricoToken(user);
                if (centricoToken.isPresent()) {
                    return centricoToken.get();
                }
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new GenericHypeException(
                    "Thread poll waiting interrupted for subjectId " + user.getIdSoggetto(),
                    ex
            );
        }
        throw new TokenPollException(
                "Token not found, ending polling with failure after " + maxRetries + " retries for subjectId " + user.getIdSoggetto()
        );
    }

    private Optional<String> getCachedCentricoToken(User user) {
        return tokenRepoWrapperService.loadAndDecryptLock(user)
                .map(TokenLock::getCentricoToken);
    }

}
