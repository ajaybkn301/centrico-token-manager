package it.hype.centrico.token.manager.domain.exception.errors;

import it.hype.errorhandler.enums.IErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CentricoTokenManagerErrors implements IErrorCode {

    private static final String ERROR_ROOT_CODE = "ERRCTM";

    private final String code;
    private final String description;
    private final HttpStatus status;

    public static final CentricoTokenManagerErrors GENERIC_INTERNAL_SERVER_ERROR = new CentricoTokenManagerErrors(
            ERROR_ROOT_CODE + "001",
            "Generic error",
            HttpStatus.INTERNAL_SERVER_ERROR
    );

    public static final CentricoTokenManagerErrors ACCESS_TOKEN_RETRIEVE_ERROR = new CentricoTokenManagerErrors(
            ERROR_ROOT_CODE + "002",
            "An invalid access token has been retrieved from centrico SSO",
            HttpStatus.EXPECTATION_FAILED
    );

    public static final CentricoTokenManagerErrors SSO_TOKEN_RETRIEVE_ERROR = new CentricoTokenManagerErrors(
            ERROR_ROOT_CODE + "003",
            "An invalid sso token has been retrieved from centrico SSO",
            HttpStatus.EXPECTATION_FAILED
    );

    public static final CentricoTokenManagerErrors CRYPTO_ERROR = new CentricoTokenManagerErrors(
            ERROR_ROOT_CODE + "004",
            "Error in token encryption/decryption",
            HttpStatus.INTERNAL_SERVER_ERROR
    );

    public static final CentricoTokenManagerErrors INVALID_AUTH_TOKEN = new CentricoTokenManagerErrors(
            ERROR_ROOT_CODE + "005",
            "The given auth-token is not valid",
            HttpStatus.UNAUTHORIZED
    );

    public static final CentricoTokenManagerErrors INVALID_ACCESS_TOKEN = new CentricoTokenManagerErrors(
            ERROR_ROOT_CODE + "006",
            "The given access token is not valid",
            HttpStatus.EXPECTATION_FAILED
    );

    @Override
    public String name() {
        return code;
    }
}
