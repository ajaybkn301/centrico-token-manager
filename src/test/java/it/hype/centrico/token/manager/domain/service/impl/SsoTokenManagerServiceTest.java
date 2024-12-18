//package it.hype.centrico.token.manager.domain.service.impl;
//
//import it.hype.centrico.token.manager.domain.exception.InvalidAccessTokenRetrievedException;
//import it.hype.centrico.token.manager.domain.exception.InvalidSsoTokenRetrievedException;
//import it.hype.centrico.token.manager.domain.model.AccessTokenData;
//import it.hype.centrico.token.manager.domain.model.SsoTokenInfo;
//import it.hype.centrico.token.manager.domain.model.SsoTokenResponseData;
//import it.hype.centrico.token.manager.domain.model.UserDetailsData;
//import it.hype.centrico.token.manager.domain.service.CentricoSsoClientService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.doReturn;
//
//@ExtendWith(MockitoExtension.class)
//class SsoTokenManagerServiceTest {
//
//    @InjectMocks
//    SsoTokenManagerService ssoTokenManagerService;
//
//    @Mock
//    CentricoSsoClientService client;
//
//    @Test
//    void testGetSsoToken_shouldReturnToken() {
//        String authToken = "authToken";
//        String accessToken = "accessToken";
//        String ssoToken = "ssoToken";
//        String ibCode = "ibCode";
//        SsoTokenInfo tokenInfo = new SsoTokenInfo();
//        tokenInfo.setIbCode(ibCode);
//        tokenInfo.setSsoToken(ssoToken);
//        AccessTokenData accessTokenData = AccessTokenData.builder()
//                .userDetails(UserDetailsData.builder()
//                        .ibCode(ibCode)
//                        .build()
//                ).accessToken(accessToken)
//                .build();
//        SsoTokenResponseData ssoTokenResponseData = SsoTokenResponseData.builder().data(tokenInfo).build();
//        doReturn(accessTokenData).when(client).getAccessToken(authToken);
//        doReturn(ssoTokenResponseData).when(client).getSsoToken(accessToken);
//
//        String res = ssoTokenManagerService.getSsoToken(authToken, ibCode);
//
//        assertEquals(ssoToken, res);
//    }
//
//    @Test
//    void testGetAccessTokenWithEmptyAccessToken_shouldThrowException() {
//        String authToken = "authToken";
//        String ibCode = "ibCode";
//        AccessTokenData accessTokenData = AccessTokenData.builder()
//                .userDetails(UserDetailsData.builder()
//                        .ibCode(ibCode)
//                        .build()
//                ).accessToken("")
//                .build();
//        doReturn(accessTokenData).when(client).getAccessToken(authToken);
//
//        var ex = assertThrows(InvalidAccessTokenRetrievedException.class,
//                () -> ssoTokenManagerService.getSsoToken(authToken, ibCode));
//
//        assertEquals("Empty access token from Centrico", ex.getMessage());
//    }
//
//    @Test
//    void testGetAccessTokenWithEmptyUserDetails_shouldThrowException() {
//        String authToken = "authToken";
//        String ibCode = "ibCode";
//        String accessToken = "accessToken";
//        AccessTokenData accessTokenData = AccessTokenData.builder()
//                .accessToken(accessToken)
//                .build();
//
//        doReturn(accessTokenData).when(client).getAccessToken(authToken);
//
//        var ex = assertThrows(InvalidAccessTokenRetrievedException.class,
//                () -> ssoTokenManagerService.getSsoToken(authToken, ibCode));
//
//        assertEquals("Empty user details received from centrico response", ex.getMessage());
//    }
//
//    @Test
//    void testGetAccessTokenWithInvalidIbCode_shouldThrowException() {
//        String authToken = "authToken";
//        String ibCode = "ibCode";
//        String accessToken = "accessToken";
//        AccessTokenData accessTokenData = AccessTokenData.builder()
//                .accessToken(accessToken)
//                .userDetails(UserDetailsData.builder()
//                        .ibCode(ibCode)
//                        .build()
//                ).build();
//        doReturn(accessTokenData).when(client).getAccessToken(authToken);
//
//        var ex = assertThrows(InvalidAccessTokenRetrievedException.class,
//                () -> ssoTokenManagerService.getSsoToken(authToken, "userIbCode"));
//
//        assertEquals("Invalid access token belonging to ibCode instead of required user userIbCode", ex.getMessage());
//    }
//
//    @Test
//    void testGetSsoTokenWithInvalidSsoToken_shouldThrowException() {
//        String authToken = "authToken";
//        String accessToken = "accessToken";
//        String ssoToken = "";
//        String ibCode = "ibCode";
//        SsoTokenInfo tokenInfo = new SsoTokenInfo();
//        tokenInfo.setIbCode(ibCode);
//        tokenInfo.setSsoToken(ssoToken);
//        AccessTokenData accessTokenData = AccessTokenData.builder()
//                .userDetails(UserDetailsData.builder()
//                        .ibCode(ibCode)
//                        .build()
//                ).accessToken(accessToken)
//                .build();
//        SsoTokenResponseData ssoTokenResponseData = SsoTokenResponseData.builder().data(tokenInfo).build();
//        doReturn(accessTokenData).when(client).getAccessToken(authToken);
//        doReturn(ssoTokenResponseData).when(client).getSsoToken(accessToken);
//
//        var ex = assertThrows(InvalidSsoTokenRetrievedException.class,
//                () -> ssoTokenManagerService.getSsoToken(authToken, ibCode));
//
//        assertEquals("Empty sso token from Centrico", ex.getMessage());
//    }
//
//    @Test
//    void testGetSsoTokenWithInvalidIbCode_shouldThrowException() {
//        String authToken = "authToken";
//        String accessToken = "accessToken";
//        String ssoToken = "sso_token";
//        String ibCode = "ibCode";
//        SsoTokenInfo tokenInfo = new SsoTokenInfo();
//        tokenInfo.setIbCode("invalid_ibCode");
//        tokenInfo.setSsoToken(ssoToken);
//        AccessTokenData accessTokenData = AccessTokenData.builder()
//                .userDetails(UserDetailsData.builder()
//                        .ibCode(ibCode)
//                        .build()
//                ).accessToken(accessToken)
//                .build();
//        SsoTokenResponseData ssoTokenResponseData = SsoTokenResponseData.builder().data(tokenInfo).build();
//        doReturn(accessTokenData).when(client).getAccessToken(authToken);
//        doReturn(ssoTokenResponseData).when(client).getSsoToken(accessToken);
//
//        var ex = assertThrows(InvalidSsoTokenRetrievedException.class,
//                () -> ssoTokenManagerService.getSsoToken(authToken, ibCode));
//
//        assertEquals("Invalid sso token belonging to invalid_ibCode instead of required user ibCode", ex.getMessage());
//    }
//
//}
