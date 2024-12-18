package it.hype.centrico.token.manager.application.client.feign;

import it.hype.centrico.token.manager.application.client.models.AccessTokenResponse;
import it.hype.centrico.token.manager.application.client.models.SsoTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient(name = "centrico-sso-client", url = "${centrico.sso.url}")
public interface CentricoSsoClient {

    @PostMapping(value = "/sso/rest/api/connector/access_token",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    AccessTokenResponse getAccessToken(
            @RequestHeader(value = "X-Cnctr-Fast") String authToken,
            @RequestHeader(value = "X-Cnctr-Device-Id") String deviceId,
            @RequestHeader(value = "X-Cnctr-Channel-Id") String channelId,
            String body
    );

    @GetMapping(value = "/sso/rest/api/connector/userinfo/{accessToken}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    SsoTokenResponse getSsoToken(@PathVariable(value = "accessToken") String accessToken);

}
