package it.hype.centrico.token.manager.domain.exception;

import it.hype.centrico.token.manager.domain.exception.errors.CentricoTokenManagerErrors;

public class InvalidAuthTokenException extends CentricoTokenException {
    public InvalidAuthTokenException(String message, Throwable throwable) {
        super(message, CentricoTokenManagerErrors.INVALID_AUTH_TOKEN, throwable);
    }
}
