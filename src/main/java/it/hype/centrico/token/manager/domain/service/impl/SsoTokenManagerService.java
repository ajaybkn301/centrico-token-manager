package it.hype.centrico.token.manager.domain.service.impl;

import it.hype.centrico.token.manager.domain.exception.InvalidAccessTokenRetrievedException;
import it.hype.centrico.token.manager.domain.exception.InvalidSsoTokenRetrievedException;
import it.hype.centrico.token.manager.domain.model.AccessTokenData;
import it.hype.centrico.token.manager.domain.model.SsoTokenInfo;
import it.hype.centrico.token.manager.domain.model.SsoTokenResponseData;
import it.hype.centrico.token.manager.domain.service.CentricoSsoClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class SsoTokenManagerService {

    private final CentricoSsoClientService client;

    public String getSsoToken(String authToken, String ibCode) {
        String accessToken = getAccessToken(authToken, ibCode);
        return getSsoTokenSub(accessToken, ibCode);
    }

    private String getAccessToken(String authToken, String ibCode) {
        AccessTokenData accessToken = client.getAccessToken(authToken);
        if (StringUtils.isBlank(accessToken.getAccessToken())) {
            throw new InvalidAccessTokenRetrievedException("Empty access token from Centrico");
        }
        if(accessToken.getUserDetails() == null ){
            throw new InvalidAccessTokenRetrievedException("Empty user details received from centrico response");
        }
        if(!StringUtils.equals(ibCode, accessToken.getUserDetails().getIbCode())){
            throw new InvalidAccessTokenRetrievedException(
                    "Invalid access token belonging to " + accessToken.getUserDetails().getIbCode() +
                            " instead of required user " + ibCode
            );
        }
        return accessToken.getAccessToken();
    }

    private String getSsoTokenSub(String accessToken, String ibCode) {
        SsoTokenResponseData responseData = client.getSsoToken(accessToken);
        SsoTokenInfo ssoTokenData = responseData.getData();
        if (ssoTokenData == null || StringUtils.isBlank(ssoTokenData.getSsoToken())) {
            throw new InvalidSsoTokenRetrievedException("Empty sso token from Centrico");
        }
        if(!StringUtils.equals(ibCode, ssoTokenData.getIbCode())){
            throw new InvalidSsoTokenRetrievedException(
                    "Invalid sso token belonging to " + ssoTokenData.getIbCode() +
                            " instead of required user " + ibCode
            );
        }
        return ssoTokenData.getSsoToken();
    }
}
