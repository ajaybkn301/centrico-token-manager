//package it.hype.centrico.token.manager.infrastructure.repository;
//
//import it.hype.centrico.token.manager.domain.model.TokenLock;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.times;
//
//
//@ExtendWith(MockitoExtension.class)
//public class LockRepositoryImplTest {
//
//    @InjectMocks
//    LockRepositoryImpl lockRepository;
//
//    @Mock
//    LockMongoRepository repository;
//
//    @Test
//    void testSave() {
//        TokenLock lock = TokenLock.builder()
//                .centricoToken("centricoToken")
//                .build();
//
//        lockRepository.save(lock);
//
//        Mockito.verify(repository, times(1)).save(lock);
//    }
//
//    @Test
//    void testGetLockById() {
//        String id = "lockId";
//        TokenLock lock = TokenLock.builder()
//                .centricoToken("centricoToken")
//                .build();
//
//        doReturn(Optional.of(lock)).when(repository).findById(id);
//        var res = lockRepository.getLockById(id);
//
//        assertEquals(lock, res.get());
//        Mockito.verify(repository, times(1)).findById(id);
//    }
//}
