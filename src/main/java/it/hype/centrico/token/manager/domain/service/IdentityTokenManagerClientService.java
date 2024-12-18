package it.hype.centrico.token.manager.domain.service;

import it.hype.authhandler.model.HypeUser;

public interface IdentityTokenManagerClientService {
    HypeUser validateToken(String authToken);
}
