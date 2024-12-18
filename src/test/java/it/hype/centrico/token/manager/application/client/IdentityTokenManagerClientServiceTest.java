//package it.hype.centrico.token.manager.application.client;
//
//import feign.FeignException;
//import it.hype.authhandler.model.HypeUser;
//import it.hype.centrico.token.manager.application.client.feign.IdentityTokenManagerFeignClient;
//import it.hype.centrico.token.manager.domain.exception.InvalidAuthTokenException;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class IdentityTokenManagerClientServiceTest {
//
//    @Mock
//    IdentityTokenManagerFeignClient feignClient;
//
//    @InjectMocks
//    IdentityTokenManagerClientServiceImpl identityTokenManagerClientService;
//
//    @Test
//    void testGetAccessToken() {
//        String authToken = RandomStringUtils.randomAlphanumeric(10);
//        HypeUser hypeUser = new HypeUser();
//        hypeUser.setFirstname("firstName");
//
//        doReturn(hypeUser).when(feignClient).validateToken(authToken, "{}");
//
//        HypeUser result = identityTokenManagerClientService.validateToken(authToken);
//
//        verify(feignClient, times(1)).validateToken(authToken, "{}");
//        assertEquals(hypeUser, result);
//    }
//
//    @Test
//    void testGetAccessTokenWithUnauthorized_shouldThroeInvalidAuthTokenException() {
//        String authToken = RandomStringUtils.randomAlphanumeric(10);
//        HypeUser hypeUser = new HypeUser();
//        hypeUser.setFirstname("firstName");
//
//        doThrow(FeignException.Unauthorized.class).when(feignClient).validateToken(authToken, "{}");
//
//        var ex = assertThrows(InvalidAuthTokenException.class,
//                () -> identityTokenManagerClientService.validateToken(authToken));
//
//        String exceptionMessage = "Received unauthorized checking auth token on identity-token-manager";
//        assertEquals(exceptionMessage, ex.getMessage());
//
//        verify(feignClient, times(1)).validateToken(authToken, "{}");
//
//    }
//
//}
