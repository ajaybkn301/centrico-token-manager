package it.hype.centrico.token.manager.application.client;

import feign.FeignException;
import it.hype.centrico.token.manager.application.client.feign.CentricoSsoClient;
import it.hype.centrico.token.manager.application.client.models.AccessTokenResponse;
import it.hype.centrico.token.manager.application.client.models.SsoTokenResponse;
import it.hype.centrico.token.manager.application.mappers.CentricoSsoClientMappers;
import it.hype.centrico.token.manager.domain.exception.InvalidAccessTokenException;
import it.hype.centrico.token.manager.domain.exception.InvalidAccessTokenRetrievedException;
import it.hype.centrico.token.manager.domain.exception.InvalidAuthTokenException;
import it.hype.centrico.token.manager.domain.exception.InvalidSsoTokenRetrievedException;
import it.hype.centrico.token.manager.domain.model.AccessTokenData;
import it.hype.centrico.token.manager.domain.model.SsoTokenResponseData;
import it.hype.centrico.token.manager.domain.service.CentricoSsoClientService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CentricoSsoClientServiceImpl implements CentricoSsoClientService {

    @Value("${centrico.sso.access-token.device-id}")
    private String deviceId;

    @Value("${centrico.sso.access-token.channel-id}")
    private String channelId;

    private final CentricoSsoClient client;
    private final CentricoSsoClientMappers mappers;

    public AccessTokenData getAccessToken(String authToken) {
        AccessTokenResponse response = retrieveAccessToken(authToken, deviceId, channelId);
        if (response == null) {
            throw new InvalidAccessTokenRetrievedException("Received an empty access token response from Centrico");
        }
        return mappers.convertToDomain(response);
    }

    private AccessTokenResponse retrieveAccessToken(String authToken, String deviceId, String channelId) {
        try {
            return client.getAccessToken(authToken, deviceId, channelId, "{}");
        } catch (FeignException.Unauthorized ex) {
            throw new InvalidAuthTokenException("Received unauthorized retrieving access token from Centrico", ex);
        }
    }

    public SsoTokenResponseData getSsoToken(String accessToken) {
        SsoTokenResponse response = retrieveSsoToken(accessToken);
        if (response == null || response.getData() == null) {
            throw new InvalidSsoTokenRetrievedException("Empty sso token response from Centrico");
        }
        return mappers.convertToDomain(response);
    }

    private SsoTokenResponse retrieveSsoToken(String accessToken) {
        try {
            return client.getSsoToken(accessToken);
        } catch (FeignException.Unauthorized ex) {
            throw new InvalidAccessTokenException("Received unauthorized retrieving sso token from Centrico", ex);
        }
    }
}
