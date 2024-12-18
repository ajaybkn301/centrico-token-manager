package it.hype.centrico.token.manager.infrastructure.repository;

import it.hype.centrico.token.manager.domain.model.TokenLock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LockMongoRepository extends MongoRepository<TokenLock, String> {
    Optional<TokenLock> findById(String id);
}
