package it.hype.centrico.token.manager.application.mappers;

import it.hype.centrico.token.manager.application.client.models.AccessTokenResponse;
import it.hype.centrico.token.manager.application.client.models.SsoTokenData;
import it.hype.centrico.token.manager.application.client.models.SsoTokenResponse;
import it.hype.centrico.token.manager.application.client.models.UserDetails;
import it.hype.centrico.token.manager.domain.model.AccessTokenData;
import it.hype.centrico.token.manager.domain.model.SsoTokenInfo;
import it.hype.centrico.token.manager.domain.model.SsoTokenResponseData;
import it.hype.centrico.token.manager.domain.model.UserDetailsData;
import org.mapstruct.Mapper;

@Mapper
public interface CentricoSsoClientMappers {
    AccessTokenData convertToDomain(AccessTokenResponse response);
    UserDetailsData convertToDomain(UserDetails userDetailsData);

    SsoTokenResponseData convertToDomain(SsoTokenResponse response);
    SsoTokenInfo convertToDomain(SsoTokenData data);
}
