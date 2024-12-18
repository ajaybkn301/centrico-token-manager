//package it.hype.centrico.token.manager.domain.service.impl;
//
//import it.hype.authhandler.model.User;
//import it.hype.centrico.token.manager.domain.exception.CryptoUtilsException;
//import it.hype.centrico.token.manager.domain.model.TokenLock;
//import it.hype.centrico.token.manager.domain.repository.LockRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class TokenRepoWrapperServiceTest {
//
//    @InjectMocks
//    TokenRepoWrapperService tokenRepoWrapperService;
//
//    @Mock
//    LockRepository repository;
//
//    @Mock
//    CryptoService cryptoUtils;
//
//    @BeforeEach
//    void init() {
//        ReflectionTestUtils.setField(tokenRepoWrapperService, "centricoKey", "testKey");
//    }
//
//    @Test
//    void testLoadAndDecryptLockWithEmptyCentricoToken_shouldReturnTheSameLock() {
//        User user = mock(User.class);
//        doReturn("userTenant").when(user).getTenant();
//        doReturn("userCorId").when(user).getCorrelationId();
//
//        String id = "userTenant_userCorId";
//        TokenLock lock = TokenLock.builder().build();
//        doReturn(Optional.of(lock)).when(repository).getLockById(id);
//
//        Optional<TokenLock> res = tokenRepoWrapperService.loadAndDecryptLock(user);
//
//        assertTrue(res.isPresent());
//        assertSame(lock, res.get());
//    }
//
//    @Test
//    void testLoadAndDecryptLockWithCentricoToken_shouldReturnLockWithDecryptedToken() {
//        User user = mock(User.class);
//        doReturn("userTenant").when(user).getTenant();
//        doReturn("userCorId").when(user).getCorrelationId();
//        doReturn("userKey").when(user).getKey();
//
//        String decryptedKey = "decryptedKey";
//        TokenLock lock = TokenLock.builder()
//                .centricoToken("centricoToken")
//                .build();
//        doReturn(Optional.of(lock)).when(repository).getLockById("userTenant_userCorId");
//        doReturn(decryptedKey).when(cryptoUtils).decrypt("userKeytestKey", "centricoToken");
//
//        Optional<TokenLock> res = tokenRepoWrapperService.loadAndDecryptLock(user);
//
//        assertTrue(res.isPresent());
//        assertEquals(decryptedKey, res.get().getCentricoToken());
//    }
//
//    @Test
//    void testLoadAndDecryptLockWithCryptoUtilsException_shouldReturnLockWithNullToken() {
//        User user = mock(User.class);
//        doReturn("userId").when(user).getKey();
//        doReturn("userTenant").when(user).getTenant();
//        doReturn("userCorId").when(user).getCorrelationId();
//
//        String id = "userTenant_" + "userCorId";
//        String encryptionKey = "userId" + "testKey";
//        Optional<TokenLock> lock = Optional.of(TokenLock.builder()
//                .centricoToken("centricoToken")
//                .build());
//        doReturn(lock).when(repository).getLockById(id);
//        doThrow(CryptoUtilsException.class).when(cryptoUtils).decrypt(encryptionKey, "centricoToken");
//
//        Optional<TokenLock> res = tokenRepoWrapperService.loadAndDecryptLock(user);
//
//        assertTrue(res.isPresent());
//        assertNull(res.get().getCentricoToken());
//    }
//
//    @Test
//    void testPncryptAndPersistLockWithCentricoToken_should() {
//        User user = mock(User.class);
//        doReturn("userId").when(user).getKey();
//
//        String encryptionKey = "userIdtestKey";
//        TokenLock lock = TokenLock.builder()
//                .centricoToken("centricoToken")
//                .build();
//        String encryptedToken = "encryptedToken";
//        doReturn(encryptedToken).when(cryptoUtils).encrypt(encryptionKey, "centricoToken");
//
//        tokenRepoWrapperService.encryptAndPersistLock(user, lock);
//
//        assertEquals(encryptedToken, lock.getCentricoToken());
//
//        verify(repository).save(lock);
//        verify(cryptoUtils).encrypt(encryptionKey, "centricoToken");
//    }
//
//    @Test
//    void testEncryptAndPersistLockWithEmptyToken_shouldSaveWithoutEncrypting() {
//        User user = mock(User.class);
//
//        TokenLock lock = TokenLock.builder().build();
//
//        tokenRepoWrapperService.encryptAndPersistLock(user, lock);
//
//        verify(repository).save(lock);
//        verify(cryptoUtils, never()).encrypt(anyString(), anyString());
//    }
//
//    @Test
//    void testEncryptAndPersistLockWithCryptoUtilsException_shouldSkipSaveAndThrowException() {
//        User user = mock(User.class);
//        doReturn("userId").when(user).getKey();
//
//        doThrow(new CryptoUtilsException("Test"))
//                .when(cryptoUtils).encrypt(anyString(), anyString());
//
//        TokenLock lock = TokenLock.builder()
//                .centricoToken("centricoToken")
//                .build();
//        CryptoUtilsException ex = assertThrows(CryptoUtilsException.class,
//                () -> tokenRepoWrapperService.encryptAndPersistLock(user, lock));
//
//        assertEquals("Test", ex.getMessage());
//
//        verify(repository, never()).save(lock);
//    }
//}
