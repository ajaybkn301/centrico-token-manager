package it.hype.centrico.token.manager.application.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SsoTokenData {

    @JsonProperty(value = "ssotoken")
    private String ssoToken;

    @JsonProperty(value = "ibcode")
    private String ibCode;

    @JsonProperty(value = "uid")
    private String uid;

    @JsonProperty(value = "abi")
    private String abi;

    @JsonProperty(value = "auth_level")
    private String authLevel;

    @JsonProperty(value = "client_id")
    private String clientId;

    @JsonProperty(value = "cot")
    private String cot;

    @JsonProperty(value = "last_activity")
    private String lastActivity;
}
