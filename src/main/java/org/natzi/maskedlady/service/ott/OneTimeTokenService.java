package org.natzi.maskedlady.service.ott;

import org.natzi.maskedlady.entity.OneTimeTokenLogin;
import org.natzi.maskedlady.repository.OneTimeTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class OneTimeTokenService implements TokenManagerStrategy<OneTimeTokenLogin> {

    private final OneTimeTokenRepository repository;
    private final Logger lg = Logger.getLogger(OneTimeTokenService.class.getName());

    @Autowired
    public OneTimeTokenService(OneTimeTokenRepository repository) {
        this.repository = repository;
    }

    @Override
    public void consume(OneTimeTokenLogin ott) {
        verifyLifeOtt(ott.getExpiresAt(), ott.isUsed());
        ott.setUsed(true);
        OneTimeTokenLogin token = repository.save(ott);
    }
}