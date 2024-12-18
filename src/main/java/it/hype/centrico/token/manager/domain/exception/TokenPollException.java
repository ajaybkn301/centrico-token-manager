package it.hype.centrico.token.manager.domain.exception;

import it.hype.centrico.token.manager.domain.exception.errors.CentricoTokenManagerErrors;

public class TokenPollException extends CentricoTokenException {
    public TokenPollException(String message) {
        super(message, CentricoTokenManagerErrors.GENERIC_INTERNAL_SERVER_ERROR, null);
    }
}
