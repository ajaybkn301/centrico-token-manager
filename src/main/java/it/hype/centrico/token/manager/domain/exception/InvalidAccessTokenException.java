package it.hype.centrico.token.manager.domain.exception;

import it.hype.centrico.token.manager.domain.exception.errors.CentricoTokenManagerErrors;

public class InvalidAccessTokenException extends CentricoTokenException {
    public InvalidAccessTokenException(String message, Throwable throwable) {
        super(message, CentricoTokenManagerErrors.INVALID_ACCESS_TOKEN, throwable);
    }
}
