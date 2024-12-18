//package it.hype.centrico.token.manager.domain.service.impl;
//
//import it.hype.authhandler.model.User;
//import it.hype.centrico.token.manager.domain.exception.GenericHypeException;
//import it.hype.centrico.token.manager.domain.exception.TokenPollException;
//import it.hype.centrico.token.manager.domain.model.TokenLock;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import java.time.Duration;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class TokenPollingServiceTest {
//
//    @InjectMocks
//    TokenPollingService tokenPollingService;
//
//    @Mock
//    TokenRepoWrapperService tokenRepoWrapperService;
//
//    @Mock
//    SleeperService sleeperService;
//
//    @BeforeEach
//    void init() {
//        ReflectionTestUtils.setField(tokenPollingService, "maxRetries", 5);
//        ReflectionTestUtils.setField(tokenPollingService, "pollDuration", Duration.ofMillis(10));
//    }
//
//    @Test
//    void testPollToken() {
//        User user = mock(User.class);
//
//        TokenLock lock = TokenLock.builder()
//                .centricoToken("centricoToken")
//                .build();
//
//        doReturn(Optional.of(lock)).when(tokenRepoWrapperService).loadAndDecryptLock(user);
//
//        String res = tokenPollingService.pollToken(user);
//
//        assertEquals("centricoToken", res);
//    }
//
//    @Test
//    void testPollTokenWithTimeExceeded_shouldThrowException() {
//        User user = mock(User.class);
//
//        doReturn(Optional.empty()).when(tokenRepoWrapperService).loadAndDecryptLock(user);
//
//        TokenPollException ex = assertThrows(TokenPollException.class,
//                () -> tokenPollingService.pollToken(user));
//
//        assertEquals("Token not found, ending polling with failure after 5 retries for subjectId 0", ex.getMessage());
//    }
//
//    @Test
//    void testPollTokenWithInterruptedException_shouldThrowException() throws InterruptedException {
//        User user = mock(User.class);
//
//        doThrow(new InterruptedException()).when(sleeperService).sleep(anyLong());
//
//        GenericHypeException ex = assertThrows(GenericHypeException.class,
//                () -> tokenPollingService.pollToken(user));
//
//        assertEquals("Thread poll waiting interrupted for subjectId 0", ex.getMessage());
//    }
//
//}
