//package it.hype.centrico.token.manager.domain.service.impl;
//
//import it.hype.authhandler.model.User;
//import it.hype.centrico.token.manager.domain.model.TokenLock;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.doReturn;
//
//@ExtendWith(MockitoExtension.class)
//class SsoTokenServiceTest {
//
//    @InjectMocks
//    SsoTokenService ssoTokenService;
//
//    @Mock
//    User user;
//
//    @Mock
//    TokenRepoWrapperService tokenRepoWrapperService;
//
//    @Mock
//    TokenRetrievingService tokenRetrievingService;
//
//    @Mock
//    TokenPollingService tokenPollingService;
//
//    @Test
//    void testGetSsoToken_shouldReturnToken() {
//        String authToken = "authToken";
//        String ssoToken = "ssoToken";
//
//        TokenLock lock = TokenLock.builder()
//                .centricoToken(ssoToken)
//                .build();
//        doReturn(Optional.of(lock)).when(tokenRepoWrapperService).loadAndDecryptLock(user);
//
//        String res = ssoTokenService.getSsoToken(authToken);
//
//        assertEquals(ssoToken, res);
//    }
//
//    @Test
//    void testGetSsoTokenWithoutCachedToken_shouldReturnNewToken() {
//        String authToken = "authToken";
//        String ssoToken = "ssoToken";
//
//        TokenLock lock = TokenLock.builder().build();
//        doReturn(Optional.of(lock)).when(tokenRepoWrapperService).loadAndDecryptLock(user);
//
//        doReturn(ssoToken).when(tokenRetrievingService).retrieveToken(user, authToken);
//
//        String res = ssoTokenService.getSsoToken(authToken);
//
//        assertEquals(ssoToken, res);
//    }
//
//    @Test
//    void testGetSsoTokenWithAlreadyActiveLock_shouldReturnNewToken() {
//        String authToken = "authToken";
//        String ssoToken = "ssoToken";
//
//        TokenLock lock = TokenLock.builder().build();
//        doReturn(Optional.of(lock)).when(tokenRepoWrapperService).loadAndDecryptLock(user);
//
//        doReturn(null).when(tokenRetrievingService).retrieveToken(user, authToken);
//
//        doReturn(ssoToken).when(tokenPollingService).pollToken(user);
//
//        String res = ssoTokenService.getSsoToken(authToken);
//
//        assertEquals(ssoToken, res);
//    }
//}
