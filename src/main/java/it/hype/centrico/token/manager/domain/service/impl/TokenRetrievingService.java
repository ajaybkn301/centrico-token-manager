package it.hype.centrico.token.manager.domain.service.impl;

import feign.FeignException;
import it.hype.authhandler.model.User;
import it.hype.centrico.token.manager.domain.exception.CentricoTokenException;
import it.hype.centrico.token.manager.domain.exception.GenericHypeException;
import it.hype.centrico.token.manager.domain.service.callable.GetTokenLockingTask;
import it.hype.centrico.token.manager.domain.utils.LockUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.javacrumbs.shedlock.core.LockConfiguration;
import net.javacrumbs.shedlock.core.LockingTaskExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Log4j2
@Service
@RequiredArgsConstructor
public class TokenRetrievingService {

    private final LockingTaskExecutor executor;
    private final SsoTokenManagerService ssoTokenManagerService;
    private final TokenRepoWrapperService tokenRepoWrapperService;

    @Value("${lock.lockAtMost}")
    private Duration lockAtMostFor;

    @Value("${lock.lockAtLeast}")
    private Duration lockAtLeastFor;

    public String retrieveToken(User user, String authToken) {
        String lockId = LockUtils.calculateLockId(user);
        try {
            GetTokenLockingTask task = buildTokenTask(user, authToken);
            LockConfiguration lockConfig = new LockConfiguration(Instant.now(), lockId, lockAtMostFor, lockAtLeastFor);
            return executor.executeWithLock(task, lockConfig).getResult();
        }catch( DuplicateKeyException e) {
            log.warn(
                    "Error during lock acquire. Duplicate key exception for subjectId {} on key {}",
                    user.getIdSoggetto(),  lockId, e
            );
            return null;
        } catch (FeignException | CentricoTokenException ex) {
            throw ex;
        }  catch(Throwable e) {
            throw new GenericHypeException(
                    "Error retrieving token for subjectId " + user.getIdSoggetto(),
                    e
            );
        }
    }

    private GetTokenLockingTask buildTokenTask(User user, String authToken) {
        return GetTokenLockingTask.builder()
                .tokenRepoWrapperService(tokenRepoWrapperService)
                .ssoTokenManagerService(ssoTokenManagerService)
                .authToken(authToken)
                .user(user)
                .build();
    }

}
