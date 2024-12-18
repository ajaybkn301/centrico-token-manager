package it.hype.centrico.token.manager.domain.service.callable;

import it.hype.authhandler.model.User;
import it.hype.centrico.token.manager.domain.exception.TokenPollException;
import it.hype.centrico.token.manager.domain.model.TokenLock;
import it.hype.centrico.token.manager.domain.service.impl.SsoTokenManagerService;
import it.hype.centrico.token.manager.domain.service.impl.TokenRepoWrapperService;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import net.javacrumbs.shedlock.core.LockingTaskExecutor;

import java.util.Optional;

@Log4j2
@Builder
@EqualsAndHashCode
public class GetTokenLockingTask implements LockingTaskExecutor.TaskWithResult<String> {

    private User user;
    private String authToken;

    private TokenRepoWrapperService tokenRepoWrapperService;
    private SsoTokenManagerService ssoTokenManagerService;

    @Override
    public String call() {
        Optional<TokenLock> tokenLock = tokenRepoWrapperService.loadAndDecryptLock(user);
        Optional<String> centricoToken = tokenLock.map(TokenLock::getCentricoToken);
        if (centricoToken.isPresent()) {
            return centricoToken.get();
        }

        if (tokenLock.isEmpty()) {
            throw new TokenPollException(
                    "Error during poll for token lock for user " + user.getTenant() + "/" + user.getIdSoggetto()
            );
        }
        return retrieveAndPersistToken(tokenLock.get());
    }

    private String retrieveAndPersistToken(TokenLock lockData) {
        try {
            String centricoToken = ssoTokenManagerService.getSsoToken(authToken, user.getIbCode());
            lockData.setCentricoToken(centricoToken);
            tokenRepoWrapperService.encryptAndPersistLock(user, lockData);
            return centricoToken;
        } catch (Exception ex) {
            //  prevents data without createdAt field
            tokenRepoWrapperService.encryptAndPersistLock(user, lockData);
            throw ex;
        }
    }
}
