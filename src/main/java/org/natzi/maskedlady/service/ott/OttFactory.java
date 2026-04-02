package org.natzi.maskedlady.service.ott;

import org.natzi.maskedlady.entity.OneTimeTokenLogin;
import org.natzi.maskedlady.entity.EmailConfirmationUUID;
import org.natzi.maskedlady.entity.TokenConfirmationTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OttFactory {

    private final Map<Class<?>, TokenManagerStrategy<? extends TokenConfirmationTemplate>> factories = new HashMap<>();

    public OttFactory(OneTimeTokenService ott, OneTimeTokenValidation ottv) {
        factories.put(OneTimeTokenLogin.class, ott);
        factories.put(EmailConfirmationUUID.class, ottv);
    }

    public <T extends TokenConfirmationTemplate> TokenManagerStrategy<T> factoryToken(Class<T> tClass) {
        TokenManagerStrategy<?> strategy = factories.get(tClass);

        if (strategy == null) {
            throw new IllegalArgumentException(tClass + "no tiene soporte de implementación");
        }

        return (TokenManagerStrategy<T>) strategy;
    }
}
