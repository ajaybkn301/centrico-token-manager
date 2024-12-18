package it.hype.centrico.token.manager.domain.service.impl;

import it.hype.authhandler.model.User;
import it.hype.centrico.token.manager.domain.model.TokenLock;
import it.hype.errorhandler.exception.HypeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class SsoTokenService {

    private final User user;

    private final TokenRepoWrapperService tokenRepoWrapperService;
    private final TokenRetrievingService tokenRetrievingService;
    private final TokenPollingService tokenPollingService;

    public String getSsoToken(String authToken) throws HypeException {
        log.info("Received new request for token retrieval for subjectId {}", user.getIdSoggetto());
        Optional<String> centricoToken = getCachedCentricoToken();
        if (centricoToken.isPresent()) {
            log.info("Token found on cache for subjectId {}", user.getIdSoggetto());
            return centricoToken.get();
        }
        log.info("Token not in cache, starting retrieval from Centrico-SSO for subjectId {}", user.getIdSoggetto());
        String retrievedToken = tokenRetrievingService.retrieveToken(user, authToken);
        if (StringUtils.isNotBlank(retrievedToken)) {
            log.info("Token retrieved from centrico and stored in cache for subjectId {}", user.getIdSoggetto());
            return retrievedToken;
        }
        log.info("Start polling for cache update for subjectId {}", user.getIdSoggetto());
        String ssoToken = tokenPollingService.pollToken(user);
        log.info("Token retrieved after polling for subjectId {}", user.getIdSoggetto());
        return ssoToken;
    }

    private Optional<String> getCachedCentricoToken() {
        return tokenRepoWrapperService.loadAndDecryptLock(user)
                .map(TokenLock::getCentricoToken);
    }
}
