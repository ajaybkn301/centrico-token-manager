package it.hype.centrico.token.manager.domain.service.impl;

import it.hype.authhandler.model.User;
import it.hype.centrico.token.manager.domain.exception.CryptoUtilsException;
import it.hype.centrico.token.manager.domain.model.TokenLock;
import it.hype.centrico.token.manager.domain.repository.LockRepository;
import it.hype.centrico.token.manager.domain.utils.LockUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class TokenRepoWrapperService {

    private final LockRepository repository;
    private final CryptoService cryptoUtils;

    @Value("${centrico.sso.secret.value}")
    private String centricoKey;

    public Optional<TokenLock> loadAndDecryptLock(User user) {
        String lockId = LockUtils.calculateLockId(user);
        return repository.getLockById(lockId)
                .map(l -> decryptLock(user, l));
    }

    private TokenLock decryptLock(User user, TokenLock tokenLock) {
        String encryptedCentricoToken = tokenLock.getCentricoToken();
        if (StringUtils.isBlank(encryptedCentricoToken)) {
            return tokenLock;
        }
        String encryptionKey = buildEncryptionKey(user);
        String decryptedToken = decrypt(encryptionKey, encryptedCentricoToken);
        tokenLock.setCentricoToken(decryptedToken);
        return tokenLock;
    }

    private String decrypt(String encryptionKey, String encryptedCentricoToken) {
        try {
            return cryptoUtils.decrypt(encryptionKey, encryptedCentricoToken);
        } catch (CryptoUtilsException e) {
            log.warn("An error has been produced during decryption of userData. Returning null", e);
            return null;
        }
    }

    public void encryptAndPersistLock(User user, TokenLock tokenLock) {
        String centricoToken = tokenLock.getCentricoToken();
        if (StringUtils.isNotBlank(centricoToken)) {
            String encryptionKey = buildEncryptionKey(user);
            String encryptedToken = cryptoUtils.encrypt(encryptionKey, centricoToken);
            tokenLock.setCentricoToken(encryptedToken);
        }
        repository.save(tokenLock);
    }

    private String buildEncryptionKey(User user) {
        return user.getKey() + centricoKey;
    }

}
