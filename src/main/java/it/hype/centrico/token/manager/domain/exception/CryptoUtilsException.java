package it.hype.centrico.token.manager.domain.exception;

import it.hype.centrico.token.manager.domain.exception.errors.CentricoTokenManagerErrors;

public class CryptoUtilsException extends CentricoTokenException {
    public CryptoUtilsException(String message) {
        this(message, null);
    }

    public CryptoUtilsException(String message, Throwable throwable) {
        super(message, CentricoTokenManagerErrors.CRYPTO_ERROR, throwable);
    }
}
