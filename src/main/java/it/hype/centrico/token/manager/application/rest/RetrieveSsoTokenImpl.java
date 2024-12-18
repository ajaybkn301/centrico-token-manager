package it.hype.centrico.token.manager.application.rest;

import it.hype.centrico.token.manager.domain.service.IdentityTokenManagerClientService;
import it.hype.centrico.token.manager.domain.service.impl.SsoTokenService;
import it.hype.centrico.token.manager.generated.application.api.RetrieveSsoTokenFromCentricoApiDelegate;
import it.hype.centrico.token.manager.generated.application.model.GetTokenResponse;
import it.hype.errorhandler.exception.HypeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@Service
@RequiredArgsConstructor
public class RetrieveSsoTokenImpl implements RetrieveSsoTokenFromCentricoApiDelegate {

    private final SsoTokenService ssoTokenService;
    //private final IdentityTokenManagerClientService identityTokenManagerClientService;

    @Override
    public ResponseEntity<GetTokenResponse> getSsoToken(String authToken) throws HypeException {
        //identityTokenManagerClientService.validateToken(authToken);
        String ssoToken = ssoTokenService.getSsoToken(authToken);
        GetTokenResponse response = new GetTokenResponse().token(ssoToken);
        return ResponseEntity.ok(response);
    }
}
