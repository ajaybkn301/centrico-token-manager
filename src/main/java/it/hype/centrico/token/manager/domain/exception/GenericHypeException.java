package it.hype.centrico.token.manager.domain.exception;

import it.hype.centrico.token.manager.domain.exception.errors.CentricoTokenManagerErrors;

public class GenericHypeException extends CentricoTokenException {
    public GenericHypeException(String message, Throwable throwable) {
        super(message, CentricoTokenManagerErrors.GENERIC_INTERNAL_SERVER_ERROR, throwable);
    }
}
