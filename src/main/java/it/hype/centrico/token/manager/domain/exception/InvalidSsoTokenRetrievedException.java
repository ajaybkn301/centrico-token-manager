package it.hype.centrico.token.manager.domain.exception;

import it.hype.centrico.token.manager.domain.exception.errors.CentricoTokenManagerErrors;

public class InvalidSsoTokenRetrievedException extends CentricoTokenException {
    public InvalidSsoTokenRetrievedException(String message) {
        this(message, null);
    }

    public InvalidSsoTokenRetrievedException(String message, Throwable throwable) {
        super(message, CentricoTokenManagerErrors.SSO_TOKEN_RETRIEVE_ERROR, throwable);
    }
}
