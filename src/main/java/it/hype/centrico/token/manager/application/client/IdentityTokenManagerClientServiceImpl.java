package it.hype.centrico.token.manager.application.client;

import feign.FeignException;
import it.hype.authhandler.model.HypeUser;
import it.hype.centrico.token.manager.application.client.feign.IdentityTokenManagerFeignClient;
import it.hype.centrico.token.manager.domain.exception.InvalidAuthTokenException;
import it.hype.centrico.token.manager.domain.service.IdentityTokenManagerClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

//@AllArgsConstructor
//@Service
//public class IdentityTokenManagerClientServiceImpl implements IdentityTokenManagerClientService {
//
//    private final IdentityTokenManagerFeignClient client;
//
//    @Override
//    public HypeUser validateToken(String authToken) {
//        try {
//            return client.validateToken(authToken, "{}");
//        } catch (FeignException.Unauthorized ex) {
//            throw new InvalidAuthTokenException("Received unauthorized checking auth token on identity-token-manager", ex);
//        }
//    }
//}
