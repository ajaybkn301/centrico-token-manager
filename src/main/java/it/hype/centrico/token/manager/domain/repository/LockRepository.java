package it.hype.centrico.token.manager.domain.repository;

import it.hype.centrico.token.manager.domain.model.TokenLock;

import java.util.Optional;


public interface LockRepository {
    void save(TokenLock lock);

    Optional<TokenLock> getLockById(String id);
}
