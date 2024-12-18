package it.hype.centrico.token.manager.domain.service;

import it.hype.centrico.token.manager.domain.model.AccessTokenData;
import it.hype.centrico.token.manager.domain.model.SsoTokenResponseData;

public interface CentricoSsoClientService {

    AccessTokenData getAccessToken(String authToken);

    SsoTokenResponseData getSsoToken(String accessToken);

}
