package it.hype.centrico.token.manager.domain.utils;

import it.hype.authhandler.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LockUtils {

    public static String calculateLockId(User user) {
        return user.getTenant() + "_" + user.getCorrelationId();
    }

}
