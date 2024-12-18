package it.hype.centrico.token.manager.infrastructure.repository;

import it.hype.centrico.token.manager.domain.model.TokenLock;
import it.hype.centrico.token.manager.domain.repository.LockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class LockRepositoryImpl implements LockRepository {

    private final LockMongoRepository repository;

    @Override
    public void save(TokenLock lock) {
        repository.save(lock);
    }

    @Override
    public Optional<TokenLock> getLockById(String id) {
        return repository.findById(id);
    }

}
