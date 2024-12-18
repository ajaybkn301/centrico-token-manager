package it.hype.centrico.token.manager.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsData {
    private String uid;
    private String ibCode;
    private String daysBeforePinExpire;
    private String daysBeforeOtpExpire;
    private String authLevel;
}
