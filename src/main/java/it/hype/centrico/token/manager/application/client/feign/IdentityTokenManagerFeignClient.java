package it.hype.centrico.token.manager.application.client.feign;


import it.hype.authhandler.model.HypeUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "identity-token-manager-client", url = "${identity-token-manager.services.url}")
public interface IdentityTokenManagerFeignClient {

    @PostMapping(
            value = "${identity-token-manager.services.api.validate-token}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    HypeUser validateToken(@RequestHeader("auth-token") String authToken, String body);

}
