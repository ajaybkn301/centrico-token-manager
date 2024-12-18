package it.hype.centrico.token.manager.application.exceptionhandler;

import feign.FeignException;
import it.hype.centrico.token.manager.domain.exception.CentricoTokenException;
import it.hype.centrico.token.manager.domain.exception.errors.CentricoTokenManagerErrors;
import it.hype.centrico.token.manager.generated.application.model.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@ControllerAdvice
@Log4j2
public class CentricoTokenManagerExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handleFeignException(FeignException ex, WebRequest request) {
        logError(ex, request);
        return newResponseEntity(request, CentricoTokenManagerErrors.GENERIC_INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CentricoTokenException.class)
    public ResponseEntity<ErrorResponse> handleCentricoTokenException(CentricoTokenException ex, WebRequest request) {
        logError(ex, request);
        return newResponseEntity(request, ex.getErrorCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest request) {
        logError(ex, request);
        return newResponseEntity(request, CentricoTokenManagerErrors.GENERIC_INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> newResponseEntity(WebRequest request, CentricoTokenManagerErrors error) {
        ErrorResponse errorResponse = newErrorResponse(request, error);
        return new ResponseEntity<>(errorResponse, error.getStatus());
    }

    private ErrorResponse newErrorResponse(WebRequest request, CentricoTokenManagerErrors error) {
        return new ErrorResponse()
                .errorCode(error.getCode())
                .errorMessage(error.getDescription())
                .timestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli())
                .uri(((ServletWebRequest) request).getRequest().getRequestURI());
    }

    private void logError(Exception ex, WebRequest request) {
        String requestURI = ((ServletWebRequest) request).getRequest().getRequestURI();
        log.error("An error happened while calling {} API: {}", requestURI, ex.getMessage(), ex);
    }
}
