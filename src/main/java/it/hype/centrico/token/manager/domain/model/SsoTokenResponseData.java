package it.hype.centrico.token.manager.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SsoTokenResponseData {
    private String httpCode;
    private String responseCode;
    private String description;
    private SsoTokenInfo data;
}
