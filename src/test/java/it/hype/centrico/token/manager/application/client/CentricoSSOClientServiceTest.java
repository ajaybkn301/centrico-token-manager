//package it.hype.centrico.token.manager.application.client;
//
//import feign.FeignException;
//import it.hype.centrico.token.manager.application.client.feign.CentricoSsoClient;
//import it.hype.centrico.token.manager.application.client.models.AccessTokenResponse;
//import it.hype.centrico.token.manager.application.client.models.SsoTokenData;
//import it.hype.centrico.token.manager.application.client.models.SsoTokenResponse;
//import it.hype.centrico.token.manager.application.mappers.CentricoSsoClientMappers;
//import it.hype.centrico.token.manager.domain.exception.InvalidAccessTokenException;
//import it.hype.centrico.token.manager.domain.exception.InvalidAccessTokenRetrievedException;
//import it.hype.centrico.token.manager.domain.exception.InvalidAuthTokenException;
//import it.hype.centrico.token.manager.domain.exception.InvalidSsoTokenRetrievedException;
//import org.junit.jupiter.api.BeforeEach;
//import it.hype.centrico.token.manager.domain.model.AccessTokenData;
//import it.hype.centrico.token.manager.domain.model.SsoTokenInfo;
//import it.hype.centrico.token.manager.domain.model.SsoTokenResponseData;
//import it.hype.centrico.token.manager.domain.model.UserDetailsData;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class CentricoSSOClientServiceTest {
//
//    @InjectMocks
//    CentricoSsoClientServiceImpl centricoSSOService;
//
//    @Mock
//    CentricoSsoClient client;
//
//    @Mock
//    CentricoSsoClientMappers mappers;
//
//    private static final String AUTH_TOKEN = "AUTH_TOKEN";
//    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
//    private static final String DEVICE_ID = "HYPE_CUSTOMERS_MOBILE";
//    private static final String CHANNEL_ID = "HYPE_CUSTOMERS_MOBILE";
//
//    @BeforeEach
//    void init() {
//        ReflectionTestUtils.setField(centricoSSOService, "channelId", CHANNEL_ID);
//        ReflectionTestUtils.setField(centricoSSOService, "deviceId", DEVICE_ID);
//    }
//
//    @Test
//    void shouldReturnAccessToken_whenGetAccessTokenReceivesValidAccessToken() {
//        //  PARAMETERS
//        AccessTokenResponse token = AccessTokenResponse.builder().accessToken(ACCESS_TOKEN).build();
//        AccessTokenData data = AccessTokenData.builder().accessToken(ACCESS_TOKEN).userDetails(UserDetailsData.builder().ibCode("IBCODE").build()).build();
//
//        //  MOCKS
//        doReturn(token).when(client).getAccessToken(anyString(), anyString(), anyString(), anyString());
//        doReturn(data).when(mappers).convertToDomain(token);
//
//        //  TEST
//        AccessTokenData response = centricoSSOService.getAccessToken(ACCESS_TOKEN);
//
//        //  RESULT
//        verify(client).getAccessToken(ACCESS_TOKEN, "HYPE_CUSTOMERS_MOBILE", "HYPE_CUSTOMERS_MOBILE", "{}");
//        verify(mappers).convertToDomain(token);
//        assertEquals(data, response);
//    }
//
//    @Test
//    void shouldThrowInvalidAccessTokenRetrievedException_whenGetAccessTokenReceivesAnEmptyAccessToken() {
//        //  MOCKS
//        doReturn(null).when(client).getAccessToken(anyString(), anyString(), anyString(), anyString());
//
//        //  TEST
//        assertThrowsExactly(InvalidAccessTokenRetrievedException.class, () -> centricoSSOService.getAccessToken(AUTH_TOKEN));
//
//        //  RESULT
//        verify(client).getAccessToken(AUTH_TOKEN, "HYPE_CUSTOMERS_MOBILE", "HYPE_CUSTOMERS_MOBILE", "{}");
//    }
//
//    @Test
//    void shouldThrowInvalidAuthTokenException_whenGetAccessTokenReceivesUnauthorized() {
//        FeignException.Unauthorized ex = mock(FeignException.Unauthorized.class);
//        //  MOCKS
//        doThrow(ex).when(client).getAccessToken(anyString(), anyString(), anyString(), anyString());
//
//        //  TEST
//        assertThrowsExactly(InvalidAuthTokenException.class, () -> centricoSSOService.getAccessToken(AUTH_TOKEN));
//
//        //  RESULT
//        verify(client).getAccessToken(AUTH_TOKEN, "HYPE_CUSTOMERS_MOBILE", "HYPE_CUSTOMERS_MOBILE", "{}");
//    }
//
//    @Test
//    void shouldReturnSsoToken_whenGetSsoTokenReceivesValidSsoToken() {
//        //  PARAMETERS
//        SsoTokenData data = SsoTokenData.builder().ssoToken("sso_token").build();
//        SsoTokenResponse response = SsoTokenResponse.builder().data(data).build();
//        SsoTokenInfo tokenInfo = new SsoTokenInfo();
//        tokenInfo.setIbCode("ibCode");
//        tokenInfo.setSsoToken("sso_token");
//        SsoTokenResponseData expectedResponse = SsoTokenResponseData.builder().data(tokenInfo).build();
//
//        //  MOCKS
//        doReturn(response).when(client).getSsoToken(anyString());
//        doReturn(expectedResponse).when(mappers).convertToDomain(response);
//        //  TEST
//        SsoTokenResponseData result = centricoSSOService.getSsoToken(ACCESS_TOKEN);
//
//        //  RESULT
//        verify(client).getSsoToken(ACCESS_TOKEN);
//        assertEquals(expectedResponse, result);
//    }
//
//    @Test
//    void shouldThrowInvalidSsoTokenRetrievedException_whenGetSsoTokenReceivesEmptyResponse() {
//        //  MOCKS
//        doReturn(null).when(client).getSsoToken(ACCESS_TOKEN);
//
//        //  TEST
//        assertThrowsExactly(InvalidSsoTokenRetrievedException.class,
//                () -> centricoSSOService.getSsoToken(ACCESS_TOKEN));
//
//        //  RESULT
//        verify(client).getSsoToken(ACCESS_TOKEN);
//    }
//
//    @Test
//    void shouldThrowInvalidAccessTokenException_whenGetAccessTokenReceivesUnauthorized() {
//        FeignException.Unauthorized ex = mock(FeignException.Unauthorized.class);
//        //  MOCKS
//        doThrow(ex).when(client).getSsoToken(ACCESS_TOKEN);
//
//        //  TEST
//        assertThrowsExactly(InvalidAccessTokenException.class, () -> centricoSSOService.getSsoToken(ACCESS_TOKEN));
//
//        //  RESULT
//        verify(client).getSsoToken(ACCESS_TOKEN);
//    }
//}
