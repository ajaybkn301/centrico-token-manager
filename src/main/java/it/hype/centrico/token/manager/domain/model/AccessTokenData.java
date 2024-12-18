package it.hype.centrico.token.manager.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenData {
    private String httpCode;
    private String responseCode;
    private String description;
    private String accessToken;
    private UserDetailsData userDetails;

}