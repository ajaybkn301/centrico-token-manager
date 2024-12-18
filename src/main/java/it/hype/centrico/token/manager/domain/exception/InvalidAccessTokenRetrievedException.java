package it.hype.centrico.token.manager.domain.exception;

import it.hype.centrico.token.manager.domain.exception.errors.CentricoTokenManagerErrors;

public class InvalidAccessTokenRetrievedException extends CentricoTokenException {
    public InvalidAccessTokenRetrievedException(String message) {
        super(message, CentricoTokenManagerErrors.ACCESS_TOKEN_RETRIEVE_ERROR, null);
    }
}
