package it.hype.centrico.token.manager.domain.exception;

import it.hype.centrico.token.manager.domain.exception.errors.CentricoTokenManagerErrors;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class CentricoTokenException extends RuntimeException {

    private final transient CentricoTokenManagerErrors errorCode;

    public CentricoTokenException(String message, CentricoTokenManagerErrors errorCode, Throwable t) {
        super(message, t);
        this.errorCode = errorCode;
    }

}