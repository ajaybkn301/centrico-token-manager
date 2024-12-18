//package it.hype.centrico.token.manager.domain.service.callable;
//
//import it.hype.authhandler.model.User;
//import it.hype.centrico.token.manager.domain.exception.CryptoUtilsException;
//import it.hype.centrico.token.manager.domain.exception.TokenPollException;
//import it.hype.centrico.token.manager.domain.model.TokenLock;
//import it.hype.centrico.token.manager.domain.service.impl.SsoTokenManagerService;
//import it.hype.centrico.token.manager.domain.service.impl.TokenRepoWrapperService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//class GetTokenLockingTaskTest {
//
//    public static final String AUTH_TOKEN = "testToken";
//
//    GetTokenLockingTask getTokenLockingTask;
//
//    TokenRepoWrapperService tokenRepoWrapperService;
//    SsoTokenManagerService ssoTokenManagerService;
//    User user;
//
//    @BeforeEach
//    void init() {
//        tokenRepoWrapperService = mock(TokenRepoWrapperService.class);
//        ssoTokenManagerService = mock(SsoTokenManagerService.class);
//        user = mock(User.class);
//        doReturn("tenant").when(user).getTenant();
//        doReturn("corrId").when(user).getCorrelationId();
//
//        getTokenLockingTask = GetTokenLockingTask.builder()
//                .tokenRepoWrapperService(tokenRepoWrapperService)
//                .ssoTokenManagerService(ssoTokenManagerService)
//                .authToken(AUTH_TOKEN)
//                .user(user)
//                .build();
//    }
//
//    @Test
//    void testCallWithTokenPresent_shouldReturnToken() {
//        TokenLock lock = TokenLock.builder()
//                .centricoToken("centricoTest")
//                .build();
//        doReturn(Optional.of(lock)).when(tokenRepoWrapperService).loadAndDecryptLock(user);
//        String res = getTokenLockingTask.call();
//        assertEquals("centricoTest", res);
//    }
//
//    @Test
//    void testCallWithLockMissing_shouldThrowException() {
//        doReturn(Optional.empty()).when(tokenRepoWrapperService).loadAndDecryptLock(user);
//        TokenPollException ex = assertThrows(TokenPollException.class,
//                () -> getTokenLockingTask.call());
//        String message = "Error during poll for token lock for user " + user.getTenant() + "/" + user.getIdSoggetto();
//        assertEquals(message, ex.getMessage());
//    }
//
//    @Test
//    void testCallWithTokenMissing_shouldReturnToken() {
//        TokenLock lock = TokenLock.builder()
//                .build();
//        String ibCode = "ibCode";
//        doReturn(Optional.of(lock)).when(tokenRepoWrapperService).loadAndDecryptLock(user);
//        doReturn(ibCode).when(user).getIbCode();
//        doReturn("centricoToken").when(ssoTokenManagerService).getSsoToken(AUTH_TOKEN, ibCode);
//
//        String res = getTokenLockingTask.call();
//        assertEquals("centricoToken", res);
//
//        TokenLock updatedLock = TokenLock.builder()
//                .centricoToken("centricoToken")
//                .build();
//        verify(tokenRepoWrapperService).encryptAndPersistLock(user, updatedLock);
//    }
//
//    @Test
//    void testCallWithErrorGettingSsoToken_shouldThrowException_1() {
//        TokenLock lock = TokenLock.builder()
//                .build();
//        String ibCode = "ibCode";
//        doReturn(Optional.of(lock)).when(tokenRepoWrapperService).loadAndDecryptLock(user);
//        doReturn(ibCode).when(user).getIbCode();
//        doThrow(new IllegalStateException("Test")).when(ssoTokenManagerService).getSsoToken(AUTH_TOKEN, ibCode);
//
//        IllegalStateException ex = assertThrows(IllegalStateException.class,
//                () -> getTokenLockingTask.call());
//        assertEquals("Test", ex.getMessage());
//
//        verify(tokenRepoWrapperService).encryptAndPersistLock(user, lock);
//    }
//
//    @Test
//    void testCallWithErrorGettingSsoToken_shouldThrowException_2() {
//        TokenLock lock = TokenLock.builder()
//                .build();
//        String ibCode = "ibCode";
//        doReturn(Optional.of(lock)).when(tokenRepoWrapperService).loadAndDecryptLock(user);
//        doReturn(ibCode).when(user).getIbCode();
//        doThrow(new CryptoUtilsException("TestCrypto")).when(ssoTokenManagerService).getSsoToken(AUTH_TOKEN, ibCode);
//        CryptoUtilsException ex2 = assertThrows(CryptoUtilsException.class,
//                () -> getTokenLockingTask.call());
//        assertEquals("TestCrypto", ex2.getMessage());
//
//        verify(tokenRepoWrapperService).encryptAndPersistLock(user, lock);
//    }
//}
