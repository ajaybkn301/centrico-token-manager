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
public class UserDetails {

    @JsonProperty(value = "uid")
    private String uid;

    @JsonProperty(value = "ibcode")
    private String ibCode;

    @JsonProperty(value = "pin_days_before_expiring")
    private String daysBeforePinExpire;

    @JsonProperty(value = "otp_days_before_expiring")
    private String daysBeforeOtpExpire;

    @JsonProperty(value = "auth_level")
    private String authLevel;
}
