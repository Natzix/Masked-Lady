package org.natzi.maskedlady.service.ott;

import org.natzi.maskedlady.config.exception.OneTimeTokenException;
import org.natzi.maskedlady.entity.OttToken;
import org.natzi.maskedlady.utils.dto.HasAToken;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

public interface TokenManagerStrategy<T extends OttToken> {

    default HasAToken generate(String username) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[32];

        secureRandom.nextBytes(bytes);

        String ott  = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(bytes);

        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(5);

        return new HasAToken(username, ott, expiresAt);
    }

    default void verifyLifeOtt(LocalDateTime expireAt, boolean used) {
        LocalDateTime now = LocalDateTime.now();

        if (expireAt.isBefore(now)) {
            throw new OneTimeTokenException("El token ya no puede ser utilizado");
        }
        if (used) {
            throw new OneTimeTokenException("El token ya se uso");
        }
    }

    void consume(T ott);
}
