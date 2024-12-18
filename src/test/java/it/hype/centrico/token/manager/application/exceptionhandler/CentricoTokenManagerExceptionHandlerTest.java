//package it.hype.centrico.token.manager.application.exceptionhandler;
//
//import feign.FeignException;
//import it.hype.centrico.token.manager.domain.exception.InvalidAccessTokenRetrievedException;
//import it.hype.centrico.token.manager.domain.exception.TokenPollException;
//import it.hype.centrico.token.manager.domain.exception.errors.CentricoTokenManagerErrors;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.web.context.request.ServletWebRequest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.doReturn;
//
//@ExtendWith(MockitoExtension.class)
//class CentricoTokenManagerExceptionHandlerTest {
//
//    @InjectMocks
//    private CentricoTokenManagerExceptionHandler exceptionHandler;
//
//    @Test
//    void testHandleCentricoTokenException() {
//        //  PARAMETERS
////        ServletWebRequest request = new ServletWebRequest(new MockHttpServletRequest());
////        InvalidAccessTokenRetrievedException ex = new InvalidAccessTokenRetrievedException("test");
////
////        var result = exceptionHandler.handleCentricoTokenException(ex, request);
////
////        assertNotNull(result.getBody());
////        assertEquals(HttpStatus.EXPECTATION_FAILED, result.getStatusCode());
////        assertEquals("ERRCTM002", result.getBody().getErrorCode());
//    }
//
//    @Test
//    void testHandleFeignException() {
//        //  PARAMETERS
//        ServletWebRequest request = new ServletWebRequest(new MockHttpServletRequest());
//        FeignException.BadGateway ex = Mockito.mock(FeignException.BadGateway.class);
//
//        //  MOCKS
//        doReturn("message").when(ex).getMessage();
//
//        //  TEST
//        var result = exceptionHandler.handleFeignException(ex, request);
//
//        //  RESULTS
//        assertNotNull(result.getBody());
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
//        assertEquals(CentricoTokenManagerErrors.GENERIC_INTERNAL_SERVER_ERROR.name(), result.getBody().getErrorCode());
//    }
//
//    @Test
//    void testHandleException() {
//        //  PARAMETERS
//        ServletWebRequest request = new ServletWebRequest(new MockHttpServletRequest());
//        TokenPollException ex = Mockito.mock(TokenPollException.class);
//
//        doReturn("message").when(ex).getMessage();
//
//        //  TEST
//        var result = exceptionHandler.handleException(ex, request);
//
//        //  RESULTS
//        assertNotNull(result.getBody());
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
//        assertEquals(CentricoTokenManagerErrors.GENERIC_INTERNAL_SERVER_ERROR.name(), result.getBody().getErrorCode());
//    }
//
//}
