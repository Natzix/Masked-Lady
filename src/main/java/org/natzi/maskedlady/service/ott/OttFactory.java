package org.natzi.maskedlady.service.ott;

import org.natzi.maskedlady.entity.OneTimeToken;
import org.natzi.maskedlady.entity.OttTimeTable;
import org.natzi.maskedlady.entity.OttToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OttFactory {

    private final Map<Class<?>, TokenManagerStrategy<? extends OttToken>> factories = new HashMap<>();

    public OttFactory(OneTimeTokenService ott, OneTimeTokenValidation ottv) {
        factories.put(OneTimeToken.class, ott);
        factories.put(OttTimeTable.class, ottv);
    }

    public <T extends OttToken> TokenManagerStrategy<T> factoryToken(Class<T> tClass) {
        TokenManagerStrategy<?> strategy = factories.get(tClass);

        if (strategy == null) {
            throw new IllegalArgumentException(tClass + "no tiene soporte de implementación");
        }

        return (TokenManagerStrategy<T>) strategy;
    }
}
