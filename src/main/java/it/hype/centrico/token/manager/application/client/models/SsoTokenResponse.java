package it.hype.centrico.token.manager.application.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SsoTokenResponse{

    @JsonProperty(value="response_http_code")
    private String httpCode;

    @JsonProperty(value="response_code")
    private String responseCode;

    @JsonProperty(value="response_desc")
    private String description;

    @JsonProperty(value="response_data")
    private SsoTokenData data;
}
