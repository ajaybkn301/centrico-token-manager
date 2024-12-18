//package it.hype.centrico.token.manager.application.rest;
//
//import it.hype.authhandler.model.HypeUser;
//import it.hype.centrico.token.manager.application.rest.RetrieveSsoTokenImpl;
//import it.hype.centrico.token.manager.domain.service.IdentityTokenManagerClientService;
//import it.hype.centrico.token.manager.domain.service.impl.SsoTokenService;
//import it.hype.centrico.token.manager.generated.application.model.GetTokenResponse;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.ResponseEntity;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class RetrieveSSOTokenTest {
//
//    @InjectMocks
//    RetrieveSsoTokenImpl retrieveSsoToken;
//
//    @Mock
//    SsoTokenService service;
//
//    @Mock
//    IdentityTokenManagerClientService identityTokenManagerClientService;
//
//    @Test
//    void testGetToken() {
////        //  PARAMETERS
////        String authToken = "authTest";
////        String tokenResponse = "token";
////
////        //  MOCKS
////        doReturn(tokenResponse).when(service).getSsoToken(authToken);
////
////        //  TEST
////        ResponseEntity<GetTokenResponse> response = retrieveSsoToken.getSsoToken(authToken);
////
////        //  RESULT
////        verify(identityTokenManagerClientService).validateToken(authToken);
////        verify(service, times(1)).getSsoToken(authToken);
////        assertEquals(200, response.getStatusCodeValue());
////        assertNotNull(response.getBody());
////        assertEquals(tokenResponse, response.getBody().getToken());
//    }
//}
