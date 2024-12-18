package it.hype.centrico.token.manager.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SsoTokenInfo {
    private String ssoToken;
    private String ibCode;
    private String uid;
    private String abi;
    private String authLevel;
    private String clientId;
    private String cot;
    private String lastActivity;
}
