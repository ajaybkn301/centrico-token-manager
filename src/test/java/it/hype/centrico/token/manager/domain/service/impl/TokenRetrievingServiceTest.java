//package it.hype.centrico.token.manager.domain.service.impl;
//
//import feign.FeignException;
//import it.hype.authhandler.model.User;
//import it.hype.centrico.token.manager.domain.exception.CentricoTokenException;
//import it.hype.centrico.token.manager.domain.exception.GenericHypeException;
//import it.hype.centrico.token.manager.domain.service.callable.GetTokenLockingTask;
//import it.hype.centrico.token.manager.domain.utils.LockUtils;
//import net.javacrumbs.shedlock.core.LockConfiguration;
//import net.javacrumbs.shedlock.core.LockingTaskExecutor;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.dao.DuplicateKeyException;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import java.time.Duration;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class TokenRetrievingServiceTest {
//
//    public static final Duration LOCK_AT_MOST = Duration.ofMillis(30);
//    public static final Duration LOCK_AT_LEAST = Duration.ofMillis(0);
//
//    @InjectMocks
//    TokenRetrievingService tokenRetrievingService;
//
//    @Mock
//    LockingTaskExecutor executor;
//
//    @Mock
//    SsoTokenManagerService ssoTokenManagerService;
//
//    @Mock
//    TokenRepoWrapperService tokenRepoWrapperService;
//
//    @BeforeEach
//    void init() {
//        ReflectionTestUtils.setField(tokenRetrievingService, "lockAtMostFor", LOCK_AT_MOST);
//        ReflectionTestUtils.setField(tokenRetrievingService, "lockAtLeastFor", LOCK_AT_LEAST);
//    }
//
//    @Test
//    void testRetrieveToken_shouldReturnToken() throws Throwable {
//        User user = mock(User.class);
//        doReturn("userTenant").when(user).getTenant();
//        doReturn("userCorId").when(user).getCorrelationId();
//
//        GetTokenLockingTask task = GetTokenLockingTask.builder()
//                .tokenRepoWrapperService(tokenRepoWrapperService)
//                .ssoTokenManagerService(ssoTokenManagerService)
//                .authToken("authToken")
//                .user(user)
//                .build();
//
//        String lockId = LockUtils.calculateLockId(user);
//        LockingTaskExecutor.TaskResult result = mock(LockingTaskExecutor.TaskResult.class);
//        doReturn("resultToken").when(result).getResult();
//        doReturn(result).when(executor).executeWithLock(eq(task), any(LockConfiguration.class));
//
//        String res = tokenRetrievingService.retrieveToken(user, "authToken");
//        assertEquals("resultToken", res);
//
//        ArgumentCaptor<LockConfiguration> confCaptor = ArgumentCaptor.forClass(LockConfiguration.class);
//        verify(executor).executeWithLock(eq(task), confCaptor.capture());
//        LockConfiguration conf = confCaptor.getValue();
//        assertEquals(lockId, conf.getName());
//        assertEquals(LOCK_AT_MOST, conf.getLockAtMostFor());
//        assertEquals(LOCK_AT_LEAST, conf.getLockAtLeastFor());
//    }
//
//    @Test
//    void testRetrieveTokenWithRuntimeException_shouldThrowGenericHypeException() throws Throwable {
//        User user = mock(User.class);
//
//        doThrow(new RuntimeException("Test"))
//                .when(executor).executeWithLock(any(GetTokenLockingTask.class), any(LockConfiguration.class));
//
//        GenericHypeException ex = assertThrows(GenericHypeException.class,
//                () -> tokenRetrievingService.retrieveToken(user, "authToken"));
//
//        assertEquals("Error retrieving token for subjectId 0", ex.getMessage());
//    }
//
//    @Test
//    void testRetrieveTokenWithFeignException_shouldRethrowException() throws Throwable {
//        User user = mock(User.class);
//
//        FeignException.BadRequest exception = mock(FeignException.BadRequest.class);
//        doReturn("Test").when(exception).getMessage();
//        doThrow(exception)
//                .when(executor).executeWithLock(any(GetTokenLockingTask.class), any(LockConfiguration.class));
//
//        FeignException.BadRequest ex = assertThrows(FeignException.BadRequest.class,
//                () -> tokenRetrievingService.retrieveToken(user, "authToken"));
//
//        assertEquals("Test", ex.getMessage());
//    }
//
//    @Test
//    void testRetrieveTokenWithCentricoTokenException_shouldRethrowException() throws Throwable {
//        User user = mock(User.class);
//
//        CentricoTokenException exception = mock(CentricoTokenException.class);
//        doReturn("Test").when(exception).getMessage();
//        doThrow(exception)
//                .when(executor).executeWithLock(any(GetTokenLockingTask.class), any(LockConfiguration.class));
//
//        CentricoTokenException ex = assertThrows(CentricoTokenException.class,
//                () -> tokenRetrievingService.retrieveToken(user, "authToken"));
//
//        assertEquals("Test", ex.getMessage());
//    }
//
//    @Test
//    void testRetrieveTokenWithDuplicateKeyException_shouldReturnNull() throws Throwable {
//        User user = mock(User.class);
//
//        DuplicateKeyException exception = mock(DuplicateKeyException.class);
//
//        doThrow(exception)
//                .when(executor).executeWithLock(any(GetTokenLockingTask.class), any(LockConfiguration.class));
//
//        String token = tokenRetrievingService.retrieveToken(user, "authToken");
//
//        assertNull(token);
//    }
//
//
//}
